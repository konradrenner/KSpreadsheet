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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
        boolean done = init.init(aSheet);
        if (done) {
            this.sheet = aSheet;
        }
    }

    public SeparatorStrategy getSeparator() {
        return separator;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CSVWorkbook other = (CSVWorkbook) obj;
        return true;
    }

    @Override
    public String toString() {
        return "CSVWorkbook{" + "separator=" + separator + ", sheet=" + sheet + '}';
    }

    @Override
    public Sheet createNewSheet() {
        if (this.sheet == null) {
            this.sheet = new CSVSheet(this, "sheet", 0);
            return this.sheet;
        }
        throw new IllegalStateException("Workbook contains already a sheet");
    }
    @Override
    public Sheet createNewSheet(String name) {
        if (this.sheet == null) {
            this.sheet = new CSVSheet(this, "name", 0);
            return this.sheet;
        }
        throw new IllegalStateException("Workbook contains already a sheet");
    }
    @Override
    public Sheet getSheetByName(String name) {
        return this.sheet;
    }
    @Override
    public Sheet getSheetByIndex(int number) {
        return this.sheet;
    }
    @Override
    public int getNumberOfSheets() {
        if (this.sheet == null) {
            return 0;
        }
        return 1;
    }
    @Override
    public int getSelectedSheetIndex() {
        if (this.sheet == null) {
            return -1;
        }
        return 0;
    }
    @Override
    public void setSelectedSheet(int index) {
        throw new UnsupportedOperationException("Not supported yet for CSV");
    }
    @Override
    public void save(OutputStream stream) throws IOException {
        if (this.sheet == null) {
            throw new IllegalStateException("No sheet created");
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream))) {
            int rows = this.sheet.getNumberOfLastRow();
            int maxColumns = this.sheet.getAbsoluteLastColumnIndex();

            for (int i = 0; i < rows; i++) {
                StringBuilder row = new StringBuilder();
                for (int x = 0; x < maxColumns; x++) {
                    row.append(this.sheet.getValueAt(i, x).asString());

                    if ((x + 1) < maxColumns) {
                        row.append(this.separator.getSymbol());
                    }
                }

                writer.append(row);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
