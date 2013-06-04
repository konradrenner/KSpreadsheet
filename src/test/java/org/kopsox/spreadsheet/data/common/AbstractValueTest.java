package org.kopsox.spreadsheet.data.common;

import static org.junit.Assert.*;
import org.junit.Test;

public abstract class AbstractValueTest{
	
	/**
	 * This value is not allowed to be NULL
	 * 
	 */
	protected AbstractValue primaryValue;
	
	/**
	 * This value is not allowed to be equal to the primaryValue
	 * 
	 */
	protected AbstractValue secondaryValue;
	
	/**
	 * This value is not allowed to be unequal to the primaryValue
	 * 
	 */
	protected AbstractValue thirdValue;

	protected abstract void setUp() throws Exception;

	@Test
	public void testHashCode() {
		assertNotNull(primaryValue);
		assertNotNull(secondaryValue);
		assertNotNull(thirdValue);
		
		assertTrue(primaryValue.hashCode() == thirdValue.hashCode());
		assertFalse(primaryValue.hashCode() == secondaryValue.hashCode());
		assertFalse(thirdValue.hashCode() == secondaryValue.hashCode());
	}
	
	@Test
	public void testGetFormula() {
		assertNotNull(primaryValue);
		assertNotNull(secondaryValue);
		assertNotNull(thirdValue);
		
		String formula = "SUM(A1:A2)";
		
		primaryValue.setFormula(formula);
		secondaryValue.setFormula(null);
		
		assertEquals(primaryValue.getFormula(), formula);
		assertEquals(secondaryValue.getFormula(), null);
	}

	@Test
	public void testEqualsObject() {
		assertNotNull(primaryValue);
		assertNotNull(secondaryValue);
		assertNotNull(thirdValue);
		
		assertTrue(primaryValue.equals(thirdValue));
		assertTrue(thirdValue.equals(primaryValue));
		
		assertFalse(primaryValue.equals(secondaryValue));
		assertFalse(secondaryValue.equals(primaryValue));
		
		assertFalse(secondaryValue.equals(thirdValue));
		assertFalse(thirdValue.equals(secondaryValue));
	}

}
