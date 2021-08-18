package com.task.webapp.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.task.webapp.hibernate.model.Article;


@RestController
public class WebappController 
{
	 private static Map<String, Article> productRepo = new HashMap<>();
	 static {
		 Article article = new Article();
		 article.setId(5);
		 article.setTitle("bemmigz");
		 article.setSummary("ok");
		 article.setPhotoURL("url");
		 article.setPrice(120);
	     productRepo.put(Integer.toString(article.getId()), article);
		      
	     Article article2 = new Article();
	     article2.setId(6);
	     article2.setTitle("anotherbemmigz");
	     article2.setSummary("ok");
		 article2.setPhotoURL("url");
		 article2.setPrice(120);
	     productRepo.put(Integer.toString(article2.getId()), article2);
	 }
	 
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() 
    {
	     return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);   
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Article article) 
	{
	   productRepo.put(Integer.toString(article.getId()), article);
	   return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Article article) { 
	      productRepo.remove(id);
	      article.setId(Integer.valueOf(id));
	      productRepo.put(id, article);
	      return new ResponseEntity<>("Article is updated successsfully", HttpStatus.OK);
	  }   
	 @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
	      productRepo.remove(id);
	      return new ResponseEntity<>("Article is deleted successsfully", HttpStatus.OK);
	 }
}
