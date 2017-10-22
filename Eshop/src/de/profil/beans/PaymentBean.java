package de.profil.beans;

import de.profil.Payment;

/**
 * 
 * @author Alexander Herrfurth
 * 
 */
public class PaymentBean {
	
	// Fields
	
	private Payment[] _payments = null;
	
	// Constructors
	
	public PaymentBean() {
		
	}
	
	public PaymentBean(Payment[] payments) {
		
		_payments = payments;
		
	}
	
	// Getters / Setters
	
	public void setPayments(Payment[] payments) {
		
		_payments = payments;
		
	}
	
	// Methods
	
	/**
	 * 
	 * @deprecated Ersetzt durch {@link SelectBoxBuilder}.
	 * @return
	 */
	@Deprecated
	public String htmlSelect(String name, int size, String defaultOption, String cssClass, String cssId) {
		
		String html = "";
		
		if(_payments == null)
			return html;
		
		size = Math.max(1, size);
		
		final String tab = "\t";
		final String newLine = "\n";
		
		html += "<select ";
		
		if(cssClass != null)
			html += "class='"+cssClass + "' ";
		
		if(cssId != null)
			html += "id='" + cssId + "' ";
		
		html += "name='" + name + "' size='" + size + "'>" + newLine;
		
		if(defaultOption != null)
			html += tab + "<option>" + defaultOption + "</option>" + newLine;
		
		for(Payment p : _payments) {
			
			html += tab + "<option>" + p.getName() + "</option>" + newLine;
			
		}
		
		return html + "</select>" + newLine;
		
	}
	
	public String htmlList(String cssClass) {
		
		String html = "<ul ";
		
		if(cssClass != null)
			html += " class='" + cssClass + "' ";
		
		html += " >\n";
		
		if(_payments != null) {
			
			for(Payment p : _payments) {
				
				html += "\t<li>" + p.getName() + "</li>\n";
				
			}
			
		}
		
		html += "</ul>\n";
		
		return html;
		
	}

}
