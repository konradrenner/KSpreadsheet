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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static org.junit.Assert.*;
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
public abstract class AbstractCSVWorkbookTest {

    CSVWorkbook workbook;


    public abstract void setUp() throws Exception;

    public abstract SeparatorStrategy getSeparator();

    @Test
    public void testGetSeparator() {
        assertEquals(getSeparator().getSymbol(), this.workbook.getSeparator().getSymbol());
    }

    @Test
    public void testCreateWorkbookWithSheet() throws Exception {

        Workbook book;
        if (getSeparator().equals(SeparatorStrategy.SEMICOLON)) {
            book = SpreadsheetFactory.SpreadsheetType.CSV_SEMICOLON.createWorkbook("Test");
        } else {
            book = SpreadsheetFactory.SpreadsheetType.CSV_COMMA.createWorkbook("Test");
        }

        assertEquals(0, book.getNumberOfSheets());
        assertEquals(-1, book.getSelectedSheetIndex());

        book.createNewSheet();

        assertEquals(1, book.getNumberOfSheets());
        assertEquals(0, book.getSelectedSheetIndex());

        book.getSheetByIndex(0).setValueAt(3, 3, "Test");

        File newFile = new File("newCSV.csv");

        OutputStream stream = null;
        try {
            stream = new FileOutputStream(newFile);
            book.save(stream);
        } finally {
            stream.close();
        }

        InputStream istream = null;
        try {
            istream = new FileInputStream(newFile);
            book = SpreadsheetFactory.SpreadsheetType.CSV_COMMA.openWorkbook("name", istream);
            assertNotNull(book);
            String toCheck = book.getSheetByIndex(0).getValueAt(3, 3).asString();
            assertEquals("Test", toCheck);
            assertEquals(new BlankValue(), book.getSheetByIndex(0).getValueAt(1, 2));
        } finally {
            if (istream != null) istream.close();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateNewSheet_0args() {
        this.workbook.createNewSheet();
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateNewSheet_String() {
        this.workbook.createNewSheet("Testing");
    }

    @Test
    public void testGetSheetByName() {
        assertNotNull(this.workbook.getSheetByName("TEST"));
        assertEquals(new StringValue("zelle41"), this.workbook.getSheetByName("TEST").getValueAt(4, 1));
    }

    @Test
    public void testGetSheetByIndex() {
        assertNotNull(this.workbook.getSheetByIndex(7));
        assertEquals(new StringValue("zelle41"), this.workbook.getSheetByIndex(7).getValueAt(4, 1));
    }

    @Test
    public void testGetNumberOfSheets() {
        assertEquals(1, this.workbook.getNumberOfSheets());
    }

    @Test
    public void testGetSelectedSheetIndex() {
        assertEquals(0, this.workbook.getSelectedSheetIndex());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetSelectedSheet() {
        this.workbook.setSelectedSheet(4);
    }

}