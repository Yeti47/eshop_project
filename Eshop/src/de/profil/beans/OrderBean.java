package de.profil.beans;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.profil.EshopDatabaseAccessor;
import de.profil.Order;
import de.profil.Product;
import de.profil.WebUtility;

public class OrderBean {
	
	// Fields
	
	private HttpSession _session = null;
	private HttpServletRequest _request = null;
	private Order _order = null;
	
	// Constructors
	
	public OrderBean(HttpSession session, HttpServletRequest request) {
		
		_session = session;
		_request = request;
		
	}
	
	public OrderBean() {
		
	}

	// Getters / Setters
	
	public HttpSession getSession() {
		return _session;
	}

	public HttpServletRequest getRequest() {
		return _request;
	}

	public void setRequest(HttpServletRequest request) {
		_request = request;
	}

	public Order getOrder() {
		return _order;
	}

	public void setSession(HttpSession session) {
		_session = session;
	}
	
	// Methods
	
	public boolean initializeOrder() {
		
		if(_session == null)
			return false;
		
		Order order = null;
		
		try {
			
			order = (Order)_session.getAttribute("order");
			
		}
		catch (ClassCastException e) {
			
		}
		
		if(order != null)
			_order = order;
		else
			_order = new Order();
		
		return _order != null;
		
		
	}
	
	public void addProductsFromRequest() {
		
		if(_order == null || _request == null)
			return;
		
		String prodId = _request.getParameter("add");
		String quantityStr = _request.getParameter("quantity");
		
		int quantity = 0;
		
		try {
			quantity = Integer.parseInt(quantityStr);
		}
		catch (NumberFormatException e) {
			
		}
		
		if(prodId == null || quantity < 1)
			return;			
		
		EshopDatabaseAccessor dbAccess = new EshopDatabaseAccessor();
		
		Product p = dbAccess.fetchJoinedSingle(() -> new Product(), "prod_id=?", new String[] { prodId });
		
		for(int i = 0; i < quantity; i++) {

			_order.addProduct(p);
			
		}
		
	}
	
	public void saveOrder() {
		
		_session.setAttribute("order", _order);
		
	}

}
