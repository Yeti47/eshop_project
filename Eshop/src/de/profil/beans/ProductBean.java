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
			
		final String tab1 = "\t";
		final String tab2 = "\t\t";
		final String tab3 = "\t\t\t";
		final String tab4 = "\t\t\t\t";
		final String tab5 = "\t\t\t\t\t";
		final String newLine = "\n";

		html += "<table id='productTable'>" + newLine;

		for(Product c : _products) {
			html += tab1 + "<tr class='product'>" + newLine;
			html += tab2 + "<td class='product-image'>" + newLine;
			html += tab3 + "<td>" + newLine;
			html += tab4 + "<a href='img/" + c.getImageName() + "' target='_blank'><img src='img/" + c.getImageName() + "' width='60' height='60' border='0' alt=''/></a>" + newLine;
			html += tab3 + "</td>" + newLine;
			html += tab2 + "<td class='product-title'>" + newLine;			
			html += tab3 + "<td>" + newLine;
			html += tab4 + "<b>" + c.getName() + "</b><br />" + newLine;
			html += tab4 + c.getDescription() + newLine;
			html += tab3 + "</td>" + newLine;
			html += tab2 + "<td class='product-price'>" + newLine;			
			html += tab3 + "<div class='Pricing'>" + newLine;
			html += tab4 + c.getPrice() + "<br />"+ newLine;
			html += tab4 + c.getPackageFee() + "<br />" + newLine;
			html += tab4 + "<form action='content/warenkorb.jsp' method='post'>" + newLine;
			html += tab5 + "<input type='number' name='quantity' value='1' />" + newLine;
			html += tab5 + "<button name='add' value='" + c.getId() + "'>in den Warenkorb</button>" + newLine;
			html += tab4 + "</form>" + newLine;
			html += tab3 + "</div>" + newLine;
			html += tab2 + "</td>" + newLine;
			html += tab1 + "</tr>" + newLine;
			
		}
		
		return html + "</table>" + newLine;
		
	}
		
}
