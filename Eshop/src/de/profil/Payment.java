package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.yetibyte.snowstorm.IJoinedDatabaseObj;
import net.yetibyte.snowstorm.Join;

public class Payment implements IJoinedDatabaseObj {

	// Constants
	
	private final static String TABLE_NAME = "pay_countries";
	private final static String[] COLUMN_NAMES = new String[] { "pay_countries.pay_id", "pay_countries.pay_fee", "payments.name" };
	
	// Fields
	
	private int _id = -1;
	
	private String _name = "Nicht verfügbar";
	
	private double _fee = 0.0;
	
	// Constructor
	
	public Payment(int id, String name, double fee) {
		_id = id;
		_name = name;
		_fee = fee;
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

		return COLUMN_NAMES;
		
	}

	@Override
	public void readFromDatabase(ResultSet rs) throws SQLException {
		
		_id = rs.getInt("pay_id");
		_name = rs.getString("name");
		_fee = rs.getDouble("pay_fee");
		
	}

	@Override
	public Collection<Join> join() {

		List<Join> joins = new ArrayList<Join>();
		
		Join j = new Join("payments", "pay_countries.pay_id", "payments.pay_id");
		joins.add(j);
		
		return joins;
		
	}
	
	// Methods
	
	
}
