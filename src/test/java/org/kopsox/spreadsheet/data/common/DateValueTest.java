package org.kopsox.spreadsheet.data.common;

import java.sql.Time;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kopsox.spreadsheet.data.Value;

public class DateValueTest extends AbstractValueTest {
	
	private final Date t1 = new Date(System.currentTimeMillis());
	private final Date t2 = new Date(System.currentTimeMillis()+1);

	@Before
	@Override
	public void setUp() throws Exception {
		
		primaryValue = new DateValue(t1);
		secondaryValue = new DateValue(t2);
		thirdValue = new DateValue(t1);
	}

	@Test
	public void testGetType() {
		assertEquals(Value.Type.DATE, primaryValue.getType());
	}

	@Test
	public void testAsBoolean() {
		assertNull(primaryValue.asBoolean());
	}

	@Test
	public void testAsDate() {
		assertNotNull(primaryValue.asDate());
		assertEquals(primaryValue.asDate(), t1);
		assertNotSame(primaryValue.asDate(), t2);
	}

	@Test
	public void testAsDateString() {
		assertNotNull(primaryValue.asDate("dd-MM-yyyy"));
		assertEquals(primaryValue.asDate("dd-MM-yyyy"), t1);
		assertNotSame(primaryValue.asDate("dd-MM-yyyy"), t2);
	}

	@Test
	public void testAsDouble() {
		assertNull(primaryValue.asDouble());
	}

	@Test
	public void testAsTime() {
		assertNotNull(primaryValue.asTime());
		assertEquals(primaryValue.asTime(), new Time(t1.getTime()));
		assertNotSame(primaryValue.asTime(), new Time(t2.getTime()));
	}

	@Test
	public void testAsString() {
		assertNotNull(primaryValue.asString());
		assertEquals(primaryValue.asString(), t1.toString());
		assertNotSame(primaryValue.asString(), t2.toString());
	}

}
