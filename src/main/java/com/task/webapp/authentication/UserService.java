package com.task.webapp.authentication;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task.webapp.hibernate.UserApp;

@Service
public class UserService  implements UserDetailsService{

	UserApp app = new UserApp();
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		com.task.webapp.hibernate.model.User user = app.findUser(userName);
		//Logic to get the user from the Database
		
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

}
