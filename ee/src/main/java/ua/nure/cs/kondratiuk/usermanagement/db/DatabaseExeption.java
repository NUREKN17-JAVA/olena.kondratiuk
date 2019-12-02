package ua.nure.cs.kondratiuk.usermanagement.db;

import java.sql.SQLException;

public class DatabaseExeption extends Exception {
	private static final long serialVersionUID = 5899773385953212700L;

	public DatabaseExeption(SQLException e) {
		super(e);
	}

	public DatabaseExeption(String string) {
		super(string);
	}	
}
