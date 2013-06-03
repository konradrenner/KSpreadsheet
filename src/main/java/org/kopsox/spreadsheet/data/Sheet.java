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

package org.kopsox.spreadsheet.data;

import java.sql.Time;
import java.util.Date;

import org.kopsox.spreadsheet.style.CellStyle;

/**
 * 
 * @author Konrad Renner
 */
public interface Sheet {
	
	/**
	 * Gets the workbook which contains that sheet
	 * 
	 * @return Workbook
	 * @author Konrad Renner
	 */
	public Workbook getWorkbook();

	/**
     * Gets the index of the Sheet
     * 
     * @return int
     * @author Konrad Renner
     */
    public int getSheetIndex();
    
    /**
     * Gets the Name of the Sheet
     * 
     * @return String
     * @author Konrad Renner
     */
    public String getSheetName();
    
    /**
     * Returns the Value of a cell.<br>
     * If the cell is empty (or does not exist), a Value of the type 'BLANK' is returned
     * 
     * @param row
     * @param column
     * @return Value
     * @author Konrad Renner
     */
    public Value getValueAt(int row,int column);
    
    /**
     * Sets the value of a cell
     * 
     * @param row
     * @param column
     * @param value - String
     * @author Konrad Renner
     */
    public void setValueAt(int row,int column,String value);
    
    /**
     * Sets the value of a cell
     * 
     * @param row
     * @param column
     * @param value - double
     * @author Konrad Renner
     */
    public void setValueAt(int row,int column,double value);
    
    /**
     *  Sets the value of a cell. You must specify an format (e.g. yyyy-MM-dd)
     * 
     * @param row
     * @param column
     * @param value - Date
     * @param dateFormat
     * @author Konrad Renner
     */
    public void setValueAt(int row,int column,Date value,String dateFormat);
    
    /**
     *  Sets the value of a cell. You must specify an format (e.g. hh:mm:ss)
     * 
     * @param row
     * @param column
     * @param value - Date
     * @param timeFormat
     * @author Konrad Renner
     */
    public void setValueAt(int row,int column,Time value,String timeFormat);
    
    /**
     *  Sets the value of a cell
     * 
     * @param row
     * @param column
     * @param value - boolean
     * @author Konrad Renner
     */
    public void setValueAt(int row,int column,boolean value);
    
    /**
     *  Sets the formual of a cell
     * 
     * @param row
     * @param column
     * @param formula
     * @author Konrad Renner
     */
    public void setFormualaAt(int row,int column,String formula);
    
    /**
     * Gets the index of the last row
     * 
     * @return int
     * @author Konrad Renner
     */
    public int getNumberOfLastRow();
    
    /**
     * Gets the index of the last column, from the given row
     * 
     * @param row
     * @return int
     * @author Konrad Renner
     */
    public int getNumberOfLastColumn(int row);
    
    /**
     * Merges cells which are in the given Region
     * 
     * @param firstRow
     * @param lastRow
     * @param firstColumn
     * @param lastColumn
     * @author Konrad Renner
     */
    public void mergeRegion(int firstRow,int lastRow,int firstColumn,int lastColumn);
    
    /**
     * Unmerges cells
     * 
     * @param firstRow
     * @param lastRow
     * @param firstColumn
     * @param lastColumn
     * @author Konrad Renner
     */
    public void umergeRegion(int firstRow,int lastRow,int firstColumn,int lastColumn);
    
    /**
     * Autosizes a column
     * 
     * @param column
     * @author Konrad Renner
     */
    public void autoSizeColumn(int column);
    
    /**
     * Appends the given style to the cell at the given index
     * 
     * @param row
     * @param column
     * @param style
     * @author Konrad Renner
     */
    public void setStyleAt(int row, int column, CellStyle style);
}
