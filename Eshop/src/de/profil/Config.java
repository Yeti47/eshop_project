package de.profil;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public abstract class Config {
	
	/**
	 * Der Name des Online-Shops.
	 */
	public static final String ESHOP_NAME = "K. Adam's Online Store";
	
	public static final String DB_URL = "jdbc:oracle:thin:@192.168.122.132:1521:XE";
	public static final String DB_USER = "a10";
	public static final String DB_PASS = "a10";
	
	public static DataSource getDataSource() {
		
		OracleDataSource dataSource = null;
		
		try {
			
			dataSource = new OracleDataSource();
			
			dataSource.setURL(DB_URL);
			dataSource.setUser(DB_USER);
			dataSource.setPassword(DB_PASS);
			
			Connection c = dataSource.getConnection();
			
		} catch (SQLException e) {
			
		}
		
		return dataSource;
		
	}
	

}
