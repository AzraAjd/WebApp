package com.task.webapp.authentication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		
		//logic to get user from db
		com.task.webapp.hibernate.model.User user = app.findUser(userName);
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
		return new User(user.getUserName(), user.getPassword(),  authorities
		);
	}
    
	private List<GrantedAuthority> buildUserAuthority(String role){
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authority;
	}

}






