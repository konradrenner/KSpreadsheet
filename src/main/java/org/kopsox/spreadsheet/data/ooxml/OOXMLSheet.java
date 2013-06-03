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

import java.sql.Time;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.kopsox.spreadsheet.data.Value;
import org.kopsox.spreadsheet.data.Workbook;
import org.kopsox.spreadsheet.data.common.AbstractSheet;
import org.kopsox.spreadsheet.data.common.BlankValue;
import org.kopsox.spreadsheet.util.POIUtil;

/**
 * 
 * @author Konrad Renner
 */
public class OOXMLSheet extends AbstractSheet {
	
	private final XSSFSheet sheet;
	
	public OOXMLSheet(Workbook wb,XSSFSheet sh,String name,int idx) {
		super(wb,name,idx);
		this.sheet = sh;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#getNumberOfLastColumn(int)
	 */
	@Override
	public int getNumberOfLastColumn(int row) {
		XSSFRow r = sheet.getRow(row);
        if(r != null){
            return r.getLastCellNum()-1;
        }
        return 0;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#getNumberOfLastRow()
	 */
	@Override
	public int getNumberOfLastRow() {
		if(sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() > 0){
            return sheet.getPhysicalNumberOfRows();
        }
        return sheet.getLastRowNum();
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#getValueAt(int, int)
	 */
	@Override
	public Value getValueAt(int row, int column) {
		XSSFRow excelRow = sheet.getRow(row);
        if(excelRow == null){
            BlankValue value = new BlankValue();
            return value;
        }
        
        XSSFCell excelCell = excelRow.getCell(column);
        if(excelCell == null){
        	BlankValue value = new BlankValue();
            return value;
        }
        
		return POIUtil.getValueFormCell(excelCell);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setFormualaAt(int, int, java.lang.String)
	 */
	@Override
	public void setFormualaAt(int row, int column, String formula) {
		XSSFRow excelRow = sheet.getRow(row);
        if(excelRow == null){
            excelRow = sheet.createRow(row);
        }
        
        XSSFCell excelCell = excelRow.getCell(column);
        if(excelCell == null){
            excelCell = excelRow.createCell(column);
        }
        
        excelCell.setCellFormula(formula);
        excelCell.setCellType(Cell.CELL_TYPE_FORMULA);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, java.lang.String)
	 */
	@Override
	public void setValueAt(int row, int column, String value) {
		XSSFRow excelRow = sheet.getRow(row);
        if(excelRow == null){
            excelRow = sheet.createRow(row);
        }
        
        XSSFCell excelCell = excelRow.getCell(column);
        if(excelCell == null){
            excelCell = excelRow.createCell(column);
        }
        
        XSSFRichTextString str = new XSSFRichTextString(value);
        
        excelCell.setCellValue(str);
        excelCell.setCellType(Cell.CELL_TYPE_STRING);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, double)
	 */
	@Override
	public void setValueAt(int row, int column, double value) {
		XSSFRow excelRow = sheet.getRow(row);
        if(excelRow == null){
            excelRow = sheet.createRow(row);
        }
        
        XSSFCell excelCell = excelRow.getCell(column);
        if(excelCell == null){
            excelCell = excelRow.createCell(column);
        }
        
        excelCell.setCellValue(value);
        excelCell.setCellType(Cell.CELL_TYPE_NUMERIC);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, java.util.Date)
	 */
	@Override
	public void setValueAt(int row, int column, Date value,String format) {
		XSSFRow excelRow = sheet.getRow(row);
        if(excelRow == null){
            excelRow = sheet.createRow(row);
        }
        
        XSSFCell excelCell = excelRow.getCell(column);
        if(excelCell == null){
            excelCell = excelRow.createCell(column);
        }
        
        CreationHelper createHelper = this.sheet.getWorkbook().getCreationHelper();
        CellStyle cellStyle =  this.sheet.getWorkbook().createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(format));
        excelCell.setCellStyle(cellStyle);
        
        excelCell.setCellValue(value);
        excelCell.setCellType(Cell.CELL_TYPE_NUMERIC);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, boolean)
	 */
	@Override
	public void setValueAt(int row, int column, boolean value) {
		XSSFRow excelRow = sheet.getRow(row);
        if(excelRow == null){
            excelRow = sheet.createRow(row);
        }
        
        XSSFCell excelCell = excelRow.getCell(column);
        if(excelCell == null){
            excelCell = excelRow.createCell(column);
        }
        
        excelCell.setCellValue(value);
        excelCell.setCellType(Cell.CELL_TYPE_BOOLEAN);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#setValueAt(int, int, java.sql.Time)
	 */
	@Override
	public void setValueAt(int row, int column, Time value,String format) {
		XSSFRow excelRow = sheet.getRow(row);
        if(excelRow == null){
            excelRow = sheet.createRow(row);
        }
        
        XSSFCell excelCell = excelRow.getCell(column);
        if(excelCell == null){
            excelCell = excelRow.createCell(column);
        }
        
        CreationHelper createHelper = this.sheet.getWorkbook().getCreationHelper();
        CellStyle cellStyle =  this.sheet.getWorkbook().createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(format));
        excelCell.setCellStyle(cellStyle);
        
        excelCell.setCellValue(value);
        excelCell.setCellType(Cell.CELL_TYPE_NUMERIC);
	}
   
   @Override
	public void mergeRegion(int firstRow, int lastRow, int firstColumn,
			int lastColumn) {

		if(regions.containsKey(new CellRegion(firstRow,lastRow,firstColumn,lastColumn))) {
			return;
		}
		
		CellRangeAddress adress = new CellRangeAddress(firstRow,lastRow,firstColumn,lastColumn);
		int index = this.sheet.addMergedRegion(adress);
		
		CellRegion region = new CellRegion(firstRow,lastRow,firstColumn,lastColumn,index);
		
		regions.put(region, region);
	}

	@Override
	public void umergeRegion(int firstRow, int lastRow, int firstColumn,
			int lastColumn) {
		CellRegion region = new CellRegion(firstRow,lastRow,firstColumn,lastColumn);
		
		if(regions.containsKey(region)) {
			this.sheet.removeMergedRegion(region.index);
			regions.remove(region);
		}
	}

	@Override
	public void autoSizeColumn(int column) {
		this.sheet.autoSizeColumn(column);
	}

	@Override
	public void setStyleAt(int row, int column,org.kopsox.spreadsheet.style.CellStyle style) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("this methode is not supported for the moment");
	}
}
