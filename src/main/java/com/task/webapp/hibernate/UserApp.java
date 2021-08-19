package com.task.webapp.hibernate;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.task.webapp.hibernate.model.User;
import com.task.webapp.hibernate.util.HibernateUtil;

public class UserApp {
	
	/*GET USER BY ID*/
	public User getUserById(int id)
	{
		System.out.println("im here");
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
	
	/*POST A USER*/
	public void postUser(String userName, String email, String password, boolean admin)
	{
		User user = new User(userName,email, password, admin);
		System.out.println(user.getUserName());
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
