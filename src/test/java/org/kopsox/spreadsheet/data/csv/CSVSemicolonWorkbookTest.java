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
import org.junit.Before;
import org.kopsox.spreadsheet.SpreadsheetFactory;
import org.kopsox.spreadsheet.TestUtil;

/**
 *
 * @author Konrad Renner
 */
public class CSVSemicolonWorkbookTest extends AbstractCSVWorkbookTest {

    @Before
    @Override
    public void setUp() throws Exception {
	InputStream stream = null;
        try {
            stream = TestUtil.getSpreadSheetStream("csv_test_semicolon.csv");
            workbook = (CSVWorkbook) SpreadsheetFactory.SpreadsheetType.CSV_SEMICOLON.openWorkbook("name", stream);
        } finally {
            if (stream != null) stream.close();
        }
    }

    @Override
    public SeparatorStrategy getSeparator() {
        return SeparatorStrategy.SEMICOLON;
    }
}
