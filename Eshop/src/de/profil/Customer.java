package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.yetibyte.snowstorm.DatasetAttributes;
import net.yetibyte.snowstorm.IDatabaseReadable;
import net.yetibyte.snowstorm.IDatabaseWritable;

public class Customer extends Receiver implements IDatabaseReadable, IDatabaseWritable {
	// Constants
	
	private static final String TABLE_NAME = "customers";
	private static final String[] COLUMN_NAMES = new String[] { "custom_id", "title", "name", "firstname", "addr_id", "email" };

	// Fields

	private int      _custom_id = -1;
	private Receiver rec 				= new Receiver();
	private String   _email 		= "";
	private Bank     bank 			= new Bank();

	public Customer(Title title, String name, String firstname, Integer addr_id, Integer custom_id, String email) {
		
		super(title, name, firstname, addr_id, custom_id);
		_email = email;
	}
	
	public Customer() {
		
	}
	
	// Getters / Setters
	
	public Integer getCustom_id() {
		return _custom_id;
	}
	
	public String getEmail() {
		return _email;
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
		super.readFromDatabase(rs);
				
		/* TODO: nochmal pruefen
		_custom_id = rs.getInt("custom_id");
		rec.setTitle(rs.getString("title"));
		rec.setName(rs.getString("name"));
		rec.setFirstname(rs.getString("firstname"));
		rec.setAddr_id(rs.getInt("addr_id"));
		
		*/
		_email 		 = rs.getString("email");
	}

	@Override
	public DatasetAttributes writeToDatabase() {
		
		/*
		DatasetAttributes attributes = new DatasetAttributes();
		
		attributes.setAttribute("title", rec.getTitle());
		attributes.setAttribute("name", rec.getName());
		attributes.setAttribute("firstname", rec.getFirstname());
		attributes.setAttribute("addr_id", rec.getAddr_id());
		attributes.setAttribute("email", _email);
		
		*/
		
		DatasetAttributes attributes = super.writeToDatabase();
		attributes.setAttribute("email", _email);
		
		return attributes;
	}

}
