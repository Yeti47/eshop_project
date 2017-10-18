package de.profil;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	// Fields
	
	private int _id = -1;
	private Customer _customer = null;
	private Receiver _receiver = null;
	private List<Product> _products = new ArrayList<Product>();
	private Payment _payment = null;
	
	// Constructors
	
	// Getters / Setters
	
	public int getId() {
		return _id;
	}

	public Customer getCustomer() {
		return _customer;
	}

	public void setCustomer(Customer customer) {
		_customer = customer;
	}

	public Receiver getReceiver() {
		return _receiver;
	}

	public void setReceiver(Receiver receiver) {
		_receiver = receiver;
	}

	public Payment getPayment() {
		return _payment;
	}

	public void setPayment(Payment payment) {
		_payment = payment;
	}
	
	// Methods
	
	public Product[] getAllProducts() {
		
		return _products.toArray(new Product[0]);
		
	}
	
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
