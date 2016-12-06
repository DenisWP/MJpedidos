package com.example.deniswilson.mjpedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListarProdutos extends AppCompatActivity {

    ListView listarProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_produtos);

        listarProdutos = (ListView) findViewById(R.id.lstProdutos);
    }
}
