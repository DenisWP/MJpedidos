package com.example.deniswilson.mjpedidos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Denis Wilson on 27/11/2016.
 */

public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsuario){
        URL url;
        HttpURLConnection connection = null;

        try {
            url = new URL(urlUsuario); //Variavel URL
            connection = (HttpURLConnection) url.openConnection(); //abrindo conexão
            /*
            * Passando parâmetros através da comunicação
            * */
            connection.setRequestMethod("POST"); //
            connection.setRequestProperty("Content-Type","application/x-www-form-urlenconded"); // Como os dados serão codificados, para separar informações
            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametrosUsuario.getBytes().length));//passando numeros de bits para conexão
            connection.setRequestProperty("Content-Language", "pt-BR");

            /*Desabilitando cache*/
            connection.setUseCaches(false);

            connection.setDoInput(true);//habilitando a entrada e saida de dados.
            connection.setDoOutput(true);


            /*
            * Enviando e capturando dados.
            *
            * */
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(parametrosUsuario);
            outputStreamWriter.flush();

            /*Obtendo informações*/

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String linha;
            StringBuffer resposta = new StringBuffer();


            while ((linha = bufferedReader.readLine()) != null){
                resposta.append(linha);
                resposta.append('\r');
            }
            bufferedReader.close();
            return resposta.toString();

        }catch (Exception erro){
            return null;
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }

    }


}
