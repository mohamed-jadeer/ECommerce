package model;

import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProductsBean {

	public List<Products> getAllProducts(){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Products";
		Query query = session.createQuery(hql);
		//System.out.println("Before Query");
		List<Products> results = query.getResultList();
		//System.out.println("After Query");
		//System.out.println("Size of the result array is : " + results.size());
//		ListIterator<Products> litr = null;
//		litr = results.listIterator();
		tx.commit();
		session.close();
		factory.close();
//		while(litr.hasNext()){
//			Products product = litr.next();
//			System.out.println(" " + product.getProductName() + " " + product.getUnitPrice() +" ");
//		}
		return results;
	}
	
	public boolean checkStock(int productId , int orderQuantity){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Products WHERE product_id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, productId);
		List<Products> results = query.getResultList();
		tx.commit();
		session.close();
		factory.close();
		if (results.get(0).getStockInHand() > orderQuantity){
			return true;
		}
		else
			return false;
	}
	
	public Products getProductbyId(String productId){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Products WHERE product_id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, productId);
		List<Products> results = query.getResultList();
		tx.commit();
		session.close();
		factory.close();
		return results.get(0);
	}
	
}
