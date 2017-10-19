package de.profil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.yetibyte.snowstorm.DatabaseAccessor;
import net.yetibyte.snowstorm.Join;

public class Bundle extends Product {

	// Constants
	
	private static final String TABLE_NAME = "bundles";
	private static final String[] COLUMN_NAMES = new String[] {
			"bundles.bundle_id AS prod_id",
			"bundles.name AS name",
			"bundles.descr AS descr",
			"bundles.price AS price",
			"bundles.active AS active",
			"packages.package_fee AS package_fee",
			"bundles.image AS image"
	};
	
	// Fields
	
	private List<Product> _products = new ArrayList<Product>();
	
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
	public Collection<Join> join() {

		List<Join> joins = new ArrayList<Join>();
		Join j = new Join("packages", "bundles.package_id", "packages.package_id");
		joins.add(j);
		
		return joins;
		
	}
	
	public boolean fetchProducts(DatabaseAccessor dbAccessor) {
		
		List<ProductBundlePair> pbPairs = dbAccessor.autofetch(() -> new ProductBundlePair(), "bundle_id=?", new String[] { getId()+"" });
		
		if(pbPairs == null)
			return false;
		
		if(pbPairs.size() <= 0)
			return true;
		
		String[] prodIds = new String[pbPairs.size()];
		String whereClause = "";
		
		for(int i = 0; i < prodIds.length; i++) {
			
			prodIds[i] = pbPairs.get(i).getProdId() + "";
			whereClause += "prod_id=? ";
			
			if(i < prodIds.length-1)
				whereClause += " OR ";
			
		}
	
		
		List<Product> prods = dbAccessor.fetchJoined(() -> new Product(), whereClause, prodIds);
		
		if(prods == null)
			return false;
		
		_products = prods;
		
		return true;
		
	}
	
}
