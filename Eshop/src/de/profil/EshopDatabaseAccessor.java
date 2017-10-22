package de.profil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import net.yetibyte.snowstorm.DatabaseAccessor;

public class EshopDatabaseAccessor extends DatabaseAccessor {

	public EshopDatabaseAccessor(DataSource dataSource) {
		super(dataSource);
		
	}
	
	public EshopDatabaseAccessor() {
		super(Config.getDataSource());
	}
	
	/**
	 * Gibt den aktuellen Wert der Sequenz mit den übergebenen Namen zurück, erhöht dabei jedoch auch ihren Wert.
	 * @param sequenceName Der Name der Sequenz.	
	 * @return Der aktuelle Wert der Sequenz.
	 */
	public Integer fetchCurrentSequenceValue(String sequenceName) {
		
		DataSource dataSource = getDataSource();
		
		if(dataSource == null || sequenceName == null)
			return null;
		
		Connection connection = null;
		
		try {
			
			connection = dataSource.getConnection();
			
			String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
			
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			if(rs.next())
				return rs.getInt(1)-1;
			
			
		}
		 catch(Exception e) {
		    	
		    	return null;
		    	
		}
	    finally {
	    	
	    	if(connection != null) {
	    		
	    		try { connection.close(); }
	    		catch(Exception e) { }
	    		
	    	}
	    	
	    }
		
		return null;
		
		
	}

}
