package com.example.deniswilson.mjpedidos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListarProdutos extends AppCompatActivity {

    ListView listarProdutos;
    String url = "http://192.168.0.104/mjpedidos/listar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_produtos);

        listarProdutos = (ListView) findViewById(R.id.lstProdutos);
        Listar l = new Listar(this,url,listarProdutos);
        l.execute();

    }





    public class Listar extends AsyncTask<Void, Integer, String> {

        Context context;
        String add;
        ListView list;

        ProgressDialog pd;

        public Listar(Context context, String add, ListView list){
            this.context = context;
            this.add = add;
            this.list = list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setTitle("Baixando ... ");
            pd.setMessage("Atualizando, por favor aguarde ...");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String data=Baixar();
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pd.dismiss();
            if(s != null)
            {
                Parser p=new Parser(context,s,list);
                p.execute();
            }else
            {
                Toast.makeText(context,"Unable to download data",Toast.LENGTH_SHORT).show();
            }
        }


        private String Baixar()
        {
            InputStream is=null;
            String line =null;
            try {
                URL url=new URL(add);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                is=new BufferedInputStream(con.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                StringBuffer sb=new StringBuffer();
                if(br != null) {
                    while ((line=br.readLine()) != null) {
                        sb.append(line+"n");
                    }
                }else {
                    return null;
                }
                return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(is != null)
                {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }


}
