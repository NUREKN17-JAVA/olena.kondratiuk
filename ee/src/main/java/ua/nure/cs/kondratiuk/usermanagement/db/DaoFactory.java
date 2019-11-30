package ua.nure.cs.kondratiuk.usermanagement.db;


import java.io.IOException;
import java.util.Properties;
import ua.nure.cs.kondratiuk.usermanagement.User;

public abstract class DaoFactory {
	protected static final String USER_DAO = "dao.UserDao";
	protected static final String DAO_FACTORY = "dao.Factory";
	protected static Properties properties;
	private static DaoFactory INSTANCE;
	//private static DaoFactory INSTANCE = new DaoFactory();
	
	static {
		properties = new Properties();
	try {
		properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
//	private ConnectionFactory getConnectionFactory () {
//		String user = properties.getProperty("connection.user");
//		String password = properties.getProperty("connection.password");
//		String url = properties.getProperty("connection.url");
//		String driver = properties.getProperty("connection.driver");
//		return new ConnectionFactoryImpl(driver, url, user, password);		
//	}
	
	public Dao getDao () {
		Dao result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (Dao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
		return result;
	}
	
	public static synchronized DaoFactory getInstance() {
		if (INSTANCE == null) {
			Class<?> factoryClass;
			try {
				factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
				INSTANCE = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return INSTANCE;
	}
	
	
	protected DaoFactory() {		
	}
	
	public static void init (Properties prop) {
		properties = prop;
		INSTANCE = null;
	}
	
	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}
	public abstract  Dao GetUserDao();
}
	
