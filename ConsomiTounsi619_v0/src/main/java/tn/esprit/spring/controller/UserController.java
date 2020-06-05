package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.config.JwtTokenUtil;
import tn.esprit.spring.entity.JwtRequest;
import tn.esprit.spring.entity.JwtResponse;


import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.UserDTO;
import tn.esprit.spring.sevice.impl.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl UserService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public static User USERCONNECTED ;
	
//	@RequestMapping("/")
//	@ResponseBody
//	public String welcome() { return "Bonjour, Bienvenue à l'application de test des Web ServicesREST"; }
	

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		System.out.println("*********Entring the register method ************");
		return ResponseEntity.ok(UserService.addUser(user));
	}

	@PostMapping("/authenticate")
	@ResponseBody
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = UserService.loadUserByUsername(authenticationRequest.getUsername());
			final User user = UserService.findUserByUsername(authenticationRequest.getUsername());
			USERCONNECTED = user ;
			System.out.println(USERCONNECTED.getRole());


			final String token = jwtTokenUtil.generateToken(userDetails);
			System.out.println(token);
			return ResponseEntity.ok(new JwtResponse(token));
		} catch (Exception e) {
			System.out.println(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	///list of users////
	@GetMapping("/mylist")
	@ResponseBody
	public List<User> getUsers() {
	List<User> list = UserService.mylist();
	return list;
	}
	/////get user by id/////
	@GetMapping("/retrieve-user/{user-id}")
	@ResponseBody
	public User findbyid(@PathVariable("user-id") long userId) {
	return UserService.findbyid(userId);
	}
    ////delete user////
    @DeleteMapping("/delete-user/{user-id}")
    @ResponseBody
    public void deleteUser(@PathVariable("user-id") long userId) {
    	UserService.deleteUser(userId);
    }
    
   
	
    

}
