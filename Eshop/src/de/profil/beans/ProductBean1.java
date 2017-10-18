package de.profil.beans;

import java.util.Collection;

import de.profil.Country;
import de.profil.Product;

/**
 * 
 * @author Alexander Herrfurth
 *
 */

public class ProductBean1 {
	
	// Fields
	
	private Collection<Product> _products = null;
	
	// Constructors
	
	public ProductBean1(Collection<Product> products) {
		
		_products = products;
		
	}
	
	public ProductBean1() {
		
	}

	// Methods
	
	public String htmlSelect(String name, int size, String defaultOption, String cssClass, String cssId) {
/*	<a href="ProductSite">  Produktname	</a> */
			    
		String html = "";
		
		if(_products == null)
			return html;
		
		size = Math.max(1, size);
		
		final String tab = "\t";
		final String newLine = "\n";

		html += "<ul id='products'>";

		for(Product c : _products) {
			html += "<li> <a href='ProductSite.jsp' title='";
			html += c.getName() + "'>" + c.getName();
			html += "</a></li><hr/>" + newLine;
		}
		
		return html + "</ul>" + newLine;
		
	}
	
	public String htmlSelect(String name, int size, String defaultOption, String cssClass) {
		return htmlSelect(name, size, defaultOption, cssClass, null);
	}
	
	public String htmlSelect(String name, int size, String defaultOption) {
		return htmlSelect(name, size, defaultOption, null, null);
	}
		
}
