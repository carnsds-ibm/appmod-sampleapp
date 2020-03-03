package io.openliberty.guides.inventory.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Client;


public class XKCDClient {

    public static Response getResponse(String uri) {
        
        Response response = null;
        try {
        Client client = ClientBuilder.newBuilder()
                        .property("connection.timeout", 200)
                        .build();

        response = client.target(uri)
                            .request()
                            .get();

        client.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return response;
    }
}