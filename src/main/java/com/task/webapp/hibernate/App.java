package com.task.webapp.hibernate;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.task.webapp.hibernate.model.Article;
import com.task.webapp.hibernate.util.HibernateUtil;


public class App
{
	Transaction transaction = null;
	
	/*GET ARTICLE BY ID */
	public Article getArticleById(int id)
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession())  
		{
           Article article =  (Article) session.get(Article.class, id);
           session.close();	
           return article;
           
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
		
	}
	
	/*POST AN ARTICLE*/
	public void postArticle(String title, String summary, String photo_link, float price)
	{
		Article article = new Article(title, summary, photo_link, price);
		try (Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
            session.save(article);
            session.beginTransaction().commit();
            session.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/*GET ALL ARTICLES*/
	public List<Article> getAllArticles(){       
	    try(Session session = HibernateUtil.getSessionFactory().openSession())
	    {
	    	// create Criteria
	        CriteriaQuery<Article> criteriaQuery = session.getCriteriaBuilder().createQuery(Article.class);
	        criteriaQuery.from(Article.class);

	        List<Article> contacts = session.createQuery(criteriaQuery).getResultList();
	        session.close();

	        return contacts;
	    } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/*UPDATE AN ARTICLE*/
	public void updateArticle(int id, String title, String summary, String photoURL, float price)
	{
		Article article = new Article(title, summary, photoURL, price);
	    
		 try(Session session = HibernateUtil.getSessionFactory().openSession())
	     {
			 article.setId(id);
			 
			 session.beginTransaction();
			 session.update(article);
			 session.getTransaction().commit();
			 session.close();
			 
		 } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	/*DELETE AN ARTICLE*/
	public void deleteArticle(int id)
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession())
	     {
			session.beginTransaction();
			
			Article article = (Article) session.get(Article.class, id);
	        session.delete(article);
	        session.getTransaction().commit();
	        session.close();
	        System.out.println("article deleted");
			 
	     }catch (Exception e) {
	            e.printStackTrace();
	     }
	}
}
