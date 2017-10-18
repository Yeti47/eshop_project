package de.profil;

import net.yetibyte.snowstorm.IDatabaseObj;
import net.yetibyte.snowstorm.TableAttribute;

public class ProductBundlePair implements IDatabaseObj {
	
	private final static String TABLE_NAME = "prod_bundles";
	
	// Fields
	
	@TableAttribute(column="id")
	private int _id;
	
	@TableAttribute(column="prod_id")
	private int _prodId;
	
	@TableAttribute(column="bundle_id")
	private int _bundleId;
	
	// Getters
	
	public int getId() {
		return _id;
	}

	public int getProdId() {
		return _prodId;
	}

	public int getBundleId() {
		return _bundleId;
	}
	
	// Methods

	@Override
	public String getTableName() {

		return TABLE_NAME;
		
	}

}
