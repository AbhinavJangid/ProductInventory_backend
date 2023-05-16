package com.apk.productInventory.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apk.productInventory.entity.Product;
import com.apk.productInventory.service.ProductService;

@RestController
public class ProductController {

	
	@Autowired
	private ProductService  productService;
	
	
	@RequestMapping("/products")
	public List<Product> getAllProducts() {
		return  productService.getAllProducts();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public void addProduct(@RequestBody Product product) {
		 productService.addProduct(product);
	}
	
	
	  @RequestMapping("/products/{id}") public Optional<Product> getProduct(@PathVariable
	  String id) { return  productService.getProduct(id); }
	  
	  
	  
	  @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}") public
	  void updateProduct(@RequestBody Product product, @PathVariable String id) {
	   productService.updateProduct(id, product); }
	  
	  @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
	  public String deleteProduct(@PathVariable String id) {
	   productService.deleteProduct(id);
	  
	  return "Deleted"; }
	 
}
