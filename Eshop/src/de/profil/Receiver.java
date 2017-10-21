package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.yetibyte.snowstorm.DatasetAttributes;
import net.yetibyte.snowstorm.IDatabaseReadable;
import net.yetibyte.snowstorm.IDatabaseWritable;

public class Receiver implements IDatabaseReadable, IDatabaseWritable {

	// Constants
	
	private static final String TABLE_NAME = "receivers";
	private static final String[] COLUMN_NAMES = new String[] { "rec_id", "title", "name", "firstname", "addr_id", "custom_id" };

	// Fields

	private int 	 _rec_id 		= -1;
	private Title _title 		=  Title.Frau;
	private String _name 			= "";
	private String _firstname = "";
	private int    _addr_id 	= 0;
	private int    _custom_id = 0;
	
	private Address _address = new Address();
	
	// Constructors
	
	public Receiver(Title title, String name, String firstname, Integer addr_id, Integer custom_id) {
		
		_title 		 = title;
		_name  		 = name;
		_firstname = firstname;
		_addr_id 	 = addr_id;
		_custom_id = custom_id;
		
	}
	
	public Receiver() {
		
	}

	// Getters / Setters
	
	public Title getTitle() {
		return _title;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getFirstname() {
		return _firstname;
	}
	
	public Integer getAddr_id() {
		return _addr_id;
	}
	
	public Integer getCustom_id() {
		return _custom_id;
	}
	
	public void setTitle(Title title) {
		_title = title;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setFirstname(String firstname) {
		_firstname = firstname;
	}
	
	public void setAddr_id(Integer addr_id) {
		_addr_id = addr_id;
	}
	
	public void setTitleByString(String titleStr) {
		
		if(titleStr == null)
			return;
		
		if(titleStr.toLowerCase().equals("frau"))
			_title = Title.Frau;
		else if(titleStr.toLowerCase().equals("herr"))
			_title = Title.Herr;
		else 
			_title = Title.Firma;
		
	}
	
	public Address getAddress() {
		return _address;
	}

	public void setAddress(Address address) {
		_address = address;
	}
	
	public String getTitleText() {
		
		return _title.toString();
		
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
		_rec_id 	 = rs.getInt("rec_id");
		//_title  	 = rs.getString("title");
		_name 		 = rs.getString("name");
		_firstname = rs.getString("firstname");
		_addr_id	 = rs.getInt("addr_id");
		_custom_id = rs.getInt("custom_id");
		
		String titleStr = rs.getString("title").toLowerCase();
		
		setTitleByString(titleStr);
		
	}

	@Override
	public DatasetAttributes writeToDatabase() {
		DatasetAttributes attributes = new DatasetAttributes();
		
		attributes.setAttribute("title", _title.toString());
		attributes.setAttribute("name", _name);
		attributes.setAttribute("firstname", _firstname);
		attributes.setAttribute("addr_id", _addr_id);
		attributes.setAttribute("custom_id", _custom_id);
		
		return attributes;
	}
}
