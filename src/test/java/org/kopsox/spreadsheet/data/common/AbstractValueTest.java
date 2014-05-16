package org.kopsox.spreadsheet.data.common;

import static org.junit.Assert.*;
import org.junit.Test;
import org.kopsox.spreadsheet.data.Value;

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

    @Test
    public void testCompareToEqualValues() {

        assertTrue(primaryValue.compareTo(thirdValue) == 0);
        assertTrue(thirdValue.compareTo(primaryValue) == 0);
    }

    @Test
    public void testCompareToNotEqualValues() {
        assertTrue(secondaryValue.compareTo(thirdValue) != 0);
        assertTrue(thirdValue.compareTo(secondaryValue) != 0);
    }

    @Test
    public void testCompareToEqualObjects() {
        Value valOne = new StringValue("Hallo");
        Value valTwo = new StringValue("Hallo");

        assertTrue(valOne.compareTo(valTwo) == 0);
        assertTrue(valTwo.compareTo(valOne) == 0);
    }

    @Test
    public void testCompareToSameStringRepresentation() {
        Value valOne = new StringValue("true");
        Value valTwo = new BooleanValue(Boolean.TRUE);

        assertTrue(valOne.compareTo(valTwo) == 0);
        assertTrue(valTwo.compareTo(valOne) == 0);
    }

    @Test
    public void testCompareToDifferent() {
        Value valOne = new StringValue("A");
        Value valTwo = new StringValue("B");

        assertTrue(valOne.compareTo(valTwo) == -1);
        assertTrue(valTwo.compareTo(valOne) == 1);
    }
}
