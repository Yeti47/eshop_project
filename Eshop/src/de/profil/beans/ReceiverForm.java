package de.profil.beans;

import javax.servlet.http.HttpServletRequest;

import de.profil.Address;
import de.profil.Country;
import de.profil.Customer;
import de.profil.Receiver;
import de.profil.Title;
import de.profil.WebUtility;

public class ReceiverForm {
	
	// Constants
	
	private final static String TAB = "\t";
	private final static String NEWLINE = "\n";
	
	// Fields
	
	private HttpServletRequest _request = null;
	
	private Receiver _receiver = null;
	private String _errFirstName = "";
	private String _errName = "";
	private String _errStreet = "";
	private String _errHouseNumber = "";
	private String _errPostCode = "";
	private String _errCity = "";
	private String _errPhone = "";
	private String _errGeneral = "";
	private String _errCountry = "";
	
	// Constructors
	
	public ReceiverForm(HttpServletRequest request) {
		
		_receiver = new Receiver();
		_receiver.setAddress(new Address());
		_request = request;
		
	}
	
	public ReceiverForm(Receiver receiver) {
		
		_receiver = receiver;
		
	}
	

	public ReceiverForm() {
		
	}

	// Getters / Setters
	
	public Receiver getReceiver() {
		return _receiver;
	}
	
	public void setReceiver(Receiver receiver) {
		_receiver = receiver;
	}
	
	public String getErrCountry() {
		return _errCountry;
	}
	
	public String getErrFirstName() {
		return _errFirstName;
	}


	public String getErrName() {
		return _errName;
	}


	public String getErrStreet() {
		return _errStreet;
	}


	public String getErrHouseNumber() {
		return _errHouseNumber;
	}


	public String getErrPostCode() {
		return _errPostCode;
	}


	public String getErrCity() {
		return _errCity;
	}

	public String getErrPhone() {
		return _errPhone;
	}

	public String getErrGeneral() {
		return _errGeneral;
	}
	
	// Methods
	
	public void retrieveReceiverFromRequest(Country[] countries, Customer customer) {
		
		if(_request == null)
			return;
		
		Receiver receiver = new Receiver();
		Address address = new Address();
		
		String countryCode = WebUtility.getNonNullParam(_request, "d_country");
		
		receiver.setTitleByString(WebUtility.getNonNullParam(_request, "d_title"));
		receiver.setFirstname(WebUtility.getNonNullParam(_request, "d_firstname"));
		receiver.setName(WebUtility.getNonNullParam(_request, "d_lastname"));
		
		if(customer != null)
			customer.setPhone(WebUtility.getNonNullParam(_request, "d_phone"));
		
		address.setStreet(WebUtility.getNonNullParam(_request, "d_street"));
		address.setHouseNumber(WebUtility.getNonNullParam(_request, "d_houseno"));
		address.setPostCode(WebUtility.getNonNullParam(_request, "d_postcode"));
		address.setCity(WebUtility.getNonNullParam(_request, "d_city"));
		
		for(Country c : countries) {
			
			if(c.getCode().equals(countryCode))
				address.setCountry(c);
			
		}
		
		receiver.setAddress(address);
		
		_receiver = receiver;
		
	}

	public String titleSelectBox(String name, String id) {
		
		String html = "<select id='"+id+"' name='"+name+"'>" + NEWLINE;
		
		if(_receiver != null) {
			
			html += TAB + "<option value='Frau' " + (_receiver.getTitle() == Title.Frau ? " selected " : " " ) + " >Frau</option>" + NEWLINE;
			html += TAB + "<option value='Herr' " + (_receiver.getTitle() == Title.Herr ? " selected " : " " ) + " >Herr</option>" + NEWLINE;
			html += TAB + "<option value='Firma' " + (_receiver.getTitle() == Title.Firma ? " selected " : " " ) + " >Firma</option>" + NEWLINE;
			
		}
		
		html += "</select>" + NEWLINE;
		
		return html;
		
	}
	
	public boolean validateReceiver(Customer customer) {
		
		if(_receiver == null || _receiver.getAddress() == null) {
			_errGeneral = "Ungültiger Empfänger.";
			return false;
		}
			
		
		boolean isValid = true;
		
		if(_receiver.getFirstname().length() <= 0) {
			
			isValid = false;
			_errFirstName = "Bitte einen Vornamen eingeben.";
			
		}
		else if(_receiver.getFirstname().length() > 50) {
			
			isValid = false;
			_errFirstName = "Bitte maximal 50 Zeichen eingeben.";
			
		}
		
		if(_receiver.getName().length() <= 0) {
			
			isValid = false;
			_errName = "Bitte einen Namen eingeben.";
			
		}
		else if(_receiver.getName().length() > 50) {
			
			isValid = false;
			_errName = "Bitte maximal 50 Zeichen eingeben.";
			
		}
		
		if(_receiver.getAddress().getStreet().length() <= 0) {
			
			isValid = false;
			_errStreet = "Bitte eine Straße eingeben.";
			
		}
		else if(_receiver.getAddress().getStreet().length() > 100) {
			
			isValid = false;
			_errStreet = "Bitte maximal 100 Zeichen eingeben.";
			
		}
		
		if(_receiver.getAddress().getHouseNumber().length() <= 0 || _receiver.getAddress().getHouseNumber().length() > 8) {
			
			isValid = false;
			_errHouseNumber = "Bitte eine gültige Hausnummer eingeben.";
			
		}
		
		if(_receiver.getAddress().getPostCode().length() <= 0 || _receiver.getAddress().getPostCode().length() > 30) {
			
			isValid = false;
			_errPostCode = "Bitte eine gültige Postleitzahl eingeben.";
			
		}
		
		if(_receiver.getAddress().getCity().length() <= 0) {
			
			isValid = false;
			_errCity = "Bitte einen Ort eingeben.";
			
		}
		else if(_receiver.getAddress().getCity().length() > 100) {
			
			isValid = false;
			_errCity = "Bitte maximal 100 Zeichen eingeben.";
			
		}
		
		if(_receiver.getAddress().getCountry() == null) {
			
			isValid = false;
			_errCountry = "Bitte ein Land auswählen.";
			
		}
		
		else if(!_receiver.getAddress().getCountry().getCode().equals("DE")) {
			
			if(customer.getPhone().length() <= 0) {
				
				isValid = false;
				_errPhone = "Für Lieferungen außerhalb Deutschlands ist eine Telefonnummer erforderlich.";
				
			}
			
		}
		
		return isValid;
		
	}
		

}
