package ua.nure.cs.kondratiuk.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

	private String driver = "org.hsqldb.jdbcDriver";
	private String url = "jdbc:hsqldb:file:db/usermanagement";
	private String user = "sa";	
	private String password = "";	

	@Override
	public Connection createConnection() throws DatabaseExeption {
		try {			
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}
	}

}
