package com.task.webapp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.webapp.hibernate.App;

import com.task.webapp.hibernate.model.Article;

@RestController
public class WebappController 
{
	App app = new App();
	 
	//@CrossOrigin(origins = "/**")
	/*GET ALL ARTICLES*/
    @RequestMapping(value = "/products", method=RequestMethod.GET)
    public ResponseEntity<Object> getAll() 
    {
	   return new ResponseEntity<>(app.getAllArticles(), HttpStatus.OK);   //productRepo.values()
	}
	
    /*POST AN ARTICLE*/
	//@CrossOrigin(origins = "/**")
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Article article) 
	{
	   app.postArticle(article.getTitle(), article.getSummary(), article.getPhotoURL(), article.getPrice());
	   return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}
	
	/*GET BY ID*/
	//@CrossOrigin(origins = "*")
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getById(@PathVariable("id") int id)
	{
		return new ResponseEntity<>(app.getArticleById(id), HttpStatus.OK);
	}
	
	/*UPDATE AN ARTICLE*/
	//@CrossOrigin(origins = "*")
	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestBody Article article)
	{
		app.updateArticle(id, article.getTitle() , article.getSummary(), article.getPhotoURL(), article.getPrice());
		return new ResponseEntity<>("Article is updated successsfully", HttpStatus.OK);
	}
	
	/*DELETE AN ARTICLE*/
	//@CrossOrigin(origins = "*")
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") int id) 
	{ 
	    app.deleteArticle(id);
	    return new ResponseEntity<>("Article is deleted successsfully", HttpStatus.OK);
	}
}
