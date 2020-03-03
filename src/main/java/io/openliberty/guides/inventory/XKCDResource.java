package io.openliberty.guides.inventory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/{comicid}")
public class XKCDResource {

  @Inject
  XKCDManager manager;

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getComic(@QueryParam("random") String random, @PathParam("comicid") String id) {
    // Get properties

    boolean isInt = isInteger(id);

    if (random == null && id == null) {
      return Response.status(Response.Status.NOT_FOUND)
                     .entity(
                         "<h1>ERROR: Comic is nonexistant or number not specified correctly.</h1>")
                     .build();
    }

    if (null != random && random.length() > 0) {
      return Response.status(Response.Status.OK)
                     .entity(
                         "<h1>Fetching random comic..</h1>")
                     .build();
    }

    return Response.status(Response.Status.OK)
        .entity("<h1>Hello World.</h1>").build();
  }

  private boolean isInteger(String number) {
    boolean isInt = false;
    try {
      isInt = Integer.parseInt(number) >= 0;
    } catch(Exception e) {
      System.err.println("ERROR: cannot convert comic to number:" + e);
    }
    return isInt;
  }
}
