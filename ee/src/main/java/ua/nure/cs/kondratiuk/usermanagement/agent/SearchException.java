package ua.nure.cs.kondratiuk.usermanagement.agent;

import ua.nure.cs.kondratiuk.usermanagement.db.DatabaseExeption;

public class SearchException extends Exception {
	private static final long serialVersionUID = 72239430268262666L;

	public SearchException(DatabaseExeption e) {
		e.printStackTrace();
	}
}
