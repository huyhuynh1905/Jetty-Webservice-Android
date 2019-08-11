package com.huyhuynh.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.huyhuynh.model.LoginRequest;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public User getLogin(LoginRequest loginRequest) {
    	System.out.println(loginRequest.getEmail());
    	System.out.println(loginRequest.getPass());
    	return repo.getUser(loginRequest.getEmail(), loginRequest.getPass());
    }
    
    //Register
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public User registerUser(User user) {
    	if(repo.checkUser(user.getEmail())) {
    		System.out.println("Đăng kí thành công!");
    		repo.createUser(user);
    		user.setEmail("Đăng kí thành công!");
    		return user;
    	} else {
    		System.out.println("Đăng kí thất bại");
    		user.setEmail("Đăng kí thất bại! Tài khoản đã tồn tại!");
    		return user;
    	}
    }
    
    
    //Delete
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public User deleteUser(User user) {
    	if(!repo.checkUser(user.getEmail())) {
    		System.out.println("Xoá thành công!");
    		repo.deleteUser(user);
    		user.setEmail("Xoá thành công!");
    		return user;
    	} else {
    		System.out.println("Xoá thất bại");
    		user.setEmail("Xoá thất bại! Tài khoản không tồn tại!");
    		return user;
    	}
    }
    
}