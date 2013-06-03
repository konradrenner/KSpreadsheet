package org.kopsox.spreadsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.kopsox.spreadsheet.data.common.BlankValueTest;
import org.kopsox.spreadsheet.data.common.BooleanValueTest;
import org.kopsox.spreadsheet.data.common.DateValueTest;
import org.kopsox.spreadsheet.data.common.DoubleValueTest;
import org.kopsox.spreadsheet.data.common.StringValueTest;
import org.kopsox.spreadsheet.data.common.TimeValueTest;
import org.kopsox.spreadsheet.data.excel.ExcelSheetTest;
import org.kopsox.spreadsheet.data.excel.ExcelWorkbookTest;
import org.kopsox.spreadsheet.data.ods.OpenDocumentSheetTest;
import org.kopsox.spreadsheet.data.ods.OpenDocumentWorkbookTest;
import org.kopsox.spreadsheet.data.ooxml.OOXMLSheetTest;
import org.kopsox.spreadsheet.data.ooxml.OOXMLWorkbookTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.kopsox.spreadsheet");
		//$JUnit-BEGIN$
		suite.addTestSuite(TimeValueTest.class);
		suite.addTestSuite(DateValueTest.class);
		suite.addTestSuite(StringValueTest.class);
		suite.addTestSuite(DoubleValueTest.class);
		suite.addTestSuite(BlankValueTest.class);
		suite.addTestSuite(BooleanValueTest.class);
		suite.addTestSuite(ExcelSheetTest.class);
		suite.addTestSuite(ExcelWorkbookTest.class);
		suite.addTestSuite(OpenDocumentSheetTest.class);
		suite.addTestSuite(OpenDocumentWorkbookTest.class);
		suite.addTestSuite(OOXMLSheetTest.class);
		suite.addTestSuite(OOXMLWorkbookTest.class);
		//$JUnit-END$
		return suite;
    }

    public static InputStream getSpreadSheetStrean(String filename) throws Exception {
        return new FileInputStream(new File(System.getProperty("path_to_spreadsheets") + filename));
    }
}
