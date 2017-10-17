package de.profil.beans;

import java.util.Collection;

import de.profil.Payment;

public class PaymentBean {
	
	// Fields
	
	private Collection<Payment> _payments = null;
	
	// Constructors
	
	public PaymentBean() {
		
	}
	
	public PaymentBean(Collection<Payment> payments) {
		
		_payments = payments;
		
	}
	
	// Methods
	
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

}
