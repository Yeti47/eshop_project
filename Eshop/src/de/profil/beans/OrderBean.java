package de.profil.beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.profil.Bundle;
import de.profil.EshopDatabaseAccessor;
import de.profil.Order;
import de.profil.Product;

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
		
		if(quantityStr == null)
			return;
		
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
		
		if(p == null)
			p = dbAccess.fetchJoinedSingle(() -> new Bundle(), "bundle_id=?", new String[] { prodId });
		
		if(p != null) {
			
			for(int i = 0; i < quantity; i++) {

				_order.addProduct(p);
				
			}
			
		}
		
	}
	
	public boolean removeProductFromRequest() {
		
		if(_order == null || _request == null)
			return false;
		
		String prodIdStr = _request.getParameter("remove");
		String quantityStr = _request.getParameter("quantity");
		
		if(prodIdStr == null || quantityStr == null)
			return false;
		
		int prodId, quantity = 0;
		
		try {
			prodId = Integer.parseInt(prodIdStr);
			quantity = Integer.parseInt(quantityStr);
		}
		catch (NumberFormatException e) {
			return false;
		}
		
		for(int i = 0; i < quantity; i++) {
			
			Product prod = _order.getProductById(prodId);
			
			if(prod == null)
				break;
			
			_order.removeProduct(prod);
			
		}
	
		return true;
		
	}
	
	public void saveOrder() {
		
		_session.setAttribute("order", _order);
		
	}
	
	public String listShoppingCartItems(String cssClass) {
		
		final String tab = "\t";
		final String tab2 = "\t\t";
		final String newLine = "\n";
		
		String html = "<table ";
		
		if(cssClass != null)
			html += "class='" + cssClass + "' ";
		
		html += ">" + newLine;
		
		html += tab + "<tr>"  + newLine;
		html += tab2 + "<th>Position</th><th>Artikelnr.</th><th>Bezeichung</th><th>Einzelpreis</th><th>Anzahl</th><th>Verpackung</th><th>Gesamtpreis</th>"  + newLine;
		html += tab + "</tr>"  + newLine;
		
		if(_order != null) {
			
			int posId = 1;
			
			for(Order.Position p : _order.getPositions()) {
				
				double unitPrice = p.getProduct().getPrice();
				int quantity = _order.countProducts(p.getProduct().getId());
				double packagingFee = p.getProduct().getPackageFee() * quantity;
				double totalPrice = unitPrice * quantity + packagingFee;
				
				String quantityCell = quantity + "<br><form action='warenkorb.jsp' method='post'>" + newLine;
				quantityCell += tab + "<input type='number' name='quantity' value='1' style='width:20px;' /><button class='button button-positive' name='add' value='" + p.getProduct().getId()  +"' >+</button>" + newLine;
				quantityCell += "</form>" + newLine;
				quantityCell += "<form action='warenkorb.jsp' method='post'>" + newLine;
				quantityCell += tab + "<input type='number' name='quantity' value='1' style='width:20px;' /><button class='button button-negative' name='remove' value='" + p.getProduct().getId()  +"' >-</button>" + newLine;
				quantityCell += "</form>" + newLine;
				
				html += tab + "<tr class='position'>" + newLine;
				html += tab2 + "<td>" + posId + "</td><td>" + p.getProduct().getId() + "</td><td>" + p.getProduct().getName() + "</td>"
						+ "<td>" + String.format("%.2f EUR", unitPrice) + "</td><td>" + quantityCell + "</td><td>" + String.format("%.2f EUR",packagingFee) + "</td>"
						+ "<td>" + String.format("%.2f EUR", totalPrice) + "</td>" + newLine; 
				html += tab + "</td>" + newLine;
				posId++;
				
			}
			
		}
		
		html += "</table>" + newLine;
		
		return html;
		
	}
	
	public String shoppingCartSummary(String cssClassTable, String cssClassEmptyInfo, String cssClassSumInfo) {
		
		final String tab = "\t";
		final String tab2 = "\t\t";
		final String newLine = "\n";
		
		String html = "<table ";
		
		if(cssClassTable != null)
			html += "class ='" + cssClassTable + "' ";
		
		html += " >" + newLine;
		
		if(_order != null) {
			
			if(_order.countProducts() <= 0) {
				
				String infoParagraph = "<p " + newLine;
				
				if(cssClassEmptyInfo != null)
					infoParagraph += "class='" + cssClassEmptyInfo + "' ";
				
				infoParagraph += " >Ups, es sieht so aus, als sei Ihr Warenkorb noch leer. <br> Das ist furchtbar! So verdienen wir kein Geld! <br><br>";
				infoParagraph += "Tun Sie uns doch bitte den Gefallen und suchen Sie sich ein paar Produkte aus unserem vielfältigen Sortiment aus.</p>";
				
				return infoParagraph;
				
			}
			
			html += tab + "<tr>" + newLine;
			html += tab2 + "<th>Vorläufiger Rechnungsbetrag:</th>";
			
			double total = _order.getTotalPackagingFee() + _order.getTotalProductsPrice();
			
			html += tab2 + "<td>" + String.format("%.2f EUR", total) + "</td>" + newLine;
			html += tab +"</tr>" + newLine;
			html += tab + "<tr>" + newLine;
			
			html += tab2 + "<th>Davon Verpackungsgebühr:</th>" ;
			html += "<td>" + String.format("%.2f EUR", _order.getTotalPackagingFee()) + "</td>" + newLine;
			html += tab +"</tr>" + newLine;
			
			html += tab + "<tr> " + newLine;
			html += tab2 + "<td colspan='2' ";
			
			if(cssClassSumInfo != null)
				html += "class= '" + cssClassSumInfo + "' ";
			
			html += ">Eventuell fallen zusätzliche Kosten für Versand und Zahlungsabwicklung an. <br><br>"
					+ "Der endgültige Rechnungsbetrag wird am Ende des Bestellvorgangs angezeigt.</td>" + newLine;
			html += tab +"</tr>" + newLine;
			
		}
		
		html += "</table>" + newLine;
		
		return html;
		
	}
	
	public String productCounter(String cssClass) {
		
		String html = "<div ";
		
		if(cssClass != null)
			html += "class='" + cssClass + "' ";
		
		if(_order == null || _order.countProducts() < 1)
			html += " style='visibility: hidden;' ";
		
		html += " >\n";
		
		if(_order != null) {
			
			html += _order.countProducts();
			
		}
		
		html += "</div>";
		
		return html;
		
	}

}
