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

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * This enum provides a set of horizontal alignment options, which can be used for the built in spreadsheet-types
 * 
 * @author Konrad Renner
 *
 */
public enum CellStyleHorizontalAlignment {

	LEFT("xxx",CellStyle.ALIGN_LEFT),
	RIGHT("xxx",CellStyle.ALIGN_RIGHT),
	CENTER("xxx",CellStyle.ALIGN_CENTER);
	
	private final String openDocumentHorizontalAlignment;
	private final short excelHorizontalAlignment;
	
	private CellStyleHorizontalAlignment(String ods,short excel){
		this.openDocumentHorizontalAlignment = ods;
		this.excelHorizontalAlignment = excel;
	}

	public String getOpenDocumentHorizontalAlignment() {
		return openDocumentHorizontalAlignment;
	}

	public short getExcelHorizontalAlignment() {
		return excelHorizontalAlignment;
	}
}
