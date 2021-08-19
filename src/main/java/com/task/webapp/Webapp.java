package com.task.webapp;

import java.util.List;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.task.webapp.hibernate.UserApp;


@SpringBootApplication
public class Webapp {

	public static void main(String[] args) {
		SpringApplication.run(Webapp.class, args);
	}
}
