package ua.nure.cs.kondratiuk.usermanagement.db;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instanse is null", daoFactory);
			Dao userDao = daoFactory.getUserDao();
			assertNotNull("UserDao instanse is null", userDao);
		} catch (RuntimeException e) {
			e.printStackTrace();
            fail(e.toString());
		}		
	}

}
