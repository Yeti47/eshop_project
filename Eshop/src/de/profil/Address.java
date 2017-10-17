package de.profil;

public class Address {
	
	// Fields
	
	private Title _title = Title.Herr;
	
	private String _firstName = "";
	private String _lastName = "";
	
	private String _street = "";
	private String _houseNumber = "";
	private String _postCode = "";
	private String _city = "";
	
	private Country _country = null;
	
	// Constructors
	
	public Address(Title title, String firstName, String lastName, String street, String houseNumber, String postCode, String city) {
		
		_title = title;
		_firstName = firstName;
		_lastName = lastName;
		_street = street;
		_houseNumber = houseNumber;
		_postCode = postCode;
		_city = city;
		
	}
	
	public Address() {
		
	}
	
	// Methods
	
}
