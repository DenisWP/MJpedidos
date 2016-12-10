package com.example.deniswilson.mjpedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Comprar extends AppCompatActivity {

    TextView descrição, preço, valorTotal;
    Button calcular, solicitar;
    EditText quantidade;

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

        String dados;
        Intent in = getIntent();

        if (in != null && in.hasExtra("descritivo")){
            dados = in.getStringExtra("descritivo");
            descrição.setText(dados);
        }

        preço.setText("R$: 7,00");


    }

    public void totalPagar(){
        String pegaQuant = quantidade.getText().toString();




    }
}
