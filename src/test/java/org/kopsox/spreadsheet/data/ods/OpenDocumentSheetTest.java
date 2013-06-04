package org.kopsox.spreadsheet.data.ods;

import java.io.InputStream;

import org.junit.Before;
import org.kopsox.spreadsheet.TestUtil;
import org.kopsox.spreadsheet.SpreadsheetFactory;
import org.kopsox.spreadsheet.SpreadsheetFactory.SpreadsheetType;
import org.kopsox.spreadsheet.data.common.AbstractSheetTest;
import org.kopsox.spreadsheet.data.common.StringValue;

public class OpenDocumentSheetTest extends AbstractSheetTest {

	@Override
	@Before
	public void setUp() throws Exception {
		InputStream stream = TestUtil.getSpreadSheetStrean("opendocument_test.ods");
		this.workbook = SpreadsheetFactory.openWorkbook("name",stream, SpreadsheetType.OPENDOCUMENT);
		stream.close();
		this.sheet = this.workbook.getSheetByIndex(0);
		this.lastColumnFromLastRow = new Integer(1);
		this.lastRow = new Integer(2);
		this.otherSheet = this.workbook.createNewSheet();
		this.otherValue = new StringValue("Something different");
		this.sheetIndex = new Integer(0);
		this.sheetName = "Table1";
		this.valueOfLastRowsLastColumn = new StringValue("Testenty3");
	}

}
