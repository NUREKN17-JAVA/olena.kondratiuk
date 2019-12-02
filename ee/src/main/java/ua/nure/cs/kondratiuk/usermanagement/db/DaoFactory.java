package ua.nure.cs.kondratiuk.usermanagement.db;


import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
	protected static final String USER_DAO = "dao.UserDao";
	protected static final String DAO_FACTORY = "dao.Factory";
	protected static Properties properties;
	private static DaoFactory INSTANCE;
	
	static {
		properties = new Properties();
	try {
		properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
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
	
	public abstract Dao getUserDao();
}
	
