package com.example.deniswilson.mjpedidos;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Denis Wilson on 06/12/2016.
 */

public class Parser extends AsyncTask <Void, Integer, Integer> {


    Context context;
    ListView list;
    String data;
    ArrayList<String> players=new ArrayList<>();
    ProgressDialog pd;


    public Parser(Context context, String data, ListView list) {
        this.context = context;
        this.data = data;
        this.list = list;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(context);
        pd.setTitle("Baixando");
        pd.setMessage("Baixando ... por favor aguarde.");
        pd.show();
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer == 1)
        {
            //ADAPTER
            final ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,players);
            //ADAPT TO LISTVIEW
            list.setAdapter(adapter);
            //LISTENET
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Snackbar.make(view,players.get(position),Snackbar.LENGTH_SHORT).show();

                    Intent irS = new Intent(context, Comprar.class);
                    irS.putExtra("descritivo", players.get(position));
                    context.startActivity(irS);





                }
            });
        }else
        {
            Toast.makeText(context,"Erro",Toast.LENGTH_SHORT).show();
        }
        pd.dismiss();
    }



    //PARSE RECEIVED DATA
    private int parse()
    {
        try
        {
            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja=new JSONArray(data);
            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo=null;
            players.clear();
            //LOOP THRU ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                //RETRIOEVE NAME
                String name=jo.getString("nome_produto");
                String valor=jo.getString("descritivo");
                //ADD IT TO OUR ARRAYLIST
                players.add(name +"\n"+ valor);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

