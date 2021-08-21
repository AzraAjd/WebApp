package com.task.webapp.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.task.webapp.authentication.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtFilter jwtfilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("http://localhost:3000")
	                    .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
	                    .allowedHeaders("*");                    
	           
	        }
	    };
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/auth").permitAll()
			.antMatchers(HttpMethod.GET, "/products/**").permitAll()
			.antMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
		    .antMatchers(HttpMethod.PATCH, "/products/**").hasRole("ADMIN")
		    .antMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
http.cors();
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
}
