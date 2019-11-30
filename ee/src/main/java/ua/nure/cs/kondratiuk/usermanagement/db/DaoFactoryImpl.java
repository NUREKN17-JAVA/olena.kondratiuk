package ua.nure.cs.kondratiuk.usermanagement.db;

import ua.nure.cs.kondratiuk.usermanagement.User;

public class DaoFactoryImpl extends DaoFactory {
		
	@Override
	public Dao GetUserDao() {
		Dao result = null;
		try {
			Class<?> clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (Dao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}	
}
