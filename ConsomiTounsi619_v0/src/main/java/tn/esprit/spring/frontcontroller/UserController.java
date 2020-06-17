package tn.esprit.spring.frontcontroller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

//import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.sevice.impl.UserServiceImpl;
import tn.esprit.spring.sevice.interfece.IUserService;

@Scope(value = "session")
@Controller(value = "userFController")
@ELBeanName(value = "userFController")
@Join(path = "/", to = "/login.jsf")
public class UserController {
	

	public static User USERCONNECTED ;

	//@Autowired
	//private AuthenticationManager authenticationManager;
	
	
	//@Autowired
	//private PasswordEncoder bcryptEncoder;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	private List<User> users;
	
	
	private String username;
	private Boolean loggedIn;
	private String password;
	private User authenticatedUser;
	
	
	@PostConstruct
	public void init() {
		authenticatedUser = new User();
	}
	
	
	public String dologin() {
		String navigateTo = "null";
		
		authenticatedUser = userServiceImpl.authenticatejsf(username,password);
	 
		if (authenticatedUser != null && authenticatedUser.getRole().equals("admin")  ) {
			USERCONNECTED = authenticatedUser;
			System.out.println(USERCONNECTED.getFirstName());
			navigateTo = "/welcomeadmin.xhtml?faces-redirect=true";
			loggedIn = true;
		}
		
		else if(authenticatedUser != null && authenticatedUser.getRole().equals("client")) {
			USERCONNECTED = authenticatedUser;
			System.out.println(USERCONNECTED.getFirstName());
			navigateTo = "/welcomeclient.xhtml?faces-redirect=true";
			loggedIn = true;
			
	}
		
	  else {
			FacesMessage facesMessage = new FacesMessage("Login Failed: please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		}

		return navigateTo;
	}
	
	
/*
 *login with token  
	public String dologin()  {
		String navigateTo = "null";
	
		String UserName =authenticatedUser.getUsername();
		String UserPass = authenticatedUser.getPassword();
		authenticatedUser = UserService.findUserByUsername(UserName);			
		if (authenticatedUser != null && authenticatedUser.getRole() == Role.ADMINISTRATEUR && bcryptEncoder.matches(UserPass, authenticatedUser.getPassword())) {
			navigateTo = "/welcome.xhtml?faces-redirect=true";
			loggedIn = true;
		} else {
			FacesMessage facesMessage = new FacesMessage("Login Failed: please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		}
		return navigateTo;
	}
	
	public void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
*/
	public List<User> getUsers() {
		users= userServiceImpl.mylist();
		return users;
	}





	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
	
	
	public User getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	




	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public static User getUSERCONNECTED() {
		return USERCONNECTED;
	}


	public static void setUSERCONNECTED(User uSERCONNECTED) {
		USERCONNECTED = uSERCONNECTED;
	}
	
	
	
	
	
}
