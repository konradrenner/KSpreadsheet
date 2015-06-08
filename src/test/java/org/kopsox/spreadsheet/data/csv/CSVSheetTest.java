/*
 * Copyright (C) 2014 Konrad Renner.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.kopsox.spreadsheet.data.csv;

import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.kopsox.spreadsheet.SpreadsheetFactory;
import org.kopsox.spreadsheet.TestUtil;
import org.kopsox.spreadsheet.data.Workbook;
import org.kopsox.spreadsheet.data.common.BlankValue;
import org.kopsox.spreadsheet.data.common.StringValue;

/**
 *
 * @author Konrad Renner
 */
public class CSVSheetTest {

    CSVSheet sheet;

    @Before
    public void setUp() throws Exception {
        Workbook workbook;
        InputStream stream = null;
        try {
            stream = TestUtil.getSpreadSheetStream("csv_test_comma.csv");
            workbook = SpreadsheetFactory.SpreadsheetType.CSV_COMMA.openWorkbook("name", stream);
        } finally {
            if (stream != null) stream.close();
        }
        this.sheet = (CSVSheet) workbook.getSheetByIndex(0);
    }

    @Test
    public void testGetAbsoluteLastColumnIndex() {
        assertEquals(3, this.sheet.getAbsoluteLastColumnIndex());
    }

    @Test
    public void testGetValueAt() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(this.sheet.getValueAt(0, 0).asDate());
        assertEquals(2014, cal.get(Calendar.YEAR));
        assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals("zelle41", this.sheet.getValueAt(4, 1).asString());
        assertEquals(new BlankValue(), this.sheet.getValueAt(6, 7));
        assertEquals(new Double(0.1), this.sheet.getValueAt(18, 2).asDouble());
    }

    @Test
    public void testSetValueAt_CSVSheetCellID_Value() {
        CSVSheet.CellID expectedId = new CSVSheet.CellID(21, 30);
        StringValue expectedValue = new StringValue("TEST");
        this.sheet.setValueAt(expectedId, expectedValue);

        assertEquals(expectedValue, this.sheet.getValueAt(expectedId.getRow(), expectedId.getColumn()));
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testSetFormualaAt() {
        this.sheet.setFormualaAt(3, 2, "MIN(A1:B2)");
    }

    @Test
    public void testGetNumberOfLastRow() {
        assertEquals(19, this.sheet.getNumberOfLastRow());
    }

    @Test
    public void testGetNumberOfLastColumn() {
        assertEquals(3, this.sheet.getNumberOfLastColumn(0));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMergeRegion() {
        this.sheet.mergeRegion(0, 2, 0, 2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUmergeRegion() {
        this.sheet.umergeRegion(0, 2, 0, 2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAutoSizeColumn() {
        this.sheet.autoSizeColumn(5);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetStyleAt() {
        this.sheet.setStyleAt(0, 0, null);
    }
}