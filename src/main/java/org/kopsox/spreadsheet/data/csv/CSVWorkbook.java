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

import java.io.IOException;
import java.io.OutputStream;
import org.kopsox.spreadsheet.data.Sheet;
import org.kopsox.spreadsheet.data.common.AbstractWorkbook;

/**
 *
 * @author Konrad Renner
 */
public class CSVWorkbook extends AbstractWorkbook {
    private final SeparatorStrategy separator;
    //CSV Workbooks consists of just one sheet
    private CSVSheet sheet;

    public CSVWorkbook(String name, CSVInitializer init) {
        super(name);
        this.separator = init.getStrategy();
        CSVSheet aSheet = new CSVSheet(this, name, 0);
        boolean done = init.init(sheet);
        if (done) {
            this.sheet = aSheet;
        }
    }

    public SeparatorStrategy getSeparator() {
        return separator;
    }

    public Sheet createNewSheet() {
        if (this.sheet == null) {
            this.sheet = new CSVSheet(this, "sheet", 0);
            return this.sheet;
        }
        throw new IllegalStateException("Workbook contains already a sheet");
    }

    public Sheet createNewSheet(String name) {
        if (this.sheet == null) {
            this.sheet = new CSVSheet(this, "name", 0);
            return this.sheet;
        }
        throw new IllegalStateException("Workbook contains already a sheet");
    }

    public Sheet getSheetByName(String name) {
        return this.sheet;
    }

    public Sheet getSheetByIndex(int number) {
        return this.sheet;
    }

    public int getNumberOfSheets() {
        return 1;
    }

    public int getSelectedSheetIndex() {
        return 0;
    }

    public void setSelectedSheet(int index) {
        throw new UnsupportedOperationException("Not supported yet for CSV");
    }

    public void save(OutputStream stream) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
