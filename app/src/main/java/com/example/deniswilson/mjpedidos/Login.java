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

import java.io.IOException;

public class Login extends AppCompatActivity {

    EditText edtUsuario, edtSenha;
    Button btnEntrar;
    TextView txvCriar;

    String url = "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        txvCriar = (TextView) findViewById(R.id.txvCriar);
    }

    public void Logar(View view){
        /*Verificando estado da RED (Fonte: Android Developers*/
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

                String usuario = edtUsuario.getText().toString();
                String senha = edtSenha.getText().toString();

                if (usuario.isEmpty() || senha.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Preencha todos os campos !", Toast.LENGTH_LONG).show();
                }else{
                    url = "http://192.168.0.102/login.php";
                    parametros = "usuario" + usuario + "senha" + senha;

                    new Dados().execute(url);
                }
        } else {
            Toast.makeText(getApplicationContext(),"Não há conexão !", Toast.LENGTH_LONG).show();
        }

    }

    private class Dados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.

                return Conexao.postDados(urls[0], parametros);

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String resultado) {
            if (resultado.contains("Login realizado com sucesso")){
                Intent telacadastro = new Intent(Login.this, TelaCadastro.class);
                startActivity(telacadastro);
                }else{
                    Toast.makeText(getApplicationContext(),"Usuário ou senha incorretos !", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        protected void onPause(){
            super.onPause();
            finish();
        }
    }



