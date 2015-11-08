/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CurrencyFacade;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Dennis
 */
@Path("currency")
@RolesAllowed("User")
public class CurrencyRestService {
    
    Gson gson;
    CurrencyFacade facade;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CurrencyRestService
     */
    public CurrencyRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        
//        CHANGE PU ON DEPLOYMENT
        facade = new CurrencyFacade();
    }

    /**
     * Calculate currency given currency from and to input
     * 
     * @return an instance of java.lang.String
     */
    @GET
    @Path("calculator/{amount}/{fromcurrency}/{tocurrency}")
    @Produces("application/json")
    @Consumes("application/json")
    public String calculateCurrency(@PathParam("amount") double amount, @PathParam("fromcurrency") String fromCur, @PathParam("tocurrency") String toCur) {
        return gson.toJson(facade.calculateCurrency(amount, fromCur, toCur));
    }

}
