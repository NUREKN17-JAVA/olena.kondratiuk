package ua.nure.cs.kondratiuk.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
	private static final String USER_DAO = "ua.nure.cs.kondratiuk.usermanagement.db.Dao";
	private static final String DAO_FACTORY = "dao.factory";

	protected DaoFactory() {
	}
	private static DaoFactory instance;
	protected static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ConnectionFactory getConnectionFactory () {
		String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");
		return new ConnectionFactoryImpl(driver, url, user, password);		
	}
	
	public Dao getDao () {
		Dao result = null;
		Class clazz;
		try {
			clazz = Class.forName(properties.getProperty(USER_DAO));
			Dao userDao = (Dao) clazz.newInstance();
			userDao.setConnectoinFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
		return result;
	}
	
	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			Class<?> factoryClass;
			try {
				factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
}
	
