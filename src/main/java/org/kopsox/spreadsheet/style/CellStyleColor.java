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

import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * This enum provides a set of colors, which can be used for the built in spreadsheet-types
 * 
 * @author Konrad Renner
 *
 */
public enum CellStyleColor {

	BLACK("xxx",IndexedColors.BLACK),
	WHITE("xxx",IndexedColors.WHITE),
	RED("xxx",IndexedColors.RED),
	BLUE("xxx",IndexedColors.BLUE),
	GREEN("xxx",IndexedColors.GREEN),
	AQUA("xxx",IndexedColors.AQUA),
	BROWN("xxx",IndexedColors.BROWN),
	DARK_BLUE("xxx",IndexedColors.DARK_BLUE),
	DARK_GREEN("xxx",IndexedColors.DARK_GREEN),
	DARK_RED("xxx",IndexedColors.DARK_RED),
	DARK_YELLOW("xxx",IndexedColors.DARK_YELLOW),
	VIOLET("xxx",IndexedColors.VIOLET),
	ROSE("xxx",IndexedColors.ROSE),
	GOLD("xxx",IndexedColors.GOLD),
	LIGHT_BLUE("xxx",IndexedColors.LIGHT_BLUE),
	LIGHT_GREEN("xxx",IndexedColors.LIGHT_GREEN),
	LIGHT_ORANGE("xxx",IndexedColors.LIGHT_ORANGE),
	GREY_25_PERCENT("xxx",IndexedColors.GREY_25_PERCENT),
	GREY_40_PERCENT("xxx",IndexedColors.GREY_40_PERCENT),
	GREY_50_PERCENT("xxx",IndexedColors.GREY_50_PERCENT),
	GREY_80_PERCENT("xxx",IndexedColors.GREY_80_PERCENT),
	PINK("xxx",IndexedColors.PINK),
	ORANGE("xxx",IndexedColors.ORANGE),
	YELLOW("xxx",IndexedColors.YELLOW);
	
	private final String openDocumentColor;
	private final IndexedColors excelColor;
	
	private CellStyleColor(String openDocument, IndexedColors excelColor) {
		this.openDocumentColor = openDocument;
		this.excelColor = excelColor;
	}

	public String getOpenDocumentColor() {
		return openDocumentColor;
	}

	public IndexedColors getExcelColor() {
		return excelColor;
	}
}
