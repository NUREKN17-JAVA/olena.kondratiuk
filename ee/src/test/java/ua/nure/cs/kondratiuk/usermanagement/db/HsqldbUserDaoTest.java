package ua.nure.cs.kondratiuk.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.cs.kondratiuk.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private static final String LAST_NAME = "Doe";
	private static final String FIRST_NAME = "John";
	private static final String NEW_FIRST_NAME = "Lena";
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	private User user;

	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);

		user = new User();
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setDateOfBirth(new Date());
	}

	public void testCreate() throws DatabaseExeption {
		User userToCheck = dao.create(user);
		assertNotNull(userToCheck);
		assertNotNull(userToCheck.getId());
		assertEquals(user.getFirstName(), userToCheck.getFirstName());
		assertEquals(user.getLastName(), userToCheck.getLastName());
		assertEquals(user.getDateOfBirth(), userToCheck.getDateOfBirth());
	}

	public void testFindAll() throws DatabaseExeption {
		Collection<User> items = dao.findAll();
		assertNotNull("Collection is null", items);
		assertEquals("collectoin size does not match", 2, items.size());
	}
	
	public void testFind() throws DatabaseExeption {
		User defaultUser = dao.create(user);
		assertNotNull(defaultUser.getId());
		User foundUser = dao.find(defaultUser.getId());
		assertNotNull(foundUser);
		assertEquals(defaultUser.getId(), foundUser.getId());
	}

	public void testFindByFNandLN() throws DatabaseExeption {
		User defaultUser = dao.create(user);
		assertNotNull(defaultUser.getId());
		Collection<User> foundUsers = dao.find(defaultUser.getFirstName(), defaultUser.getLastName());
		assertNotNull(foundUsers);
		assertEquals(defaultUser.getId(), foundUsers.iterator().next().getId());
	}	

	public void testUpdate() throws DatabaseExeption {
		User testUser = dao.create(user);
		assertEquals(testUser.getFirstName(), FIRST_NAME);
		testUser.setFirstName(NEW_FIRST_NAME);
		dao.update(testUser);
		assertEquals(testUser.getFirstName(), NEW_FIRST_NAME);
	}

	public void testDelete() throws DatabaseExeption {
		User testUser = dao.create(user);
		assertNotNull(testUser.getId());
		dao.delete(testUser);
		assertNull(dao.find(testUser.getId()));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement",
				"sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("userDataSet.xml"));
		return dataSet;
	}

}
