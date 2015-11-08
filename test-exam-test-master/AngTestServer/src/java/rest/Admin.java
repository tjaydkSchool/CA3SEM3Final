package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.AdminFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("admin")
//@RolesAllowed("Admin")
public class Admin {

    Gson gson;
    AdminFacade facade;

    public Admin() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
//        CHANGE PU ON DEPLOYMENT
        facade = new AdminFacade();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
                + "\"serverTime\": \"" + now + "\"}";
    }

    @GET
    @Path("user/complete")
    @Produces("application/json")
    public String getUserList() {
        return gson.toJson(facade.getUserList());
    }
    
    @DELETE
    @Path("{user}")
    @Produces
    public String deleteUser(@PathParam("user") String user) {
        facade.deleteUser(user);
        return user;
    }

}
