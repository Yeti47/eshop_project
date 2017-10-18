package de.profil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
	
	// Fields
	
	private int _id = -1;
	private Customer _customer = null;
	private Receiver _receiver = null;
	private List<Product> _products = new ArrayList<Product>();
	private Payment _payment = null;
	
	// Constructors
	
	// Getters / Setters
	
	// Methods
	
	public void addProduct(Product product) {
		
		_products.add(product);
		
	}
	
	public boolean removeProduct(Product product) {
		
		if(!_products.contains(product))
			return false;
		
		return _products.remove(product);
		
	}
	
	public Product[] getProductsById(int prodId) {
		
		List<Product> prods = new ArrayList<Product>();
		
		for(Product p : _products) {
			
			if(p.getId() == prodId)
				prods.add(p);
			
		}
		
		return prods.toArray(new Product[0]);
		
	}
	
	public int countProducts(int prodId) {
		
		int count = 0;
		
		for(Product p : _products) {
			
			if(p.getId() == prodId)
				count++;
			
		}
		
		return count;
		
	}
	
	public double getTotalProductsPrice() {
		
		double total = 0;
		
		for(Product p : _products)
			total += p.getPrice();
		
		return total;
		
	}
	
	public double getTotalProductPrice(int prodId) {
		
		double total = 0;
		
		for(Product p : getProductsById(prodId)) {
			
			total += p.getPrice();
			
		}
			
		return total;
		
	}

}
