package org.kopsox.spreadsheet.data.common;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.kopsox.spreadsheet.data.Value;

public class DateValueTest extends AbstractValueTest {

    private Date t1;
    private Date t2;

    @Before
    @Override
    public void setUp() throws Exception {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 2);

        t1 = cal.getTime();

        cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 3);

        t2 = cal.getTime();

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
        assertFalse(primaryValue.asBoolean());
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
        assertEquals(primaryValue.asString(), new java.sql.Date(t1.getTime()).toString());
        assertNotSame(primaryValue.asString(), new java.sql.Date(t2.getTime()).toString());
    }

}
