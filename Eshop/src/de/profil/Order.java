package de.profil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.yetibyte.snowstorm.DatabaseAccessor;
import net.yetibyte.snowstorm.DatasetAttributes;
import net.yetibyte.snowstorm.IDatabaseWritable;

public class Order implements IDatabaseWritable {
	
	// Nested Class
	
	public class Position implements IDatabaseWritable {

		private static final String POS_TABLE_NAME = "orders";
		
		private int _index;
		private Product _product;
		
		public Position(int index, Product product) {
			_index = index;
			_product = product;
		}
		
		public Product getProduct() {
			return _product;
		}
		
		@Override
		public String getTableName() {

			return POS_TABLE_NAME;
		}

		@Override
		public DatasetAttributes writeToDatabase() {

			DatasetAttributes dsAttributes = new DatasetAttributes();
			dsAttributes.setAttribute("order_id", _id);
			dsAttributes.setAttribute("pos_id", _index);
			dsAttributes.setAttribute("quantity", countProducts(_product.getId()));
			
			return dsAttributes;
			
		}

	}
	
	// Constants
	
	private static final String TABLE_NAME = "orders";

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

	@Override
	public String getTableName() {
		
		return TABLE_NAME;
	}

	@Override
	public DatasetAttributes writeToDatabase() {
		
		if(_customer == null || _receiver == null || _payment == null)
			return null;
		
		DatasetAttributes dsAttributes = new DatasetAttributes();
		dsAttributes.setAttribute("custom_id", _customer.getCustom_id());
		dsAttributes.setAttribute("rec_id", _receiver.getCustom_id());
		
		Date d = Date.valueOf(LocalDate.now());
		
		// TODO: snowstORM unterstützt bislang nur das Schreiben von Strings, Typen-Unterscheidung einbauen!
		// Evtl reicht die Benutzung von statement.setObject() anstelle von statement.setString(), je nach Treiber
		dsAttributes.setAttribute("order_date", d);
		
		
		return null;
	}
	
	public boolean insertPositionsIntoDatabase(DatabaseAccessor dbAccessor) {
		
		if(dbAccessor == null)
			return false;
		
		int previousProdId = -1;
		int posId = 1;
		
		for(Product p : _products) {
			
			if(p.getId() != previousProdId) {
				
				dbAccessor.insert(new Position(posId, p));
				posId++;
				
			}
			
		}
		
		return true;
		
	}
	
	public Collection<Position> getPositions() {
		
		List<Position> positions = new ArrayList<Position>();
		
		int previousProdId = -1;
		int posId = 1;
		
		_products.sort((p, q) -> p.getId() - q.getId());
		
		for(Product p : _products) {
			
			if(p.getId() != previousProdId) {
				
				positions.add(new Position(posId, p));
				posId++;
				previousProdId = p.getId();
				
			}
			
		}
		
		return positions;
		
	}
	
	

}
