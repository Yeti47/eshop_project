package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.yetibyte.snowstorm.IJoinedDatabaseObj;
import net.yetibyte.snowstorm.Join;

public class Product implements IJoinedDatabaseObj {
	
	// Constants
	
	private static final String TABLE_NAME = "products";
	private static final String[] COLUMN_NAMES = new String[] {
			"products.prod_id AS prod_id",
			"products.name AS name",
			"products.descr AS descr",
			"products.price AS price",
			"products.active AS active",
			"packages.package_fee AS package_fee",
			"products.image AD image"
	};
	
	// Fields
	
	private int _id = -1;
	private String _name = "";
	private String _description = "";
	private double _price = 0.0;
	private boolean _isActive = true;
	private double _packageFee = 0.0;
	private String _imageName = "";
	
	// Constructor
	
	public Product(String name, double price, double packageFee) {
		
		_name = name;
		_price = price;
		_packageFee = packageFee;
		
	}
	
	public Product() {
		
	}
	
	// Getters / Setters
	
	public String getImageName() {
		return _imageName;
	}
	
	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public String getDescription() {
		return _description;
	}

	public double getPrice() {
		return _price;
	}

	public boolean isActive() {
		return _isActive;
	}

	public double getPackageFee() {
		return _packageFee;
	}

	

	// Methods

	@Override
	public String[] getColumnNames() {

		return COLUMN_NAMES;
	}

	@Override
	public void readFromDatabase(ResultSet rs) throws SQLException {
		
		/* REFERENCE:
		"products.prod_id AS prod_id",
		"products.name AS name",
		"products.descr AS descr",
		"products.price AS price",
		"products.active AS active",
		"packages.package_fee AS package_fee"
		*/
		
		_id = rs.getInt("prod_id");
		_name = rs.getString("name");
		_description = rs.getString("descr");
		_price = rs.getDouble("price");
		_isActive = rs.getBoolean("active");
		_packageFee = rs.getDouble("package_fee");
		_imageName = rs.getString("image");
				
	}

	@Override
	public String getTableName() {

		return TABLE_NAME;
	}

	@Override
	public Collection<Join> join() {

		List<Join> joins = new ArrayList<Join>();
		Join j = new Join("packages", "products.package_id", "packages.package_id");
		joins.add(j);
		
		return joins;
		
	}
	
	
	

}
