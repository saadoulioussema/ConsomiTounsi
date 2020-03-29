package tn.esprit.spring.sevice.interfece;

import java.util.List;

import tn.esprit.spring.entity.User;



public interface IUserService {
	List<User> mylist();
	User addUser(User user);
	User findbyid(long id);
	void deleteUser(long id) ;
	User authenticate(String username , String email);
	User findUserByUsername(String username);
}
