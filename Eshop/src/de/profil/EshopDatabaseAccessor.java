package de.profil;

import javax.sql.DataSource;

import net.yetibyte.snowstorm.DatabaseAccessor;

public class EshopDatabaseAccessor extends DatabaseAccessor {

	public EshopDatabaseAccessor(DataSource dataSource) {
		super(dataSource);
		
	}
	
	public EshopDatabaseAccessor() {
		super(Config.getDataSource());
	}

}
