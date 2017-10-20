package de.profil.beans;

import de.profil.Customer;
import de.profil.Title;

public class CustomerForm {
	
	// Constants
	
	private final static String TAB = "\t";
	private final static String NEWLINE = "\n";
	
	// Fields
	
	private Customer _customer = null;
	private String _errFirstName = "";
	private String _errName = "";
	private String _errStreet = "";
	private String _errHouseNumber = "";
	private String _errPostCode = "";
	private String _errCity = "";
	private String _errEmail = "";
	private String _errPhone = "";
	private String _errGeneral = "";
	public String getErrCountry() {
		return _errCountry;
	}

	private String _errCountry = "";
	
	private boolean _isDeliveryAddressChecked = false;
	
	// Constructors
	
	public CustomerForm(Customer customer) {
		
		_customer = customer;
		
	}
	

	public CustomerForm() {
		
	}

	// Getters / Setters
	
	public Customer getCustomer() {
		return _customer;
	}
	
	public void setCustomer(Customer customer) {
		_customer = customer;
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

	public String getErrEmail() {
		return _errEmail;
	}


	public String getErrPhone() {
		return _errPhone;
	}

	public String getErrGeneral() {
		return _errGeneral;
	}
	
	public boolean isDeliveryAddressChecked() {
		return _isDeliveryAddressChecked;
	}


	public void setDeliveryAddressChecked(boolean isDeliveryAddressChecked) {
		_isDeliveryAddressChecked = isDeliveryAddressChecked;
	}
	
	// Methods

	public String titleSelectBox(String name, String id) {
		
		String html = "<select id='"+id+"' name='"+name+"'>" + NEWLINE;
		
		if(_customer != null) {
			
			html += TAB + "<option value='Frau' " + (_customer.getTitle() == Title.Frau ? " selected " : " " ) + " >Frau</option>" + NEWLINE;
			html += TAB + "<option value='Herr' " + (_customer.getTitle() == Title.Herr ? " selected " : " " ) + " >Herr</option>" + NEWLINE;
			html += TAB + "<option value='Firma' " + (_customer.getTitle() == Title.Firma ? " selected " : " " ) + " >Firma</option>" + NEWLINE;
			
		}
		
		html += "</select>" + NEWLINE;
		
		return html;
		
	}
	
	public boolean validateCustomer() {
		
		if(_customer == null || _customer.getAddress() == null) {
			_errGeneral = "Ungültiger Kunde.";
			return false;
		}
			
		
		boolean isValid = true;
		
		if(_customer.getFirstname().length() <= 0) {
			
			isValid = false;
			_errFirstName = "Bitte einen Vornamen eingeben.";
			
		}
		else if(_customer.getFirstname().length() > 50) {
			
			isValid = false;
			_errFirstName = "Bitte maximal 50 Zeichen eingeben.";
			
		}
		
		if(_customer.getName().length() <= 0) {
			
			isValid = false;
			_errName = "Bitte einen Namen eingeben.";
			
		}
		else if(_customer.getName().length() > 50) {
			
			isValid = false;
			_errFirstName = "Bitte maximal 50 Zeichen eingeben.";
			
		}
		
		if(_customer.getAddress().getStreet().length() <= 0) {
			
			isValid = false;
			_errStreet = "Bitte eine Straße eingeben.";
			
		}
		else if(_customer.getAddress().getStreet().length() > 100) {
			
			isValid = false;
			_errStreet = "Bitte maximal 100 Zeichen eingeben.";
			
		}
		
		if(_customer.getAddress().getHouseNumber().length() <= 0 || _customer.getAddress().getHouseNumber().length() > 10) {
			
			isValid = false;
			_errHouseNumber = "Bitte eine gültige Hausnummer eingeben.";
			
		}
		
		if(_customer.getAddress().getPostCode().length() <= 0 || _customer.getAddress().getPostCode().length() > 30) {
			
			isValid = false;
			_errPostCode = "Bitte eine gültige Postleitzahl eingeben.";
			
		}
		
		if(_customer.getAddress().getCity().length() <= 0) {
			
			isValid = false;
			_errCity = "Bitte einen Ort eingeben.";
			
		}
		else if(_customer.getAddress().getCity().length() > 100) {
			
			isValid = false;
			_errCity = "Bitte maximal 100 Zeichen eingeben.";
			
		}
		
		if(_customer.getEmail().length() <= 0) {
			
			isValid = false;
			_errEmail = "Bitte eine E-Mail Adresse eingeben.";
			
		}
		else if(_customer.getEmail().length() > 100) {
			
			isValid = false;
			_errEmail = "Bitte maximal 100 Zeichen eingeben.";
			
		}
		
		if(_customer.getAddress().getCountry().getCode().equals("NONE")) {
			
			isValid = false;
			_errCountry = "Bitte ein Land auswählen.";
			
		}
		
		if(!_isDeliveryAddressChecked && !_customer.getAddress().getCountry().getCode().equals("DE")) {
			
			if(_customer.getPhone().length() <= 0) {
				
				isValid = false;
				_errPhone = "Für Lieferungen ist Ausland ist eine Telefonnummer erforderlich.";
				
			}
			
		}
		
		return isValid;
		
	}
	
}
