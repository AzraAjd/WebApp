package com.task.webapp;

import java.util.List;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.task.webapp.hibernate.App;
import com.task.webapp.hibernate.model.Article;

@SpringBootApplication
public class Webapp {

	public static void main(String[] args) {
		SpringApplication.run(Webapp.class, args);
		
		App app = new App();
		
		/*SAVE AN ARTICLE*/
		
		//app.postArticle("newBMX", "a bicycle", "photo.com", 400);
		
		/*GET ALL ARTICLES*/
		
		/*List<Article> articles = new ArrayList<Article>();
		articles = app.getAllArticles();
		for (Article article : articles)
		{
			System.out.println(article.getTitle());
		}*/
		
		/*GET ARTICLE BY ID*/
		
		/*Article article = app.getArticleById(3);
		System.out.println(article.getTitle());*/
		
		/*UPDATE ARTICLE*/
		//app.updateArticle(3, "mali bemix", "malo biciklo", "link.com", 150);
		
		/*UPDATE ON ONE COLUMN*/
		/*Article article = app.getArticleById(3);
		app.updateArticle(4, "Norco", article.getSummary(), article.getPhotoURL(), article.getPrice());
		*/
		
		/*DELETE AN ARTICLE*/
		//app.deleteArticle(2);  
	}
}
