package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.profil.beans.ISelectBoxOption;
import net.yetibyte.snowstorm.IDatabaseReadable;

public class Country implements IDatabaseReadable, ISelectBoxOption {
	
	// Constants
	
	private static final String TABLE_NAME = "countries";
	private static final String[] COLUMN_NAMES = new String[] { "country_code", "name", "shipping_fee" };
	
	// Fields

	private String _name = "";
	
	private String _code = "";

	private double _shippingFee = 0.0;
	
	// Constructors
	
	public Country(String name, String code) {
		
		_name = name;
		_code = code;
		
	}
	
	public Country() {
		
	}

	// Getters / Setters
	
	public String getName() {
		return _name;
	}

	public String getCode() {
		return _code;
	}

	public double getShippingFee() {
		return _shippingFee;
	}
	
	// Methods

	@Override
	public String getTableName() {
		
		return TABLE_NAME;
	}

	@Override
	public String[] getColumnNames() {

		return COLUMN_NAMES;
		
	}

	@Override
	public void readFromDatabase(ResultSet rs) throws SQLException {
	
		_name = rs.getString("name");
		_code = rs.getString("country_code");
		_shippingFee = rs.getDouble("shipping_fee");
		
	}

	@Override
	public String getOptionValue() {

		return _code;
	}

	@Override
	public String getOptionText() {

		return _name;
	}
	
	

}
