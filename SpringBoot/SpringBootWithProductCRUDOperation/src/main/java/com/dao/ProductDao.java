package com.dao;

import java.util.List;

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
	
	public List<Product> getAllProductDetails() {
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("select p from Product p");
		List<Product> listOfEmp = qry.getResultList();
		return listOfEmp;
	
	}
	
	public int storeProduct(Product pro) {
		
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		tran.begin();
			manager.persist(pro);
		tran.commit();
		
		Product p = manager.find(Product.class, pro.getPid());
		if(p==null) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public int deleteProductInfo(int id) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		
		Product p	= manager.find(Product.class, id);			// primary key	if record is available automatically convert record to object 
		if(p==null) {															// select * from product where pid =1
			return 0;
		}else {
			tran.begin();
						manager.remove(p);						// delete from product where pid = 1;
			tran.commit();
			return 1;
		}
	}

	public int updateProductInfo(Product pp) {
		
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		
		Product pro = manager.find(Product.class, pp);			// primary key
		if(pro==null) {
			return 0;
		}else {
			tran.begin();
						pro.setPrice(pro.getPrice()+pp.getPrice());
						manager.merge(pro);									//update price using pid column 
			tran.commit();
			return 1;
		}
	}


}
