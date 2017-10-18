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
	
	public String htmlBuildProductsList() {
		    
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
		
}
