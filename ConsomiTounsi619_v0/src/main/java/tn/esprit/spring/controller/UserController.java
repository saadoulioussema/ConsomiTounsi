package tn.esprit.spring.controller;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.sevice.interfece.IUserService;
import tn.esprit.spring.entity.User;

@RestController
public class UserController {
	@Autowired
	IUserService iUserService;
	
	public static tn.esprit.spring.entity.User USERCONNECTED ;
	
	@RequestMapping("/")
	@ResponseBody
	public String welcome() { return "Bonjour, Bienvenue à l'application de test des Web ServicesREST."; }
	
	@PostMapping("/register")
	@ResponseBody
	public Response addUser(@RequestBody User u) {
		User userExists = iUserService.findUserByUsername(u.getUsername());
        if (userExists != null) {
        	return Response.status(Status.NOT_FOUND).entity( "There is already a user registered with the user name provided").build();
                           
        }
        else {
	//User user = iUserService.addUser(u);
	return Response.status(Status.OK).entity("add successful").build();
        }
	}
	//////login/////
	@GetMapping("/login/{username}/{email}")
	@ResponseBody
	public Response loginUser(@PathVariable("username") String username,@PathVariable("email") String email) {
	   
		USERCONNECTED=iUserService.authenticate(username, email);
		if (USERCONNECTED != null) {
			return Response.status(Status.OK).entity("login successful").build();                           
        }
		else {
			
        	return Response.status(Status.NOT_FOUND).entity( "There is no user registered with the user name provided").build();
		}
	}
	///list of users////
	@GetMapping("/mylist")
	@ResponseBody
	public List<User> getUsers() {
	List<User> list = iUserService.mylist();
	return list;
	}
	/////get user by id/////
	@GetMapping("/retrieve-user/{user-id}")
	@ResponseBody
	public User findbyid(@PathVariable("user-id") long userId) {
	return iUserService.findbyid(userId);
	}
    ////delete user////
    @DeleteMapping("/delete-user/{user-id}")
    @ResponseBody
    public void deleteUser(@PathVariable("user-id") long userId) {
    	iUserService.deleteUser(userId);
    }
	
	
	
	
}
