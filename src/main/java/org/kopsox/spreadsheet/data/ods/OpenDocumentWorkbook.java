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

import java.io.IOException;
import java.io.OutputStream;
import org.kopsox.spreadsheet.data.Sheet;
import org.kopsox.spreadsheet.data.common.AbstractWorkbook;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

/**
 * Wrapper around the OFDOM-Toolkit
 * 
 * @author Konrad Renner
 */
public class OpenDocumentWorkbook extends AbstractWorkbook {

	private final SpreadsheetDocument workbook;
	private int indexOfSelectedSheet;
	
	public OpenDocumentWorkbook(final String name,final SpreadsheetDocument wb) {
		super(name);
		this.workbook = wb;
		this.indexOfSelectedSheet = 0;
	}
	
	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#createNewSheet()
	 */
	@Override
	public Sheet createNewSheet() {
            Table newSheet = Table.newTable(this.workbook);
            if (newSheet == null) {
                throw new IllegalStateException("Could not create new Table");
            }
		
		OpenDocumentSheet sheet = new OpenDocumentSheet(this,newSheet,newSheet.getTableName(),this.workbook.getTableList().size()-1);
		
		registerSheet(newSheet.getTableName(), sheet);
		return sheet;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#createNewSheet(java.lang.String)
	 */
	@Override
	public Sheet createNewSheet(String name) {
            Table newSheet = Table.newTable(this.workbook);
            if (newSheet == null) {
                throw new IllegalStateException("Could not create new Table");
            }

		newSheet.setTableName(name);
		
		OpenDocumentSheet sheet = new OpenDocumentSheet(this,newSheet,name,this.workbook.getTableList().size()-1);
		
		registerSheet(name, sheet);
		return sheet;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#getNumberOfSheets()
	 */
	@Override
	public int getNumberOfSheets() {
		return this.workbook.getTableList().size();
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#getSelectedSheetIndex()
	 */
	@Override
	public int getSelectedSheetIndex() {
		return this.indexOfSelectedSheet;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#getSheetByName(java.lang.String)
	 */
	@Override
	public Sheet getSheetByName(String name) {
		
		if(!containsSheet(name)) {
			for(int number=0;number<this.workbook.getTableList().size();number++) {
				
				if(this.workbook.getTableList().get(number).getTableName().equals(name)) {
					return getSheet(name,number);
				}
			}
		}
		
		return getSheet(name);
	}
	
	private final Sheet getSheet(String name,int number) {
		if(!containsSheet(name)) {
			Table odfsheet = this.workbook.getTableByName(name);
			
			OpenDocumentSheet sheet = new OpenDocumentSheet(this,odfsheet,name,number);
			
			registerSheet(name, sheet);
		}
		
		return getSheet(name);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#getSheetByIndex(int)
	 */
	@Override
	public Sheet getSheetByIndex(int number) {
		
		Table sheet = this.workbook.getTableList().get(number);
		
		return getSheet(sheet.getTableName(),number);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#setSelectedSheet(int)
	 */
	@Override
	public void setSelectedSheet(int index) {
		this.indexOfSelectedSheet = index;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Workbook#save(OutputStream)
	 */
	@Override
	public void save(OutputStream stream) throws IOException {
		if(stream != null){
		    try {
				workbook.save(stream);
			} catch (Exception e) {
				throw new IOException(e);
			}
		}else{
		    throw new IllegalArgumentException("Stream is null, so it is not possible to save the content");
		}
	}
}
