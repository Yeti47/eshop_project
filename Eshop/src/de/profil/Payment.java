package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.profil.beans.ISelectBoxOption;
import net.yetibyte.snowstorm.IJoinedDatabaseObj;
import net.yetibyte.snowstorm.Join;

public class Payment implements IJoinedDatabaseObj, ISelectBoxOption {

	// Constants
	
	private final static String TABLE_NAME = "payments";
	private final static String[] COLUMN_NAMES = new String[] { "pay_countries.pay_id", "pay_countries.pay_fee", "payments.name" };
	
	// Fields
	
	private int _id = -1;
	
	private String _name = "Nicht verfügbar";
	
	private double _fee = 0.0;
	
	private boolean _fetchNameOnly = false;
	
	// Constructor
	
	public Payment(int id, String name, double fee) {
		_id = id;
		_name = name;
		_fee = fee;
	}
	
	public Payment(boolean fetchNameOnly) {
		
		_fetchNameOnly = true;
		
	}

	public Payment() {
		
	}

	// Getters / Setters
	
	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public double getFee() {
		return _fee;
	}

	@Override
	public String getTableName() {

		return TABLE_NAME;
		
	}

	@Override
	public String[] getColumnNames() {

		return !_fetchNameOnly ? COLUMN_NAMES : new String[] { "name" };
		
	}

	@Override
	public void readFromDatabase(ResultSet rs) throws SQLException {
		
		_name = rs.getString("name");

		if(!_fetchNameOnly) {
			
			_id = rs.getInt("pay_id");
			_fee = rs.getDouble("pay_fee");
			
		}

	}

	@Override
	public Collection<Join> join() {

		List<Join> joins = new ArrayList<Join>();
		
		Join j = new Join("pay_countries", "payments.pay_id", "pay_countries.pay_id");
		joins.add(j);
		
		return joins;
		
	}

	@Override
	public String getOptionValue() {
		
		return Integer.toString(_id);
	}

	@Override
	public String getOptionText() {
		
		return _name;
	}
	
	// Methods
	
	
}
