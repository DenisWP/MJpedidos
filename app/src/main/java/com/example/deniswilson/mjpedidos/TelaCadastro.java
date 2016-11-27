package com.example.deniswilson.mjpedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TelaCadastro extends AppCompatActivity {

    EditText nome, telefone, endereço, numero, bairro, cidade, cep, email;
    Button salvar, cancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);


        nome = (EditText) findViewById(R.id.edtNome);
        telefone = (EditText) findViewById(R.id.edtTel);
        endereço = (EditText) findViewById(R.id.edtLongradouro);
        numero = (EditText) findViewById(R.id.edtNumero);
        bairro = (EditText) findViewById(R.id.edtBairro);
        cidade = (EditText) findViewById(R.id.edtCidade);
        cep = (EditText) findViewById(R.id.edtCep);
        email = (EditText) findViewById(R.id.edtEmail);

        salvar = (Button) findViewById(R.id.btnSalvar);
        cancelar = (Button) findViewById(R.id.btnCancelar);




    }
}
