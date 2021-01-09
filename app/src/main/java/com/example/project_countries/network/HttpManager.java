package com.example.project_countries.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class HttpManager implements Callable<String> {

    private URL url;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    private final String urlAddress;

    public HttpManager(String urlAddress) { //ii dau url-ul cand fac o instanta a clasei, nu-l hardcodez aici
        this.urlAddress = urlAddress;
    }

    @Override
    public String call() { //aici se face conexiunea la url si preluarea de continut
        try {
            url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder rezultat = new StringBuilder();
            String linie;
            while ((linie = bufferedReader.readLine()) != null) { //cat timp mai am de citit
                    rezultat.append(linie);
            }
            return rezultat.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //aici inchidem toate clasele noastre utilitare/conexiunile
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }
        return null;
    }
}
