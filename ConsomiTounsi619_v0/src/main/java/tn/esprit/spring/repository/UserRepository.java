package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	User findByUsernameAndEmail(String username,String email);
	
	

}
