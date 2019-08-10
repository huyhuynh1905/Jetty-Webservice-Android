package com.huyhuynh.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.huyhuynh.model.User;
import com.huyhuynh.model.UserRepository;

@Path("/android")
public class ServiceAndroid {
	
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/test")
    public String test() {
        String result = "Rest Test Services successfully started";
        return "Hello";
    }

    UserRepository repo = new UserRepository();
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{email}/{pass}")
    public User getLogin(@PathParam("email") String email,@PathParam("pass") String pass) {
    	return repo.getUser(email, pass);
    }
    
}