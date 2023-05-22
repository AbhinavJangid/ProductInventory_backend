package com.apk.productInventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"com.apk.productInventory.repository"})
//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = "com.apk.productInventory.repository")
//@EntityScan("com.apk.productInventory.entity")


@SpringBootApplication
public class ProductInventoryBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductInventoryBackendApplication.class, args);
		System.out.println("Something");
		
	}

}
