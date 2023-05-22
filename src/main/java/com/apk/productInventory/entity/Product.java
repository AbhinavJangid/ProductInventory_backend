package com.apk.productInventory.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


//@jakarta.persistence.Entity
@Entity
public class Product {

	public Product() {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	public Product(String id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	@Id
	private String id;
	private String title;
	private String description;
	
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
