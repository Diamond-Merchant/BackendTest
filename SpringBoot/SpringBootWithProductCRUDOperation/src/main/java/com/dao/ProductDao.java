package com.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Product;

@Repository
public class ProductDao {
@Autowired
EntityManagerFactory emf;

		public List<Product> getAllProduct() {
			EntityManager manager = emf.createEntityManager();
			Query qry = manager.createQuery("select p from product p");
			List<Product> listOfprd = qry.getResultList();
			return listOfprd;
		
		}

	
	
		public int storeProduct(Product pp) {
			EntityManager manager =  emf.createEntityManager();
			EntityTransaction tran = manager. getTransaction();
			tran.begin();
				manager.persist(pp);
			tran.commit();
			Product p = manager.find(Product.class,pp.getPid());
			if(p==null) {
				return 0;
			}else {
				return 1;
			}
		}
		
		public int deleteProduct(int pid) {
			EntityManager manager =  emf.createEntityManager();
			EntityTransaction tran = manager. getTransaction();
			Product p = manager.find(Product.class, pid);
			
			if(p==null) {
				return 0;
			}else {
				tran.begin();
					manager.remove(p);
				tran.commit();
				return 1;
			}
		}
		
		public int updateProduct(Product pp) {
			EntityManager manager =  emf.createEntityManager();
			EntityTransaction tran = manager. getTransaction();
			Product p =manager.find(Product.class,pp.getPid());
			if(p==null) {
				return 0;
			}else {
				tran.begin();
				    p.setPrice(p.getPrice()+pp.getPrice());
				    manager.merge(p);
				    tran.commit();
				    return 1;
			}
		}



		


		
}
