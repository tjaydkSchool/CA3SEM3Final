package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("create")
public class User {
    Gson gson;
    UserFacade facade;
    
    public User() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        
//        CHANGE PU ON DEPLOYMENT
        facade = new UserFacade();
    }

    @POST
    @Consumes("application/json")
    public void createUser(String userData) {
        facade.createUser(gson.fromJson(userData, entity.User.class));
    }
    
    @GET
    @Consumes("application/json")
    public void createDB(){
        facade.createDB();
    }

}
