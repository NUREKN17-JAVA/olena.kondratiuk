package ua.nure.cs.kondratiuk.usermanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;


public class UserTest extends TestCase {
	
	//default
	private static final int YEAR_OF_BIRTH = 1999;
	private static final int CURRENT_YEAR = 2019;
	private static final int MONTH_OF_BIRTH= Calendar.OCTOBER;
	private static final int DAY_OF_BIRTH = 14;
	private static final int ETALONE_AGE = 20;
	
	//birthday will be next month
	private static final int MONTH_OF_BIRTH1 = Calendar.NOVEMBER;
	private static final int ETALONE_AGE1 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	
	//birthday is today
	private static final int DAY_OF_BIRTH2 = Calendar.DATE;
	private static final int MONTH_OF_BIRTH2 = Calendar.OCTOBER;
	private static final int ETALONE_AGE2 = CURRENT_YEAR - YEAR_OF_BIRTH ;
	
	//birthday was last month
	private static final int MONTH_OF_BIRTH3 = Calendar.SEPTEMBER;
	private static final int ETALONE_AGE3 = CURRENT_YEAR - YEAR_OF_BIRTH;
	
	//birthday is the last day of year
	private static final int DAY_OF_BIRTH4 = 31;
	private static final int MONTH_OF_BIRTH4 = Calendar.DECEMBER;
	private static final int ETALONE_AGE4 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	
	//birthday is the first day of year
	private static final int DAY_OF_BIRTH5 = 1;
	private static final int MONTH_OF_BIRTH5 = Calendar.JANUARY;
	private static final int ETALONE_AGE5 = CURRENT_YEAR - YEAR_OF_BIRTH;	
		
		
	
	private static final String LAST_NAME = "Иванов";
	private static final String FIRST_NAME = "Иван";
	
	private User user;
	private Date dateOfBirth;

	protected void setUp() throws Exception {
		super.setUp();
		user = new User(1L, FIRST_NAME, LAST_NAME, new SimpleDateFormat("d-MM-yyyy").parse("29-04-2018"));
	}
	
		
	public void testGetFullName() {
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		assertEquals("Иванов, Иван", user.getFullName());
	}
	
	//birthday will be next month
	public void testGetAge1(){
		Calendar calendar =Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH1,DAY_OF_BIRTH);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE1,user.getAge());
	}

	//birthday is today
	public void testGetAge2(){
		Calendar calendar =Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH2,DAY_OF_BIRTH2);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE2,user.getAge());
	}
	
	//birthday was last month
	public void testGetAge3(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH3,DAY_OF_BIRTH);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE3,user.getAge());
	}
	
	//birthday is the last day of year
	public void testGetAge4(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH4,DAY_OF_BIRTH4);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE4,user.getAge());
	}
	
	//birthday is the first day of year
	public void testGetAge5(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH5,DAY_OF_BIRTH5);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE5,user.getAge());
	}	
	
	//default
	public void testGetAge(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH,DAY_OF_BIRTH);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE,user.getAge());
	}
	
	
	
	
}
