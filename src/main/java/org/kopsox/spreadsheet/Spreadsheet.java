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
package org.kopsox.spreadsheet;

import java.io.IOException;
import java.io.InputStream;
import org.kopsox.spreadsheet.SpreadsheetFactory.SpreadsheetType;
import org.kopsox.spreadsheet.data.Workbook;

/**
 * Interface for all spreadsheets. If you want to use the built in
 * implementations (Excel, OOXML, OpenDocument, CSV) choose one of the types
 * from the enum SpreadsheetType. Spreadsheet implementations use UTF-8 as
 * encoding
  * 
 * @author Konrad Renner
 * @see SpreadsheetType
 */
public interface Spreadsheet {

	/**
	 * Creates an new Workbook for this Spreadsheet. NOTE: The name property makes an Spreadsheet unique! The name must not be the filename.
	 * 
	 * @param name 
	 * @return Workbook
	 * @throws Exception
	 * @author Konrad Renner
	 */
	public Workbook createWorkbook(String name) throws Exception;
	
	/**
	 * Loads an existing workbook from the given stream. NOTE: The name property makes an Spreadsheet unique! The name must not be the filename.
	 * 
	 * @param name 
	 * @param stream
	 * @return Workbook
	 * @throws IOException
	 * @author Konrad Renner
	 */
	public Workbook openWorkbook(String name,InputStream stream) throws IOException;
}
