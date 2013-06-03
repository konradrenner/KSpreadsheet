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

import java.io.IOException;
import java.io.OutputStream;


/**
 * 
 * @author Konrad Renner
 */
public interface Workbook {

	/**
     * Creates a new Spreadsheet
     * 
     * @return Sheet
     */
    public Sheet createNewSheet();
    
    /**
     * Creates a new Spreadsheet with the given name
     * @param name 
     * @return Sheet
     */
    public Sheet createNewSheet(String name);
    
    /**
     * Gets a Spreadsheet by its name.<br>
     * Returns null, if the Sheet with the given name does not exist
     * 
     * @param name
     * @return Sheet
     * @author Konrad Renner
     */
    public Sheet getSheetByName(String name);
    
    /**
     * Gets a Spreadsheet by its index.<br>
     * Returns null, if the Sheet with the given name does not exist.<br>
     * The first Sheet has an index of 0
     * 
     * @param number
     * @return Sheet
     * @author Konrad Renner
     */
    public Sheet getSheetByIndex(int number);
    
    /**
     * Returns the number of sheets
     * 
     * @return int
     * @author Konrad Renner
     */
    public int getNumberOfSheets();
    
    /**
     * Gets the index of the current active Sheet
     * 
     * @return int
     * @author Konrad Renner
     */
    public int getSelectedSheetIndex();
    
    /**
     * Sets the current active Sheet
     * 
     * @param index
     * @author Konrad Renner
     */
    public void setSelectedSheet(int index);
    
    /**
     * Saves the Workbook but does not close the stream
     * 
     * @param stream
     * @throws IOException
     */
    public void save(OutputStream stream)throws IOException;
}
