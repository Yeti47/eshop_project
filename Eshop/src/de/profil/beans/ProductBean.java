package de.profil.beans;

import java.util.Collection;

import de.profil.Country;
import de.profil.Product;

/**
 * 
 * @author Alexander
 *
 */

public class ProductBean {
	
	// Fields
	
	private Collection<Product> _products = null;
	
	// Constructors
	public ProductBean(Collection<Product> products) {
		_products = products;
	}
	
	public ProductBean() {
	}

	// Methods
	public String htmlProductsList() {
		    
		String html = "";
		
		if(_products == null)
			return html;
			
		final String tab = "\t";
		final String newLine = "\n";

		html += "<ul id='products'>";

		for(Product c : _products) {
			html += "<li> <a href='ProductSite.jsp' title='";
			html += c.getName() + "'>" + c.getName();
			html += "</a></li>" + newLine;
		}
		
		return html + "</ul>" + newLine;
		
	}
	
	// Methods
	public String htmlProductsView() {
		    
		String html = "";
		
		if(_products == null)
			return html;
			
		final String tab = "\t";
		final String newLine = "\n";

		html += "<table id='productTable'>" + newLine;

		for(Product c : _products) {
			html += tab + 												"<tr class='product' border='1'>" + newLine;
			html += tab + tab + 									"<td class='product-image'>" + newLine;
			html += tab + tab + tab + 						"<td>" + newLine;
			html += tab + tab + tab + tab + 			"<a href='img/" + c.getImageName() + "' target='_blank'><img src='img/" + c.getImageName() + "' width='60' height='60' border='0' alt=''/></a>" + newLine;
			html += tab + tab + tab + 						"</td>" + newLine;
			html += tab + tab + "									<td class='product-title'>" + newLine;			
			html += tab + tab + tab + 						"<td>" + newLine;
			html += tab + tab + tab + tab + 			"<b>" + c.getName() + "</b><br />" + newLine;
			html += tab + tab + tab +       			c.getDescription() + "</td>" + newLine;
			html += tab + tab +             			"<td class='product-price'>" + newLine;			
			html += tab + tab + tab +  						"<div class='Pricing'>" + newLine;
			html += tab + tab + tab + tab + 			c.getPrice() + "<br />"+ newLine;
			html += tab + tab + tab + tab + 			c.getPackageFee() + "<br />" + newLine;
			html += tab + tab + tab + tab + 			"<form action='content/warenkorb.jsp' method='post'>" + newLine;
			html += tab + tab + tab + tab + tab + "<input type='number' name='quantity' value='1' />" + newLine;
			html += tab + tab + tab + tab + tab + "<button type='add' value='" + c.getId() + "'>Warenkorb</button>" + newLine;
			html += tab + tab + tab + tab + 			"</form>" + newLine;
			html += tab + tab + tab + 						"</div>" + newLine;
			html += tab + tab + 									"</td>" + newLine;
			html += tab + 												"</tr>" + newLine;
		}
		
		return html + "</table>" + newLine;
		
	}
		
}
