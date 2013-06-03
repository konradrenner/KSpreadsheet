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
package org.kopsox.spreadsheet.data.ooxml;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kopsox.spreadsheet.data.Sheet;
import org.kopsox.spreadsheet.data.common.AbstractWorkbook;

/**
 * 
 * @author Konrad Renner
 */
public class OOXMLWorkbook extends AbstractWorkbook {
	
	private final XSSFWorkbook workbook;
	
	public OOXMLWorkbook(final String name,final XSSFWorkbook wb) {
		super(name);
		this.workbook = wb;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#createNewSheet()
	 */
	@Override
	public Sheet createNewSheet() {
		
		XSSFSheet newSheet = workbook.createSheet();
		
		OOXMLSheet es = new OOXMLSheet(this,newSheet,newSheet.getSheetName(),workbook.getSheetIndex(newSheet));
		
		registerSheet(newSheet.getSheetName(), es);
		
		return es;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#createNewSheet(java.lang.String)
	 */
	@Override
	public Sheet createNewSheet(String name) {
		XSSFSheet newSheet = workbook.createSheet(name);
		
		OOXMLSheet es = new OOXMLSheet(this,newSheet,newSheet.getSheetName(),workbook.getSheetIndex(newSheet));
		
		registerSheet(name, es);
		
		return es;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#getNumberOfSheets()
	 */
	@Override
	public int getNumberOfSheets() {
		return workbook.getNumberOfSheets();
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#getSelectedSheetIndex()
	 */
	@Override
	public int getSelectedSheetIndex() {
		return workbook.getActiveSheetIndex();
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#getSheetByName(java.lang.String)
	 */
	@Override
	public Sheet getSheetByName(String name) {
		
		if(!containsSheet(name)) {
			XSSFSheet sheet = workbook.getSheet(name);
			
			if(sheet == null) {
				return null;
			}
			
			registerSheet(name, new OOXMLSheet(this,sheet,sheet.getSheetName(),workbook.getSheetIndex(sheet)));
		}
		
		return getSheet(name);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#getSheetByIndex(int)
	 */
	@Override
	public Sheet getSheetByIndex(int number) {
		
		String name = workbook.getSheetName(number);
		
		return getSheetByName(name);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#setSelectedSheet(int)
	 */
	@Override
	public void setSelectedSheet(int index) {
		workbook.setActiveSheet(index);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#save(java.io.OutputStream)
	 */
	@Override
	public void save(OutputStream stream) throws IOException {
		if(stream != null){
		    workbook.write(stream);
		}else{
		    throw new IllegalArgumentException("Stream is null, so it is not possible to save the content");
		}
	}
}
