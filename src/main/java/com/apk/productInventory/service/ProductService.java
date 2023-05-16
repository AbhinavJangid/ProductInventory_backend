package com.apk.productInventory.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apk.productInventory.entity.Product;
import com.apk.productInventory.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	
	  private List<Product> products = new ArrayList<>(Arrays.asList(
			  	new Product( "one","aphone", "okay"), 
			  	new Product( "two","pphone", "okay"),
			  	new Product( "three","kphone", "okay")
			  
			  ));
	 
	
	
	public List<Product> getAllProducts(){
		//return products;
	
		
		  List<Product> products = new ArrayList<>();
		  productRepository.findAll().forEach(products::add); return products;
		 
		
	}
	
	public void addProduct(Product product) {
		//products.add(product);
		productRepository.save(product); 
		
	}
	
	
	  public Product getProduct(String id) { 
		  return (products.stream().filter( p -> p.getId().equals(id)).findFirst().get()); 
		  
	  }
	  
	  
	  
	  public void updateProduct(String id, Product product) { for( int i = 0; i <
	  products.size(); i++) { Product p = products.get(i); if(p.getId().equals(id))
	  { products.set(i, product); return; }
	  
	  }
	  
	  }
	  
	  public void deleteProduct(String id) { products.removeIf(p ->
	  p.getId().equals(id));
	  
	  }
	 
}
