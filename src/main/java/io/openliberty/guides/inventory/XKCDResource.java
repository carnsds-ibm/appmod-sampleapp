package io.openliberty.guides.inventory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.Entity;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.openliberty.guides.inventory.client.XKCDClient;

@RequestScoped
@Path("")
public class XKCDResource {

  private static String xkcdRestUriA = "http://xkcd.com/";
  private static String xkcdRestUriB = "/info.0.json";

  @Inject
  XKCDManager manager;

  @GET
  @Path("/{comicid}")
  @Produces(MediaType.TEXT_HTML)
  public Response getComic(@PathParam("comicid") String id) {
    if (id == null || id.length() == 0) {
      return Response.status(Response.Status.NOT_FOUND)
                     .entity(
                         "<h1>ERROR: Comic is nonexistant or number not specified correctly: " + id + " .</h1>")
                     .build();
    }
    
    boolean isInt = isInteger(id);

    if (!isInt && id.equalsIgnoreCase("random")) {
      return Response.status(Response.Status.OK)
                     .entity(
                         "<h1>Fetching random comic..</h1>")
                     .build();
    }
    try {
      InputStream stream = XKCDClient.getResponse(xkcdRestUriA + id + xkcdRestUriB).readEntity(InputStream.class);
      InputStreamReader isReader = new InputStreamReader(stream);
      BufferedReader reader = new BufferedReader(isReader);
      StringBuffer sb = new StringBuffer();
      String body;
      while ((body = reader.readLine()) != null) {
        sb.append(body);
      }
      System.err.println(sb.toString() + " <--");
    } catch (Exception e) {
      System.err.println("ERROR occurred for entity" + e);
    }
    // JsonReader jsonReader = Json.createReader(
    //                           new StringReader(
    //                           XKCDClient.getResponse(xkcdRestUriA + id + xkcdRestUriB).toString()));

    // JsonObject jsonObject = jsonReader.readObject();
    // String comicImg = jsonObject.getString("img");

    //jsonReader.close();
    //System.out.println(comicImg);
    return Response.status(Response.Status.OK)
        .entity(
          "<h1>Your comic: </h1>" +
          "<img src=" + "comicImg" + " alt=" + "Requested Comic" + " />"
        ).build();
  }

  private boolean isInteger(String number) {
    boolean isInt = false;
    try {
      isInt = Integer.parseInt(number) >= 0;
    } catch(Exception e) {
      isInt = false;
    }
    return isInt;
  }
}
