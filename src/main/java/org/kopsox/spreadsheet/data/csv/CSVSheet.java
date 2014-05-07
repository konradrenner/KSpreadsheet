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

import java.sql.Time;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import org.kopsox.spreadsheet.data.Value;
import org.kopsox.spreadsheet.data.common.AbstractSheet;
import org.kopsox.spreadsheet.data.common.BlankValue;
import org.kopsox.spreadsheet.data.common.StringValue;
import org.kopsox.spreadsheet.style.CellStyle;

/**
 *
 * @author Konrad Renner
 */
public class CSVSheet extends AbstractSheet {

    private final SortedMap<CellID, Value> values;
    private int absoluteLastColumnIndex;

    public CSVSheet(CSVWorkbook workbook, String nam, int idx) {
        super(workbook, nam, idx);
        values = new TreeMap<CellID, Value>();
        absoluteLastColumnIndex = 0;
    }

    @Override
    public CSVWorkbook getWorkbook() {
        return (CSVWorkbook) super.workbook; //To change body of generated methods, choose Tools | Templates.
    }

    public Value getValueAt(int row, int column) {
        CellID id = new CellID(row, column);
        Value value = values.get(id);

        if (value == null) {
            value = new BlankValue();
        }
        return value;
    }
    
    void setValueAt(CellID id, Value value) {
        Objects.requireNonNull(id, "Row and Column must not be null");
        Objects.requireNonNull(value, "Value must not be null");

        this.values.put(id, value);
        if (id.getColumn() > this.absoluteLastColumnIndex) {
            this.absoluteLastColumnIndex = id.getColumn();
        }
    }

    public void setValueAt(int row, int column, String value) {
        Value val = value == null ? new BlankValue() : new StringValue(value);
        
        setValueAt(new CellID(row, column), val);
    }

    public void setValueAt(int row, int column, double value) {
        Value val = new StringValue(NumberFormat.getInstance().format(value));

        setValueAt(new CellID(row, column), val);
    }

    public void setValueAt(int row, int column, Date value, String dateFormat) {
        DateFormat format = dateFormat == null ? DateFormat.getDateInstance() : new SimpleDateFormat(dateFormat);
        Value val = value == null ? new BlankValue() : new StringValue(format.format(value));
        
        setValueAt(new CellID(row, column), val);
    }

    public void setValueAt(int row, int column, Time value, String timeFormat) {
        DateFormat format = timeFormat == null ? DateFormat.getTimeInstance() : new SimpleDateFormat(timeFormat);
        Value val = value == null ? new BlankValue() : new StringValue(format.format(value));

        setValueAt(new CellID(row, column), val);
    }

    public void setValueAt(int row, int column, boolean value) {
        setValueAt(new CellID(row, column), new StringValue(Boolean.toString(value)));
    }

    public void setFormualaAt(int row, int column, String formula) {
        throw new UnsupportedOperationException("Not supported for CSV Sheet");
    }

    public int getNumberOfLastRow() {
        return this.values.lastKey().getRow();
    }

    public int getNumberOfLastColumn(int row) {
        SortedMap<CellID, Value> subMap = this.values.subMap(new CellID(row, 0), new CellID(row + 1, 0));

        return subMap.lastKey().getColumn();
    }

    public void mergeRegion(int firstRow, int lastRow, int firstColumn, int lastColumn) {
        throw new UnsupportedOperationException("Not supported for CSV Sheet");
    }

    public void umergeRegion(int firstRow, int lastRow, int firstColumn, int lastColumn) {
        throw new UnsupportedOperationException("Not supported for CSV Sheet");
    }

    public void autoSizeColumn(int column) {
        throw new UnsupportedOperationException("Not supported for CSV Sheet");
    }

    public void setStyleAt(int row, int column, CellStyle style) {
        throw new UnsupportedOperationException("Not supported for CSV Sheet");
    }

    static final class CellID implements Comparable<CellID> {

        private final int row;
        private final int column;

        public CellID(int r, int c) {
            this.row = r;
            this.column = c;
        }

        public int compareTo(CellID o) {
            if (this.equals(o)) {
                return 0;
            }
            int compare = row - o.getRow();
            if (compare == 0) {
                compare = column - o.getColumn();
            }
            return compare;
        }


        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + this.row;
            hash = 97 * hash + this.column;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final CellID other = (CellID) obj;
            if (this.row != other.row) {
                return false;
            }
            if (this.column != other.column) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "CellID{" + "row=" + row + ", column=" + column + '}';
        }
    }
}
