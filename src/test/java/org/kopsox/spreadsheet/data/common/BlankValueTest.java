package org.kopsox.spreadsheet.data.common;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.kopsox.spreadsheet.data.Value;

public class BlankValueTest extends AbstractValueTest {

	@Before
	@Override
	public void setUp() throws Exception {
		primaryValue = new BlankValue();
		secondaryValue = new StringValue("TEST");
		thirdValue = new BlankValue();
	}

	@Test
	public void testGetType() {
		assertEquals(Value.Type.BLANK, primaryValue.getType());
	}

	@Test
	public void testAsBoolean() {
            assertFalse(primaryValue.asBoolean());
	}

	@Test
	public void testAsDate() {
		assertNull(primaryValue.asDate());
	}

	@Test
	public void testAsDateString() {
		assertNull(primaryValue.asDate("dd-MM-yyyy"));
	}

	@Test
	public void testAsDouble() {
		assertNull(primaryValue.asDouble());
	}

	@Test
	public void testAsTime() {
		assertNull(primaryValue.asTime());
	}

	@Test
	public void testAsString() {
		assertNull(primaryValue.asString());
	}

}
