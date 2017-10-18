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
	private String _title 		= "";
	private String _name 			= "";
	private String _firstname = "";
	private int    _addr_id 	= 0;
	private int    _custom_id = 0;
	
	// Constructors
	
	public Receiver(String title, String name, String firstname, Integer addr_id, Integer custom_id) {
		
		_title 		 = title;
		_name  		 = name;
		_firstname = firstname;
		_addr_id 	 = addr_id;
		_custom_id = custom_id;
		
	}
	
	public Receiver() {
		
	}

	// Getters / Setters
	
	public String getTitle() {
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
	
	public void setTitle(String title) {
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
		_title  	 = rs.getString("title");
		_name 		 = rs.getString("name");
		_firstname = rs.getString("firstname");
		_addr_id	 = rs.getInt("addr_id");
		_custom_id = rs.getInt("custom_id");
	}

	@Override
	public DatasetAttributes writeToDatabase() {
		DatasetAttributes attributes = new DatasetAttributes();
		
		attributes.setAttribute("title", _title);
		attributes.setAttribute("name", _name);
		attributes.setAttribute("firstname", _firstname);
		attributes.setAttribute("addr_id", _addr_id);
		attributes.setAttribute("custom_id", _custom_id);
		
		return attributes;
	}
}
