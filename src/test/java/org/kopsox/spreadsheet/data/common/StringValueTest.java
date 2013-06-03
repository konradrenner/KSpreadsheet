package org.kopsox.spreadsheet.data.common;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kopsox.spreadsheet.data.Value;

public class StringValueTest extends AbstractValueTest {
	private final Value stringAsDate1 = new StringValue("01-01-2011");
	private final Value stringAsDate2 = new StringValue("01-01-2011 01:01:01");
	private final Value stringAsTime = new StringValue("01:01:01");
	private final Value stringAsDouble = new StringValue("10.025");

	@Before
	@Override
	public void setUp() throws Exception {
		primaryValue = new StringValue("TRUE");
		secondaryValue = new StringValue("fAlsE");
		thirdValue = new StringValue("TRUE");
	}

	@Test
	public void testGetType() {
		assertEquals(Value.Type.STRING, primaryValue.getType());
	}

	@Test
	public void testAsBoolean() {
		assertNotNull(stringAsDouble.asBoolean());
		assertNotNull(primaryValue.asBoolean());
		assertNotNull(secondaryValue.asBoolean());
		
		assertTrue(primaryValue.asBoolean().booleanValue());
		assertFalse(secondaryValue.asBoolean().booleanValue());
		assertFalse(stringAsDouble.asBoolean().booleanValue());
	}

	@Test
	public void testAsDate() {
		assertNull(primaryValue.asDate());
		assertNotNull(stringAsDate2.asDate());
		assertNotNull(stringAsDate1.asDate());
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		Date d = null;
		
		try {
			d = format.parse(stringAsDate1.asString());
		} catch (ParseException e) {
			//nothing
		}
		
		assertEquals(stringAsDate1.asDate(), d);
		assertEquals(stringAsDate2.asDate(), d);
	}

	@Test
	public void testAsDouble() {
		assertNull(primaryValue.asDouble());
		assertNotNull(stringAsDouble.asDouble());
		
		assertTrue(stringAsDouble.asDouble().doubleValue() == 10.025);
	}

	@Test
	public void testAsTime() {
		assertNull(primaryValue.asTime());
		assertNull(stringAsDate2.asTime());
		assertNotNull(stringAsTime.asTime());
		
		assertEquals(stringAsTime.asTime(), Time.valueOf(stringAsTime.asString()));
	}

	@Test
	public void testAsDateString() {
		String format = "dd-MM-yyyy hh:mm:ss";
		assertNull(primaryValue.asDate(format));
		assertNull(stringAsDate1.asDate(format));
		assertNotNull(stringAsDate2.asDate(format));
		
		DateFormat dformat = new SimpleDateFormat(format);
		
		Date d = null;
		
		try {
			d = dformat.parse(stringAsDate2.asString());
		} catch (ParseException e) {
			//nothing
		}
		
		assertEquals(stringAsDate2.asDate(format), d);
	}

	@Test
	public void testAsString() {
		assertNotNull(primaryValue.asString());
		
		assertEquals(primaryValue.asString(), "TRUE");
		assertNotSame(primaryValue.asString(), "true");
	}

}
