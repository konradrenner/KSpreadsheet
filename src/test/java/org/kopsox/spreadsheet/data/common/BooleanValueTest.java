package org.kopsox.spreadsheet.data.common;

import org.junit.Before;
import org.junit.Test;
import org.kopsox.spreadsheet.data.Value;

public class BooleanValueTest extends AbstractValueTest {

	@Before
	@Override
	public void setUp() throws Exception {
		primaryValue = new BooleanValue(Boolean.TRUE);
		secondaryValue = new BooleanValue(Boolean.FALSE);
		thirdValue = new BooleanValue(Boolean.TRUE);
	}

	@Test
	public void testGetType() {
		assertEquals(Value.Type.BOOLEAN, primaryValue.getType());
	}

	@Test
	public void testAsBoolean() {
		assertNotNull(primaryValue.asBoolean());
		assertNotNull(secondaryValue.asBoolean());
		assertTrue(primaryValue.asBoolean().booleanValue());
		assertFalse(secondaryValue.asBoolean().booleanValue());
	}

	@Test
	public void testAsDate() {
		assertNull(primaryValue.asDate());
	}

	@Test
	public void testAsDateString() {
		assertNull(primaryValue.asDate("dd-MM-yyyy"));
	}

	@SuppressWarnings("boxing")
	@Test
	public void testAsDouble() {
		assertNotNull(primaryValue.asDouble());
		assertNotNull(secondaryValue.asDouble());
		assertTrue(primaryValue.asDouble().equals(1.0));
		assertTrue(secondaryValue.asDouble().equals(0.0));
	}

	@Test
	public void testAsTime() {
		assertNull(primaryValue.asTime());
	}

	@Test
	public void testAsString() {
		assertNotNull(primaryValue.asString());
		assertNotNull(secondaryValue.asString());
		assertEquals(primaryValue.asString(), Boolean.TRUE.toString());
		assertEquals(secondaryValue.asString(), Boolean.FALSE.toString());
	}

}
