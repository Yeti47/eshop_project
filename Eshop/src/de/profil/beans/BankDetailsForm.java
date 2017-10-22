package de.profil.beans;

import javax.servlet.http.HttpServletRequest;

import de.profil.Bank;
import de.profil.WebUtility;

public class BankDetailsForm {
	
	// Fields
	
	private Bank _bankDetails = null;
	private HttpServletRequest _request = null;
	
	private String _errorName = "";
	private String _errorOwner = "";
	private String _errorIban = "";
	private String _errorBic = "";
	private String _errorGeneral = "";
	
	// Constructors
	
	public BankDetailsForm(HttpServletRequest request) {
		
		_request = request;
		
	}
	
	public BankDetailsForm() {
		
	}
	
	// Getters / Setters

	public Bank getBankDetails() {
		return _bankDetails;
	}

	public void setBankDetails(Bank bankDetails) {
		_bankDetails = bankDetails;
	}

	public HttpServletRequest getRequest() {
		return _request;
	}

	public void setRequest(HttpServletRequest request) {
		_request = request;
	}

	public String getErrorName() {
		return _errorName;
	}

	public String getErrorOwner() {
		return _errorOwner;
	}

	public String getErrorIban() {
		return _errorIban;
	}

	public String getErrorBic() {
		return _errorBic;
	}

	public String getErrorGeneral() {
		return _errorGeneral;
	}
	
	// Methods
	
	public Bank retrieveBankDetailsFromRequest() {
		
		if(_request == null)
			return _bankDetails;
		
		String name = WebUtility.getNonNullParam(_request, "bankname");
		String owner = WebUtility.getNonNullParam(_request, "owner");
		String iban = WebUtility.getNonNullParam(_request, "iban").replaceAll("\\s+","");
		String bic = WebUtility.getNonNullParam(_request, "bic").replaceAll("\\s+","");
		
		_bankDetails = new Bank(name, owner, bic, iban);
		
		return _bankDetails;
		
	}
	
	public boolean validate() {
		
		if(_bankDetails == null)
			return false;
		
		boolean isValid = true;
		
		if(_bankDetails.getName().length() <= 0 || _bankDetails.getName().length() > 80) {
			
			isValid = false;
			_errorName = "Bitte einen gültigen Banknamen eingeben.";
			
		}
		
		if(_bankDetails.getOwner().length() <= 0 || _bankDetails.getOwner().length() > 80) {
			
			isValid = false;
			_errorOwner = "Bitte einen gültigen Kontoinhaber eingeben.";
			
		}
		
		if(!_bankDetails.getIban().matches("^[A-z]{2}[0-9]{16,30}$")) {
			
			isValid = false;
			_errorIban = "Bite eine gültige IBAN eingeben. Beispiel: DE19123412341234123412";
			
		}
		
		if(!_bankDetails.getBic().matches("^[A-z0-9]{8,11}$")) {
			
			isValid = false;
			_errorBic = "Bite eine gültige BIC eingeben. Beispiel: BELADEBEXXX ";
			
		}
		
		return isValid;
		
	}
	
	

}
