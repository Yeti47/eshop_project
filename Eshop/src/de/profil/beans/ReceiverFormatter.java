package de.profil.beans;

import de.profil.Address;
import de.profil.Receiver;
import de.profil.WebUtility;

public class ReceiverFormatter {
	
	// Fields
	
	private Receiver _receiver = null;
	
	// Constructors
	
	public ReceiverFormatter(Receiver receiver) {
		
		_receiver = receiver;
		
	}
	public ReceiverFormatter() {
			
	}
	
	// Methods
	
	public String htmlList(String cssClass) {
		
		String html = "";
		
		if(_receiver == null)
			return html;
		
		final String tab = "\t";
		final String newLine = "\n";
		
		html += "<ul " + (cssClass != null ? " class='"+cssClass+"' " : " ") + " >" + newLine;
		html += tab + "<li>" + _receiver.getTitleText() + "</li>" + newLine;
		html += tab + "<li>" + WebUtility.escapeHtmlChars(_receiver.getFirstname()) + " " + WebUtility.escapeHtmlChars(_receiver.getName()) + "</li>" + newLine;
		
		Address address = _receiver.getAddress();
		
		if(address != null) {
			
			html += tab + "<li>" + WebUtility.escapeHtmlChars(address.getStreet()) + " " + WebUtility.escapeHtmlChars(address.getHouseNumber()) + "</li>" + newLine;
			html += tab + "<li>" + WebUtility.escapeHtmlChars(address.getPostCode()) + " " + WebUtility.escapeHtmlChars(address.getCity()) + "</li>" + newLine;
			
			if(address.getCountry() != null)
				html += tab + "<li>" + address.getCountry().getName() + "</li>" + newLine;
			
		}
		
		html += "</ul>" + newLine;
		
		return html;
		
	}
	

}
