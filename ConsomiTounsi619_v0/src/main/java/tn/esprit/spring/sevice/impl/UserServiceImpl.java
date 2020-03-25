package tn.esprit.spring.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.sevice.interfece.IUserService;
import tn.esprit.spring.entity.User;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository var;
	
	
	@Override	
	public User  addUser(User user){
		
			var.save(user) ;
			return user;
			
		}

	@Override
	public User findUserByUsername(String username) {
	    return var.findByUsername(username);
	}

	@Override
	public User findbyid(long id){
		
			return var.findById(id).get() ;
		}

	@Override
	public List<User> mylist(){
		
			return  (List<User>)var.findAll();
		}

	@Override	
	public void deleteUser(long id){
		
			var.deleteById(id); 
		
		}
	@Override	
	public User authenticate(String username , String email){
			User u = var.findByUsernameAndEmail(username, email);
			return u;
	}

}
