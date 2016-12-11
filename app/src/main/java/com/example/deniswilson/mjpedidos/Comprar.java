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
import android.widget.TextView;
import android.widget.Toast;

public class Comprar extends AppCompatActivity {

    TextView descrição, preço, valorTotal;
    Button calcular, solicitar;
    EditText quantidade;

    String url = "";
    String parametros = "";
    Double valorPagar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_comprar);

        quantidade = (EditText) findViewById(R.id.edtQquantidade);
        descrição = (TextView) findViewById(R.id.txvDescricao);
        preço = (TextView) findViewById(R.id.txvPreco);
        valorTotal = (TextView) findViewById(R.id.txvValorTotal);
        calcular = (Button) findViewById(R.id.bCalcular);
        solicitar = (Button) findViewById(R.id.bSolicitar);

        final String dados, vt;
        Intent in = getIntent();
        final Parser parser = new Parser(); // Chamando a classe, para pegar o código

        if (in != null && in.hasExtra("produtos")){
            dados = in.getStringExtra("produtos");
            descrição.setText(dados);
            preço.setText(parser.desc);
        }

       

        /*calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double pegaQuant = Double.parseDouble(quantidade.getText().toString());
                valorPagar = Double.parseDouble(parser.desc) * pegaQuant;
                valorTotal.setText(String.valueOf(valorPagar));
            }
        });*/


        solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                        if (networkInfo != null && networkInfo.isConnected()){

                            Double pegaQuant = Double.parseDouble(quantidade.getText().toString());
                            valorPagar = Double.parseDouble(parser.desc) * pegaQuant;
                            valorTotal.setText(String.valueOf(valorPagar));


                            String url = "http://192.168.0.104/mjpedidos/solicitar.php?nome_cli=denis&endereco_cli=teste";


                            //String url = "http://192.168.0.104/mjpedidos/cadastrar.php?nome_cli=" + valorPagar + "&endereco_cli=teste";

                            new Dados().execute(url);

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
        protected void onPostExecute(String resultado){
            if(resultado.contains("solicitar_erro")){
                Toast.makeText(getApplicationContext(), " Erro ao solicitar entrega !", Toast.LENGTH_LONG).show();
            }else if (resultado.contains("solicitar_ok")) {
                Toast.makeText(getApplicationContext(), "Solicitação de entrega realizada com sucesso !", Toast.LENGTH_LONG).show();
                Intent telacadastro = new Intent(Comprar.this, ListarProdutos.class);
                startActivity(telacadastro);
            } else {
                Toast.makeText(getApplicationContext(), "Não foi possível Solicitar !", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }


}
