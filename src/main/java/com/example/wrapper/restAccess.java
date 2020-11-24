package com.example.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class restAccess {
    public static String fetchDataFromRestServer(URL urlForGet) throws IOException {
        String readLine = null;

        HttpURLConnection connection = (HttpURLConnection) urlForGet.openConnection();
        connection.setRequestMethod("GET");
        Integer responseCode = connection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();

            while((readLine = in.readLine())!= null){
                response.append(readLine);
                return response.toString();
            } in.close();

        }
        return responseCode.toString();
    }
}

