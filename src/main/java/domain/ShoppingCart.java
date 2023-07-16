package domain;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShoppingCart {

	@Id	
	private String username;
	
	private HashMap<Product, Integer> products;
	
	
	public ShoppingCart() {
		
	}
	
	
	
	
	public ShoppingCart( String userId, HashMap<Product, Integer> products) {
		super();
		this.username = userId;
		this.products = products;
	}




	public String getUserId() {
		return username;
	}




	public void setUserId(String userId) {
		this.username = userId;
	}




	
	public HashMap<Product, Integer> getProducts() {
		return products;
	}
	public void setProducts(HashMap<Product, Integer> products) {
		this.products = products;
	}
	
	
}
