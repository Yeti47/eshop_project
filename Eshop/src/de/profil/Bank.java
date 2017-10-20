package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;


import net.yetibyte.snowstorm.DatasetAttributes;
import net.yetibyte.snowstorm.IDatabaseReadable;
import net.yetibyte.snowstorm.IDatabaseWritable;


public class Bank implements IDatabaseReadable, IDatabaseWritable {
	
	// Constants
	
	private final static String TABLE_NAME = "bankdetails";
	private final static String[] COLUMN_NAMES = new String[] { "name", "owner", "bic" ,"iban" };
	
	// Fields
	
	private String _name = "";
	
	private String _owner = "";
	
	private String _bic = "";
	
	private String _iban = "";
	
	// Constructors
	
	public Bank(String name, String owner, String bic, String iban) {
		
		_name = name;
		_owner = owner;
		_bic = bic;
		_iban = iban;
		
	}
	
	public Bank() {
		
	}
	
	// Getter / Setter
		
	public String getName() {
		return _name;
	}
	
	public String getOwner() {
		return _owner;
	}
	
	public String getBic() {
		return _bic;
	}
	public String getIban() {
		return _iban;
	}
	
	
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
		_owner = rs.getString("owner");
		_bic = rs.getString("bic");
		_iban = rs.getString("iban");
			
	}	
		
	@Override
	public DatasetAttributes writeToDatabase() {
		DatasetAttributes attributes = new DatasetAttributes();
		
		attributes.setAttribute("name", _name);
		attributes.setAttribute("owner", _owner);
		attributes.setAttribute("bic", _bic);
		attributes.setAttribute("iban", _iban);
		
		
		return attributes;
	}

	
}