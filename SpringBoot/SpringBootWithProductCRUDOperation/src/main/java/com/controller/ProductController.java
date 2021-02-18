package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Product;
import com.service.ProductService;

/*	@RequestMapping(value="product",method=RequestMethod.GET)
	@GetMapping(value = "info")
	@PostMapping(value="info")
	@PutMapping(value="info")
	@DeleteMapping(value="info")
	
*/	

@RestController
@RequestMapping(value="product")
public class ProductController {

	@Autowired
	ProductService ps;
	Product p;
	@GetMapping(value="info")
public String simpleMessage() {
	
	return "Welcome To Rest Product Controller";
}
@GetMapping(value="display")
public List<Product> getAllProduct()
{
	return ps.getAllProduct();
}

//http://localhost:9090/product/allProduct
	@GetMapping(value = "allProduct",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProductDetails() {
		return ps.getAllProduct();
	}
	
	// http://localhost:9090/product/allProductData 
	
	@GetMapping(value = "allProductData",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProductDetailsFromSpringData(){
			return ps.getAllProductFormSpringData();
	}
	

	
	// http://localhost:9090/product/storeProduct
	@PostMapping(value = "storeProductData",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String storeProductSpringData(@RequestBody Product pp) {
		return ps.storeProduct(pp);
	}
	

	// http://localhost:9090/product/updateProduct
	@PutMapping(value = "updateProduct",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateProduct(@RequestBody Product pp) {
			return ps.updateProduct(pp);
	}
	
	@PutMapping(value = "updateProductData",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateProductSpringData(@RequestBody Product pp) {
			return ps.updateProduct(pp);
	}
	
	// http://localhost:9090/product/deleteProduct/100
	@DeleteMapping(value = "deleteProduct/{pid}")
	public String deleteProduct(@PathVariable("pid") int pid) {
			return ps.deleteProduct(pid);
	}
	
	@DeleteMapping(value = "deleteProductData/{pid}")
	public String deleteProductSpringData(@PathVariable("pid") int pid) {
			return ps.deleteProduct(pid);
	}
}


