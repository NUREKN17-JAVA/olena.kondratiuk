package ua.nure.cs.kondratiuk.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.cs.kondratiuk.usermanagement.User;
import ua.nure.cs.kondratiuk.usermanagement.db.DaoFactory;
import ua.nure.cs.kondratiuk.usermanagement.db.DatabaseExeption;

public class AddServlet extends EditServlet {

	private static final long serialVersionUID = 1579163404364469187L;

	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

	@Override
	protected void processUser(User user) throws DatabaseExeption, ReflectiveOperationException {
		DaoFactory.getInstance().getUserDao().create(user);
	}
	
}