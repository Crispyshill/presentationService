package domain;

import java.math.BigDecimal;
import java.util.Objects;


public class Product {
	
	private String productId;
	private String productName;
	private String description;
	private BigDecimal price;
	private int inventory;
	
	public Product(String productId, String productName, String description, BigDecimal price, int inventory) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
	}
	
	
	
	public Product() {
		
	}
	
	
	

	public int getInventory() {
		return inventory;
	}



	public void setInventory(int inventory) {
		this.inventory = inventory;
	}



	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productId, other.productId);
	}
	
	
}
