package model;

import java.util.List;

import javax.xml.ws.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class CartBean {
	
	public void addToCart(Products product, String userId, int orderQuantity){
		
		String productName;
		int productId;
		float unitPrice, netAmount;
		productName = product.getProductName();
		productId = product.getProductId();
		unitPrice = product.getUnitPrice();
		netAmount = unitPrice * orderQuantity;
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Cart cart = (Cart) context.getBean("cart");
		
		System.out.println("Inside Bean : " + userId);
		
		cart.setUserId(userId);
		cart.setProductId(productId);
		cart.setProductName(productName);
		cart.setUnitPrice(unitPrice);
		cart.setOrderQuantity(orderQuantity);
		cart.setNetAmount(netAmount);
		
		session.save(cart);
		
		tx.commit();
		session.close();
		factory.close();
	}
	
	public List<Cart> getCart(String userId){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Cart WHERE userId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		List<Cart> results = query.getResultList();
		tx.commit();
		session.close();
		factory.close();
		return results;
	}

}
