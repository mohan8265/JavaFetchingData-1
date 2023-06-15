package org.geekster;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        String urlStr = "https://api.chucknorris.io/jokes/random";
        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;

        try {
            url = new URL(urlStr);
        }catch (MalformedURLException e){
            System.out.println("Problem in Url");
        }

        try{
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        }catch (Exception e){
            System.out.println("Connection Problem");
        }

        if(responseCode == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder data = new StringBuilder();
            String readData = null;

            while ((readData = in.readLine())!= null){
                data.append(readData);
            }

            in.close();


            JSONObject jsonResponse = new JSONObject(data.toString());

            System.out.println("categories " + ": " + jsonResponse.get("categories").toString());
            System.out.println("icon_url " + ": " + jsonResponse.get("icon_url"));
            System.out.println("created_at " +": " + jsonResponse.get("created_at"));
            System.out.println("id " + ": " + jsonResponse.get("id"));
            System.out.println("updated_at " + ": " + jsonResponse.get("updated_at"));
            System.out.println("url " + ": " + jsonResponse.get("url"));
            System.out.println("value " + ": " + jsonResponse.get("value"));
        }
        else{
            System.out.println("API call did not make!!!");
        }
    }
}