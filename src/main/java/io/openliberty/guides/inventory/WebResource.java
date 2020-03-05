package io.openliberty.guides.inventory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("")
public class WebResource {

  @Inject
  XKCDManager manager;

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getCalculator() {
    
    return Response.status(Response.Status.OK)
        .entity(
          "<html>\n" +
          "<head>\n" +
          "<script>\n" +
          "function dis(val)"+
          "{ document.getElementById('result').value+=val }\n" +  
          "function solve() {\n" +
          "  let x = document.getElementById(\"result\").value \n" +
                    "let y = eval(x)\n" +
                    "document.getElementById(\"result\").value = y }\n" +
                "function clr() { document.getElementById(\"result\").value = \"\" }\n" +
             "</script>\n" +
             "<style>\n" +
                ".title{\n" +
                "margin-bottom: 10px;\n" +
                "text-align:center;\n" +
                "width: 210px;\n" +
                "color:green;\n" +
                "border: solid black 2px;}\n" +
                "input[type='button'] {\n" + 
                "background-color:green; \n" +
                "color: black;\n" +
                "border: solid black 2px;\n" +
                "width:100% }\n" + 
                "input[type=\"text\"] {\n" +
                "background-color:white;\n" +
                "border: solid black 2px;\n" +
                "width:100% } </style> \n" +
          "</head> <body> \n" +
             "<div class = title >Simple Calculator</div>\n" +
             "<table border=\"1\">\n" +
                "<tr><td colspan=\"3\"><input type=\"text\" id=\"result\"/></td>\n" +
                   "<td><input type=\"button\" value=\"c\" onclick=\"clr()\"/> </td> \n" +
                "</tr>\n" + 
                "<tr>\n" +
                   "<td><input type=\"button\" value=\"1\" onclick=\"dis('1')\"/> </td>\n" +
                   "<td><input type=\"button\" value=\"2\" onclick=\"dis('2')\"/> </td>\n" + 
                   "<td><input type=\"button\" value=\"3\" onclick=\"dis('3')\"/> </td>\n" +
                   "<td><input type=\"button\" value=\"/\" onclick=\"dis('/')\"/> </td>\n" +
                "</tr>\n" + 
                "<tr>\n" + 
                   "<td><input type=\"button\" value=\"4\" onclick=\"dis('4')\"/> </td>\n" +
                   "<td><input type=\"button\" value=\"5\" onclick=\"dis('5')\"/> </td>\n" + 
                   "<td><input type=\"button\" value=\"6\" onclick=\"dis('6')\"/> </td>\n" +
                   "<td><input type=\"button\" value=\"-\" onclick=\"dis('-')\"/> </td>\n" +
                "</tr>\n" + 
                "<tr>\n" + 
                   "<td><input type=\"button\" value=\"7\" onclick=\"dis('7')\"/> </td>\n" +
                   "<td><input type=\"button\" value=\"8\" onclick=\"dis('8')\"/> </td>\n" +
                   "<td><input type=\"button\" value=\"9\" onclick=\"dis('9')\"/> </td>\n" + 
                   "<td><input type=\"button\" value=\"+\" onclick=\"dis('+')\"/> </td>\n" +
                "</tr>\n" +
                "<tr>\n" + 
                   "<td><input type=\"button\" value=\".\" onclick=\"dis('.')\"/> </td>\n" + 
                   "<td><input type=\"button\" value=\"0\" onclick=\"dis('0')\"/> </td>\n" + 
                   "<td><input type=\"button\" value=\"=\" onclick=\"solve()\"/> </td>\n" +
                   "<td><input type=\"button\" value=\"*\" onclick=\"dis('*')\"/> </td>\n" + 
                "</tr> </table> </body> </html>"
        ).build();
  }
}
