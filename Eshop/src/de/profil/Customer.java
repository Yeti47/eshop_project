package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.yetibyte.snowstorm.DatasetAttributes;
import net.yetibyte.snowstorm.IDatabaseReadable;
import net.yetibyte.snowstorm.IDatabaseWritable;

public class Customer extends Receiver implements IDatabaseReadable, IDatabaseWritable {
	
	// Constants
	
	private static final String TABLE_NAME = "customers";
	private static final String[] COLUMN_NAMES = new String[] { "custom_id", "title", "name", "firstname", "addr_id", "email", "phon" };

	// Fields

	private int      _id = -1;
	private Receiver rec 				= new Receiver();
	private String   _email 		= "";
	private Bank     _bank 			= new Bank();
	private String _phone = "";

	// Constructors
	
	public Customer(Title title, String name, String firstname, Integer addr_id, Integer custom_id, String email, String phone) {
		
		super(title, name, firstname, addr_id, custom_id);
		_email = email;
		_phone = phone;
	}
	
	public Customer() {
		
	}
	
	// Getters / Setters
	
//	public int getId() {
//		return _id;
//	}
//	
//	public void setId(int id) {
//		_id = id;
//	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setEmail(String email) {
		_email = email;
	}
	
	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}
	
	public Bank getBank() {
		
		return _bank;
		
	}
	
	public void setBank(Bank bank) {
		_bank = bank;
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
		super.readFromDatabase(rs);
				
		/* TODO: nochmal pruefen
		_id = rs.getInt("custom_id");
		rec.setTitle(rs.getString("title"));
		rec.setName(rs.getString("name"));
		rec.setFirstname(rs.getString("firstname"));
		rec.setAddr_id(rs.getInt("addr_id"));
		
		*/
		_email = rs.getString("email");
		_phone = rs.getString("phon");
		
	}

	@Override
	public DatasetAttributes writeToDatabase() {
		
		DatasetAttributes attributes = new DatasetAttributes();
		
		attributes.setAttribute("title", getTitle().toString());
		attributes.setAttribute("name", getName());
		attributes.setAttribute("firstname", getFirstname());
		attributes.setAttribute("addr_id", getAddress().getId());
		attributes.setAttribute("email", _email);
		attributes.setAttribute("phon", _phone);
		
		return attributes;
	}

}
