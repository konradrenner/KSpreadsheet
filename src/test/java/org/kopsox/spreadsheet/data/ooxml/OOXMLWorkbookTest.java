package org.kopsox.spreadsheet.data.ooxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kopsox.spreadsheet.TestUtil;
import org.kopsox.spreadsheet.SpreadsheetFactory;
import org.kopsox.spreadsheet.SpreadsheetFactory.SpreadsheetType;
import org.kopsox.spreadsheet.data.common.AbstractWorkbookTest;

public class OOXMLWorkbookTest extends AbstractWorkbookTest {

	@Override
	@Before
	public void setUp() throws Exception {
	InputStream stream = TestUtil.getSpreadSheetStrean("ooxml_test.xlsx");
        this.workbook = SpreadsheetFactory.openWorkbook("name",stream, SpreadsheetType.OOXML);
        stream.close();
        this.sheetNames = new ArrayList<String>(3);
        this.sheetNames.add("Table1");
        this.sheetNames.add("Tabelle2");
        this.sheetNames.add("Tabelle3");
        this.numberOfSelectedSheet = new Integer(0);
	}

	@Override
	@Test
	public void testSave() {
		File newFile = new File("newOOXML.xlsx");
		
		try {
			OutputStream stream = new FileOutputStream(newFile);
			this.workbook.save(stream);
			stream.close();
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Override
	public SpreadsheetType getSpreadsheetType() {
		return SpreadsheetType.OOXML;
	}

}
