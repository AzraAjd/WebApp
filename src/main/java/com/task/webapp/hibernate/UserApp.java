package com.task.webapp.hibernate;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.task.webapp.hibernate.model.User;
import com.task.webapp.hibernate.util.HibernateUtil;

public class UserApp {
	
	/*GET USER BY ID*/
	public User getUserById(int id)
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession())  
		{
           User user =  (User) session.get(User.class, id);
           session.close();	
           return user;
           
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	/*GET RECORD WITH QUERY (FOR LOGIN)*/
	public User findUser(String userName)
	{
		try (Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			Query query=session.createQuery("from User where user_name=:username");
			  query.setParameter("username", userName);
			  User user=(User)query.uniqueResult();
			  if(user!=null) {
			   return user;
			  }else {
			   System.out.println("username and password are not valid");
			   return null;
			  }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
	}
	
	/*POST A USER*/
	public void postUser(String userName, String email, String password, boolean admin)
	{
		User user = new User(userName,email, password, admin);
		try (Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
            session.save(user);
            session.beginTransaction().commit();
            session.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/*
	 * DELETE A USER
	 * */
	public void deleteUser(int id)
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession())
	     {
			session.beginTransaction();
			
			User user = (User) session.get(User.class, id);
	        session.delete(user);
	        session.getTransaction().commit();
	        session.close();
			 
	     }catch (Exception e) {
	            e.printStackTrace();
	     }
	}
	
	
}
