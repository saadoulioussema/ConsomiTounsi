package tn.esprit.spring.sevice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.UserDTO;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	
	
	
	public User addUser(UserDTO user) {
		User newuser = new User();
		newuser.setUsername(user.getUsername());
		newuser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newuser.setFirstName(user.getFirstName());
		newuser.setLastName(user.getLastName());
		newuser.setEmail(user.getEmail());
		newuser.setRole(user.getRole());
		return userRepo.save(newuser);
	}

	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	//NEW ONE 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}


	public User findbyid(long id) {

		return userRepo.findById(id).get();
	}


	public List<User> mylist() {

		return (List<User>) userRepo.findAll();
	}

	public void deleteUser(long id) {

		userRepo.deleteById(id);

	}
	
	public User authenticate(String username, String email) {
		User u = userRepo.findByUsernameAndEmail(username, email);
		return u;
	}
	public User authenticatejsf(String username, String password) {
		User u = userRepo.findByUsernameAndPassword(username, password);
		return u;
	}

//	@Override
//	public User authenticate(String username, String email) {
//		User u = var.findByUsernameAndEmail(username, email);
//		return u;
//	}

}
