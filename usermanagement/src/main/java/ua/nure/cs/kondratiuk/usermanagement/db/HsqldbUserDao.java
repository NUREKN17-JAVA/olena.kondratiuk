package ua.nure.cs.kondratiuk.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.cs.kondratiuk.usermanagement.User;

public class HsqldbUserDao implements Dao<User> {

	private static final String SELECT_FIND_ALL = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?,?,?)";
	private ConnectionFactory connectoinFactory;
	
	public HsqldbUserDao() {
	}
	
	public HsqldbUserDao(ConnectionFactory connectoinFactory) {
		this.connectoinFactory = connectoinFactory;
	}
	
	
	
	public ConnectionFactory getConnectoinFactory() {
		return connectoinFactory;
	}



	public void setConnectoinFactory(ConnectionFactory connectoinFactory) {
		this.connectoinFactory = connectoinFactory;
	}



	@Override
	public User create(User entity) throws DatabaseExeption {
		try {
			Connection connection = connectoinFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			
			int numberOfRows = statement.executeUpdate();
			if (numberOfRows != 1) {
				throw new DatabaseExeption("Number of rows =" + numberOfRows);
			}
			CallableStatement callableStatement = connection
					.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				entity.setId(new Long(keys.getLong(1))); //Mutation
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return entity;
		} catch (DatabaseExeption e) {
			throw e;
		}
		catch (SQLException e) {
			throw new DatabaseExeption(e);
		}
	}

	@Override
	public User find(Long id) throws DatabaseExeption {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User entity) throws DatabaseExeption {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) throws DatabaseExeption {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<User> findAll() throws DatabaseExeption {
		Collection<User> result = new LinkedList<User>();
		try {
			Connection connection = connectoinFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_FIND_ALL);
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (DatabaseExeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}
		return result;
	}

}
