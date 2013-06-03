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
package org.kopsox.spreadsheet.style;

/**
 * This enum contains a set of border-looks. E.g.: the width of the border
 * 
 * @author Konrad Renner
 *
 */
public enum CellStyleBorderLook {

	NONE("xxx",org.apache.poi.ss.usermodel.CellStyle.BORDER_NONE),
	MEDIUM("xxx",org.apache.poi.ss.usermodel.CellStyle.BORDER_MEDIUM),
	THIN("xxx",org.apache.poi.ss.usermodel.CellStyle.BORDER_THIN),
	THICK("xxx",org.apache.poi.ss.usermodel.CellStyle.BORDER_THICK),
	DOUBLE_LINE("xxx",org.apache.poi.ss.usermodel.CellStyle.BORDER_DOUBLE);
	
	private final String openDocumentBorderLook;
	private final short excelBorderLook;
	
	private CellStyleBorderLook(String ods, short excel) {
		this.openDocumentBorderLook = ods;
		this.excelBorderLook = excel;
	}

	public String getOpenDocumentBorderLook() {
		return openDocumentBorderLook;
	}

	public short getExcelBorderLook() {
		return excelBorderLook;
	}
}
