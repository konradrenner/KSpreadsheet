package org.kopsox.spreadsheet.data.common;

import org.junit.Before;
import org.junit.Test;
import org.kopsox.spreadsheet.data.Value;

public class DoubleValueTest extends AbstractValueTest {

	@SuppressWarnings("boxing")
	@Before
	@Override
	public void setUp() throws Exception {
		primaryValue = new DoubleValue(1.0);
		secondaryValue = new DoubleValue(0.0);
		thirdValue = new DoubleValue(1.0);
	}

	@Test
	public void testGetType() {
		assertEquals(Value.Type.DOUBLE, primaryValue.getType());
	}

	@Test
	public void testAsBoolean() {
		assertNotNull(primaryValue.asBoolean());
		assertNotNull(secondaryValue.asBoolean());
		assertEquals(primaryValue.asBoolean(), Boolean.TRUE);
		assertEquals(secondaryValue.asBoolean(), Boolean.FALSE);
	}

	@Test
	public void testAsDate() {
		assertNull(this.primaryValue.asDate());
	}

	@Test
	public void testAsDateString() {
		assertNull(this.primaryValue.asDate("dd-MM-yyyy"));
	}

	@SuppressWarnings("boxing")
	@Test
	public void testAsDouble() {
		assertNotNull(primaryValue.asDouble());
		assertEquals(primaryValue.asDouble(), 1.0);
		assertNotSame(primaryValue.asDouble(), 1.01);
	}

	@Test
	public void testAsTime() {
		assertNull(this.primaryValue.asTime());
	}

	@Test
	public void testAsString() {
		assertNotNull(primaryValue.asDouble());
		assertEquals(primaryValue.asDouble().toString(), new Double(1.0).toString());
	}

}
