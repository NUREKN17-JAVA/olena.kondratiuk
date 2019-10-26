package ua.nure.cs.kondratiuk.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
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
	
