package io.openliberty.guides.inventory.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;

import io.openliberty.guides.inventory.model.XKCDObj;


public class XKCDClient {

    public static String getResponse(String uri) {
        
        Client client = ClientBuilder.newClient();
        Response response = client.target(uri).request().get();
        InputStream responseObject = response.readEntity(InputStream.class);
        InputStreamReader inputStreamReader = new InputStreamReader(responseObject);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String body = "";
        StringBuilder data = new StringBuilder();
        try {
            while ((body = bufferedReader.readLine()) != null) {
                data.append(body);
            }
        } catch (Exception e) {
            System.err.println("Error reading data" + e);
        }
        System.out.println(data);
        client.close();
        response.close();
        return data.toString();
    }
}