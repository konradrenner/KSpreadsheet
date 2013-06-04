/**
 * KSpreadsheet
 * Copyright (C) 2010 Free Software Foundation, Inc. <http://fsf.org/>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kopsox.spreadsheet.data.ods;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.kopsox.spreadsheet.data.Value;
import org.kopsox.spreadsheet.data.Workbook;
import org.kopsox.spreadsheet.data.common.AbstractSheet;
import org.kopsox.spreadsheet.data.common.BlankValue;
import org.kopsox.spreadsheet.style.CellStyle;
import org.kopsox.spreadsheet.util.ODFDomUtil;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableCellRange;

/**
 * 
 * @author Konrad Renner
 */
public class OpenDocumentSheet extends AbstractSheet {
	
	private final OdfTable sheet;
	
	public OpenDocumentSheet(Workbook wb, OdfTable sh, String name,int index) {
		super(wb,name,index);
		
		this.sheet = sh;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#getNumberOfLastColumn(int)
	 */
	@Override
    public int getNumberOfLastColumn(int row) {
        
            int column = this.sheet.getRowByIndex(row).getCellCount() - 1;

            //if the last column of the row is a blank, it is not the last column (implementation of the poi behavior)
            while (column > 0 && Value.Type.BLANK.equals(getValueAt(row, column).getType())) {
                column--;
            }
            return column;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#getNumberOfLastRow()
	 */
	@Override
	public int getNumberOfLastRow() {
            return this.sheet.getRowCount() - 1;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#getValueAt(int, int)
	 */
	@Override
	public Value getValueAt(int row, int column) {
		
		OdfTableCell cell = this.sheet.getCellByPosition(column, row);
		
		if(cell == null) {
			return new BlankValue();
		}
		
		return ODFDomUtil.getValueFromCell(cell);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setFormualaAt(int, int, java.lang.String)
	 */
	@Override
	public void setFormualaAt(int row, int column, String formula) {
		OdfTableCell cell = this.sheet.getCellByPosition(column, row);
		
		cell.setFormula(formula);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, java.lang.String)
	 */
	@Override
	public void setValueAt(int row, int column, String value) {
		OdfTableCell cell = this.sheet.getCellByPosition(column, row);
		
		cell.setStringValue(value);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, double)
	 */
	@SuppressWarnings("boxing")
	@Override
	public void setValueAt(int row, int column, double value) {
		OdfTableCell cell = this.sheet.getCellByPosition(column, row);
		
		cell.setDoubleValue(value);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, java.util.Date)
	 */
	@Override
	public void setValueAt(int row, int column, Date value,String format) {
		OdfTableCell cell = this.sheet.getCellByPosition(column, row);
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(value.getTime());
		cell.setFormatString(format);
		cell.setDateValue(cal);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, boolean)
	 */
	@SuppressWarnings("boxing")
	@Override
	public void setValueAt(int row, int column, boolean value) {
		OdfTableCell cell = this.sheet.getCellByPosition(column, row);
		
		cell.setBooleanValue(value);

	}
	
	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, java.sql.Time)
	 */
	@Override
	public void setValueAt(int row, int column, Time value,String format) {
		OdfTableCell cell = this.sheet.getCellByPosition(column, row);
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(value.getTime());
		cell.setFormatString(format);
		cell.setTimeValue(cal);
	}
   
   @Override
	public void mergeRegion(int firstRow, int lastRow, int firstColumn,
			int lastColumn) {
		if(regions.containsKey(new CellRegion(firstRow, lastRow, firstColumn, lastColumn))) {
			return;
		}
		
		CellRegion region = new CellRegion(firstRow, lastRow, firstColumn, lastColumn,regions.size());
		
		OdfTableCellRange range = this.sheet.getCellRangeByPosition(firstColumn, firstRow, lastColumn, lastRow);
		range.merge();
		range.setCellRangeName(Integer.toString(region.index));
		
		regions.put(region, region);
	}

	@Override
	public void umergeRegion(int firstRow, int lastRow, int firstColumn,
			int lastColumn) {
		
		CellRegion region = new CellRegion(firstRow, lastRow, firstColumn, lastColumn);
		
		if(regions.containsKey(region)) {
			//TODO
			throw new UnsupportedOperationException("unmerge for OpenDocument ist not supported at the moment");
		}
	}

	@Override
	public void autoSizeColumn(int column) {
		if(this.sheet.getColumnCount() > column) {
			this.sheet.getColumnByIndex(column).setUseOptimalWidth(true);
		}
	}

	@Override
	public void setStyleAt(int row, int column, CellStyle style) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("this methode is not supported for the moment");
	}
}
