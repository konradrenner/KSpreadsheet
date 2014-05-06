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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kopsox.spreadsheet.data.Workbook;
import org.kopsox.spreadsheet.data.excel.ExcelWorkbook;
import org.kopsox.spreadsheet.data.ods.OpenDocumentWorkbook;
import org.kopsox.spreadsheet.data.ooxml.OOXMLWorkbook;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;

/**
 * Factory-class which loads or creates Spreadsheets.<br>
 * 
 * @author Konrad Renner
 */
public final class SpreadsheetFactory {
	
	public enum SpreadsheetType implements Spreadsheet{
            EXCEL {

                    @Override
                public Workbook createWorkbook(String name) throws Exception {
                    return new ExcelWorkbook(name, new HSSFWorkbook());
                }

                @SuppressWarnings("synthetic-access")
                @Override
                public Workbook openWorkbook(String name, InputStream stream) throws IOException {
                    return openExcelWorkbook(name, stream);
                }

            }, OOXML {
                @Override
                public Workbook createWorkbook(String name) throws Exception {
                    return new OOXMLWorkbook(name, new XSSFWorkbook());
                }

                @SuppressWarnings("synthetic-access")
                @Override
                public Workbook openWorkbook(String name, InputStream stream) throws IOException {
                    return openOOXMLWorkbook(name, stream);
                }
            }, OPENDOCUMENT {
                @Override
                public Workbook createWorkbook(String name) throws Exception {
                    return new OpenDocumentWorkbook(name, OdfSpreadsheetDocument.newSpreadsheetDocument());
                }

                    @SuppressWarnings("synthetic-access")
                    @Override
                    public Workbook openWorkbook(String name, InputStream stream) throws IOException {
                        return openOpenDocumentWorkbook(name, stream);
                    }
            }, CSV_COMMA {
                @Override
                public Workbook createWorkbook(String name) throws Exception {
                    throw new UnsupportedOperationException("not yet implemented");
                }

                @SuppressWarnings("synthetic-access")
                @Override
                public Workbook openWorkbook(String name, InputStream stream) throws IOException {
                    throw new UnsupportedOperationException("not yet implemented");
                }
            }, CSV_SEMICOLON {
                @Override
                public Workbook createWorkbook(String name) throws Exception {
                    throw new UnsupportedOperationException("not yet implemented");
                }

                @SuppressWarnings("synthetic-access")
                @Override
                public Workbook openWorkbook(String name, InputStream stream) throws IOException {
                    throw new UnsupportedOperationException("not yet implemented");
                }
            };
	}

	/**
	 * Opens a Spreadsheet-Workbook depending on the Spreadsheet(-Type).<br>
	 * You can use your own implementation of Spreadsheet, or use one of the built in Spreasheet-Types
	 * @param name 
	 * @param str
	 * @param spreadSheet
	 * @return Workbook
	 * @throws IOException 
	 */
	public static Workbook openWorkbook(String name,InputStream str,Spreadsheet spreadSheet) throws IOException {
		return spreadSheet.openWorkbook(name,str);
	}
	
	/**
	 * Creates a new Spreadsheet-Workbook of the given type.<br>
	 * You can use your own implementation of Spreadsheet, or use one of the built in Spreasheet-Types
	 * @param name 
	 * @param spreadSheet
	 * @return Workbook
	 * @throws Exception 
	 */
	public static Workbook createWorkbook(String name,Spreadsheet spreadSheet) throws Exception {
		return spreadSheet.createWorkbook(name);
	}
	
	private static Workbook openExcelWorkbook(String name,InputStream str) throws IOException {
		return new ExcelWorkbook(name,new HSSFWorkbook(str));
	}
	
	private static Workbook openOOXMLWorkbook(String name,InputStream str) throws IOException {
		return new OOXMLWorkbook(name,new XSSFWorkbook(str));
	}
	
	private static Workbook openOpenDocumentWorkbook(String name,InputStream str) throws IOException {
		try {
                    return new OpenDocumentWorkbook(name, (OdfSpreadsheetDocument) OdfSpreadsheetDocument.loadDocument(str));
		} catch (Exception e) {
			throw new IOException(e);
		}
	}
}
