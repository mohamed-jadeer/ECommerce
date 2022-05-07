package model;

import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class CustomersBean {
	
	public boolean checkUserId(String userId){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Customers AS C WHERE C.userId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		List<Customers> results = query.getResultList();
		tx.commit();
		session.close();
		factory.close();
		if(results.size() == 0)
			return true;
		else
			return false;
	}
	
	public void addCustomer(Customers customer){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(customer);
		tx.commit();
		session.close();
		factory.close();
	}
	
	public String authenticateUser(String userId, String password){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Customers AS C WHERE C.userId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		List<Customers> results = query.getResultList();
		if(results.size() == 0){
			tx.commit();
			session.close();
			factory.close();
			return "userid";
		}
		else{
			hql = "FROM Customers AS C WHERE C.userId = ? AND C.password = ?";
			query = session.createQuery(hql);
			query.setParameter(0, userId);
			query.setParameter(1, password);
			results = query.getResultList();
			tx.commit();
			session.close();
			factory.close();
			if(results.size() == 0){
				return "password";
			}
			else{
				return "success";
			}
		}
		
	}

}
