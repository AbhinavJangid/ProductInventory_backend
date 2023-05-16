
  package com.apk.productInventory.repository;
  
  import org.springframework.data.jpa.repository.JpaRepository;

import com.apk.productInventory.entity.Product;
  
  public interface ProductRepository extends JpaRepository<Product, String> {
  
  }
 