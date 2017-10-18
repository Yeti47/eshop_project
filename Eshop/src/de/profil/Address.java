package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.yetibyte.snowstorm.DatasetAttributes;
import net.yetibyte.snowstorm.IDatabaseWritable;
import net.yetibyte.snowstorm.IJoinedDatabaseObj;
import net.yetibyte.snowstorm.Join;

public class Address implements IJoinedDatabaseObj, IDatabaseWritable {
	
	// Constants
	
	private static final String TABLE_NAME = "addresses";
	private static final String[] COLUMN_NAMES = new String[] {
			"addresses.addr_id AS addr_id",
			"addresses.street AS street",
			"addresses.house_no AS house_no",
			"addresses.postcode AS postcode",
			"addresses.city AS city",
			"countries.country_code AS country_code",
			"countries.name AS country_name",
			"countries.shipping_fee AS country_fee"
	};
	
	// Fields
	
	private int _id = -1;
	private String _street = "";
	private String _houseNumber = "";
	private String _postCode = "";
	private String _city = "";
	
	private Country _country = null;
	
	// Constructors
	
	public Address(String street, String houseNumber, String postCode, String city, Country country) {

		_street = street;
		_houseNumber = houseNumber;
		_postCode = postCode;
		_city = city;
		_country = country;
		
	}
	
	public Address() {
		
	}
	
	// Getters / Setters 

	public String getStreet() {
		return _street;
	}

	public void setStreet(String street) {
		_street = street;
	}

	public String getHouseNumber() {
		return _houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		_houseNumber = houseNumber;
	}

	public String getPostCode() {
		return _postCode;
	}

	public void setPostCode(String postCode) {
		_postCode = postCode;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	public Country getCountry() {
		return _country;
	}

	public void setCountry(Country country) {
		_country = country;
	}

	public int getId() {
		return _id;
	}

	// Methods
	
	@Override
	public String getTableName() {

		return TABLE_NAME;
		
	}

	@Override
	public DatasetAttributes writeToDatabase() {

		DatasetAttributes attributes = new DatasetAttributes();
		
		attributes.setAttribute("street", _street);
		attributes.setAttribute("house_no", _houseNumber);
		attributes.setAttribute("country_code", _country.getCode());
		attributes.setAttribute("postcode", _postCode);
		attributes.setAttribute("city", _city);
		
		return attributes;
		
	}

	@Override
	public String[] getColumnNames() {
		
		return COLUMN_NAMES;
	}

	@Override
	public void readFromDatabase(ResultSet rs) throws SQLException {

		_id = rs.getInt("addr_id");
		_street = rs.getString("street");
		_houseNumber = rs.getString("house_no");
		_postCode = rs.getString("postcode");
		_city = rs.getString("city");
		
		String countryName = rs.getString("country_name");
		String countryCode = rs.getString("country_code");
		double fee = rs.getDouble("country_fee");
		
		_country = new Country(countryName, countryCode, fee);
		
	}

	@Override
	public Collection<Join> join() {

		List<Join> joins = new ArrayList<Join>();
		
		Join j = new Join("countries", "addresses.country_code", "countries.country_code");
		joins.add(j);
		
		return joins;
		
	}
	
	@Override
	public String toString() {
		
		return _street + " | " + _houseNumber + " | " + _country.getCode() + " | " + _postCode + " | " + _city + " | " + _country.getName();
		
	}
	
}
