package com.example.deniswilson.mjpedidos;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {

    EditText nome, senha, telefone, endereço, numero, bairro, cidade, cep, email;
    Button salvar, cancelar;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        nome = (EditText) findViewById(R.id.edtNome);
        senha = (EditText) findViewById(R.id.edtSenha);
        telefone = (EditText) findViewById(R.id.edtTel);
        endereço = (EditText) findViewById(R.id.edtLongradouro);
        numero = (EditText) findViewById(R.id.edtNumero);
        bairro = (EditText) findViewById(R.id.edtBairro);
        cidade = (EditText) findViewById(R.id.edtCidade);
        cep = (EditText) findViewById(R.id.edtCep);
        email = (EditText) findViewById(R.id.edtEmail);


        cancelar = (Button) findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        salvar = (Button) findViewById(R.id.btnSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    String nomeC = nome.getText().toString();
                    String senhaC = senha.getText().toString();
                    String telefoneC = telefone.getText().toString();
                    String endereçoC = endereço.getText().toString();
                    String numeroC = numero.getText().toString();
                    String bairroC = bairro.getText().toString();
                    String cidadeC = cidade.getText().toString();
                    String cepC =  cep.getText().toString();
                    String emailC = email.getText().toString();

                    if (nomeC.isEmpty()
                            || senhaC.isEmpty()
                            || telefoneC.isEmpty()
                            || endereçoC.isEmpty()
                            || numeroC.isEmpty()
                            || bairroC.isEmpty()
                            || cidadeC.isEmpty()
                            || cepC.isEmpty()
                            || emailC.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Preencha todos os campos !", Toast.LENGTH_LONG).show();
                    }else{
                        /*Montando a url, para realização da conexão*/
                        url = "http://192.168.0.101/mjpedidos/cadastrar.php";
                        parametros = "nome=" +nomeC+ "&senha=" +senhaC+ "&telefone=" +telefoneC+ "&longradouro=" +endereçoC+
                                     "&numero=" +numeroC+ "&bairro=" +bairroC+ "&cidade=" +cidadeC+ "&cep=" +cepC+ "&email" +emailC;

                        new Dados().execute(url);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Não há conexão !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }





    private class Dados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);

        }

        @Override
        protected void onPostExecute(String resultado) {
            nome.setText(resultado);
        }

    }
}
