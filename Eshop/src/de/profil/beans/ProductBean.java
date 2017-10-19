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
			html += "\t<tr class='product' border='1'>" + newLine;
			html += "\t\t<td class='product-image'>" + newLine;
			html += "\t\t\t<td>" + newLine;
			html += "\t\t\t\t<img src='img/" + c.getImageName() + "' width='60' height='60' border='0' alt='' />" + newLine;
			html += "\t\t\t</td>" + newLine;
			html += "\t\t<td class='product-title'>" + newLine;			
			html += "\t\t\t<td>\n\t\t\t\t<b>" + c.getName() + "</b>\n\t\t\t<br />" + c.getDescription() + "</td>" + newLine;
			html += "\t\t<td class='product-price'>" + newLine;			
			html += "\t\t\t<div class='Pricing'>\n\t\t\t\t" + c.getPrice() + "<br />\t\t\t\t" + c.getPackageFee() + "<br />" + newLine;
			html += "<input name='m_" + c.getId() + "' class='ab-form-control input-sm' value='1' size='1'/>";
			html += "<input type='submit' value='Warenkorb' name='pb" + c.getId() + "' class='ContentButton' />";
			html += "\t\t\t</div>" + newLine;
			html += "\t\t</td>\n\t</tr>" + newLine;
		}
		
		return html + "</table>" + newLine;
		
	}
		
}
