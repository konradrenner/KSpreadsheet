package org.kopsox.spreadsheet.data.common;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.kopsox.spreadsheet.data.Sheet;
import org.kopsox.spreadsheet.data.Value;
import org.kopsox.spreadsheet.data.Workbook;

public abstract class AbstractSheetTest{
	
	protected Integer lastColumnFromLastRow;
	protected Integer lastRow;
	protected Value valueOfLastRowsLastColumn;
	protected Sheet sheet;
	protected Value otherValue;
	protected Integer sheetIndex;
	protected String sheetName;
	protected Sheet otherSheet;
	protected Workbook workbook;

	public abstract void setUp() throws Exception;
	
	@Test
	public void testGetWorkbook() {
		checkProperties();
		
		Assert.assertEquals(this.workbook, this.sheet.getWorkbook());
	}

	@Test
	public void testGetNumberOfLastColumn() {
		checkProperties();
		
		Assert.assertEquals(lastColumnFromLastRow.intValue(), this.sheet.getNumberOfLastColumn(lastRow.intValue()));
	}

	@Test
	public void testGetNumberOfLastRow() {
		checkProperties();
		
		Assert.assertEquals(lastRow.intValue(), this.sheet.getNumberOfLastRow());
	}

	@Test
	public void testGetValueAt() {
		checkProperties();
		
		Value valueFromSheet =  this.sheet.getValueAt(this.lastRow.intValue(),this.lastColumnFromLastRow.intValue());
		
		Assert.assertNotNull(valueFromSheet);
		Assert.assertEquals(valueOfLastRowsLastColumn,valueFromSheet);
		Assert.assertNotSame(otherValue,valueFromSheet);
	}

	@Test
	public void testSetFormualaAt() {
		checkProperties();
		String formula = "A1+A2";
		
		this.sheet.setFormualaAt(2, 2, formula);
		
		Assert.assertEquals(formula, this.sheet.getValueAt(2, 2).getFormula());
	}

	@Test
	public void testSetValueAtIntIntString() {
		checkProperties();
		
		String svalue = "test";
		Value value = new StringValue(svalue);
		
		this.sheet.setValueAt(2, 2, svalue);
		
		Assert.assertEquals(value, this.sheet.getValueAt(2, 2));
		Assert.assertNotSame(new StringValue("test2"), this.sheet.getValueAt(2, 2));
	}

	@SuppressWarnings("boxing")
	@Test
	public void testSetValueAtIntIntDouble() {
		checkProperties();
		double svalue = 2.0;
		
		Value value = new DoubleValue(svalue);
		
		this.sheet.setValueAt(2, 2, svalue);
		
		Assert.assertEquals(value, this.sheet.getValueAt(2, 2));
		Assert.assertNotSame(new DoubleValue(2.1), this.sheet.getValueAt(2, 2));
	}

  @Test
	public void testSetValueAtIntIntDate() {
		checkProperties();
		long svalue = System.currentTimeMillis();
		
		Value value = new DateValue(new Date(svalue));
		String format = "yyyy-MM-dd";
		
		this.sheet.setValueAt(2, 2, new Date(svalue),format);
		
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		
		Assert.assertEquals(formatter.format(value.asDate()), formatter.format(this.sheet.getValueAt(2, 2).asDate()));
		Assert.assertNotSame(new DateValue(new Date(System.currentTimeMillis())), this.sheet.getValueAt(2, 2));
	}

	@Test
	public void testSetValueAtIntIntTime() {
		checkProperties();
		long svalue = System.currentTimeMillis();
		
		Value value = new TimeValue(new Time(svalue));
		
		this.sheet.setValueAt(2, 2, new Time(svalue),"hh:mm:ss");
		
		Assert.assertEquals(value.asTime().toString(), this.sheet.getValueAt(2, 2).asTime().toString());
		Assert.assertNotSame(new Time(System.currentTimeMillis()), this.sheet.getValueAt(2, 2).asTime());
	}

	@Test
	public void testSetValueAtIntIntBoolean() {
		checkProperties();
		Value value = new BooleanValue(Boolean.TRUE);
		
		this.sheet.setValueAt(2, 2, true);
		
		Assert.assertEquals(value, this.sheet.getValueAt(2, 2));
		Assert.assertNotSame(new BooleanValue(Boolean.FALSE), this.sheet.getValueAt(2, 2));
	}

	@Test
	public void testGetSheetIndex() {
		checkProperties();
		Assert.assertEquals(this.sheetIndex.intValue(), this.sheet.getSheetIndex());
	}

	@Test
	public void testGetSheetName() {
		checkProperties();
		Assert.assertEquals(this.sheetName, this.sheet.getSheetName());
	}

	@Test
	public void testEquals() {
		checkProperties();
		Assert.assertTrue(this.sheet.equals(this.sheet));
		Assert.assertFalse(this.sheet.equals(this.otherSheet));
	}

	@Test
	public void testHashCode() {
		checkProperties();
		Assert.assertEquals(this.sheet.hashCode(), this.sheet.hashCode());
		Assert.assertFalse(this.sheet.hashCode() == this.otherSheet.hashCode());
	}

	private final void checkProperties() {
		Assert.assertNotNull(this.sheet);
		Assert.assertNotNull(this.lastColumnFromLastRow);
		Assert.assertNotNull(this.lastRow);
		Assert.assertNotNull(this.valueOfLastRowsLastColumn);
		Assert.assertNotNull(this.otherValue);
		Assert.assertNotNull(this.sheetName);
		Assert.assertNotNull(this.sheetIndex);
		Assert.assertNotNull(this.otherSheet);
		Assert.assertNotNull(this.workbook);
	}
}
