package de.profil.beans;

import javax.servlet.http.HttpServletRequest;

import de.profil.Address;
import de.profil.Country;
import de.profil.Customer;
import de.profil.Title;
import de.profil.WebUtility;

public class CustomerForm {
	
	// Constants
	
	private final static String TAB = "\t";
	private final static String NEWLINE = "\n";
	
	// Fields
	
	private HttpServletRequest _request = null;
	
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
	private String _errCountry = "";
	
	// Constructors
	
	public CustomerForm(HttpServletRequest request) {
		
		_customer = new Customer();
		_customer.setAddress(new Address());
		_request = request;
		
	}
	
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

	public String getErrEmail() {
		return _errEmail;
	}


	public String getErrPhone() {
		return _errPhone;
	}

	public String getErrGeneral() {
		return _errGeneral;
	}
	
	// Methods
	
	public void retrieveCustomerFromRequest(Country[] countries) {
		
		if(_request == null)
			return;
		
		Customer customer = new Customer();
		Address address = new Address();
		
		String countryCode = WebUtility.getNonNullParam(_request, "country");
		
		customer.setTitleByString(WebUtility.getNonNullParam(_request, "title"));
		customer.setFirstname(WebUtility.getNonNullParam(_request, "firstname"));
		customer.setName(WebUtility.getNonNullParam(_request, "lastname"));
		customer.setEmail(WebUtility.getNonNullParam(_request, "email"));
		customer.setPhone(WebUtility.getNonNullParam(_request, "phone"));
		
		address.setStreet(WebUtility.getNonNullParam(_request, "street"));
		address.setHouseNumber(WebUtility.getNonNullParam(_request, "houseno"));
		address.setPostCode(WebUtility.getNonNullParam(_request, "postcode"));
		address.setCity(WebUtility.getNonNullParam(_request, "city"));
		
		for(Country c : countries) {
			
			if(c.getCode().equals(countryCode))
				address.setCountry(c);
			
		}
		
		customer.setAddress(address);
		
		_customer = customer;
		
	}

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
			_errName = "Bitte maximal 50 Zeichen eingeben.";
			
		}
		
		if(_customer.getAddress().getStreet().length() <= 0) {
			
			isValid = false;
			_errStreet = "Bitte eine Straße eingeben.";
			
		}
		else if(_customer.getAddress().getStreet().length() > 100) {
			
			isValid = false;
			_errStreet = "Bitte maximal 100 Zeichen eingeben.";
			
		}
		
		if(_customer.getAddress().getHouseNumber().length() <= 0 || _customer.getAddress().getHouseNumber().length() > 8) {
			
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
		
		if(!_customer.getEmail().matches("^.+@.+\\..+$")) {
			
			isValid = false;
			_errEmail = "Bitte eine gültige E-Mail Adresse eingeben.";
			
		}
		
		boolean isDeliveryAddressChecked = false;
		
		if(_request != null) {
			
			isDeliveryAddressChecked = !"".equals(WebUtility.getNonNullParam(_request, "delivery"));
			
		}
		
		if(_customer.getAddress().getCountry() == null) {
			
			isValid = false;
			_errCountry = "Bitte ein Land auswählen.";
			
		}
		
		else if(!isDeliveryAddressChecked && !_customer.getAddress().getCountry().getCode().equals("DE")) {
			
			if(_customer.getPhone().length() <= 0) {
				
				isValid = false;
				_errPhone = "Für Lieferungen außerhalb Deutschlands ist eine Telefonnummer erforderlich.";
				
			}
			
		}
		
		return isValid;
		
	}
	
}
