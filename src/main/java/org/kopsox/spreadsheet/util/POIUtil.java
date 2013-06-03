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
package org.kopsox.spreadsheet.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.kopsox.spreadsheet.data.Value;
import org.kopsox.spreadsheet.data.common.BlankValue;
import org.kopsox.spreadsheet.data.common.BooleanValue;
import org.kopsox.spreadsheet.data.common.DateValue;
import org.kopsox.spreadsheet.data.common.DoubleValue;
import org.kopsox.spreadsheet.data.common.StringValue;

/**
 * Utility class for the usage of the Apache POI-Jar
 * 
 * @author Konrad Renner
 */
public final class POIUtil {
	
	private POIUtil() {
		//Utility class
	}

	/**
	 * Gets a Value from the given Cell
	 * 
	 * @param cell
	 * @return Value
	 */
	@SuppressWarnings("boxing")
	public static final Value getValueFormCell(Cell cell){
        
        
        //NUMERIC
        if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
            //Numeric can be a Date or a Double
            
            return getValueFromNumeric(cell,null);
            
        }//STRING
        else if(Cell.CELL_TYPE_STRING == cell.getCellType()){
            
            return new StringValue(cell.getRichStringCellValue().getString());
        }//FORMEL
        else if(Cell.CELL_TYPE_FORMULA == cell.getCellType()){
            String formula = cell.getCellFormula();
            
            return getValueFromFormula(formula,cell);
        }//BOOLEAN
        else if(Cell.CELL_TYPE_BOOLEAN == cell.getCellType()){
            
            return new BooleanValue(cell.getBooleanCellValue());
        }//BLANK
        else if(Cell.CELL_TYPE_BLANK == cell.getCellType()){
            
            return new BlankValue();
        }//ERROR
        else if(Cell.CELL_TYPE_ERROR == cell.getCellType()){
        	//TODO
        	//NOT SUPPORTED AT THE MOMENT
        }
        
        return new BlankValue();
    }
	
	@SuppressWarnings("boxing")
	private static final Value getValueFromNumeric(Cell cell, String formula){
        
        Value ret = null;
        
        //Numeric can be a Date or a Double
        if(!DateUtil.isCellDateFormatted(cell)){
            DoubleValue tmp = new DoubleValue(cell.getNumericCellValue());
            tmp.setFormula(formula);
            ret = tmp;
            
        }else{
            DateValue tmp = new DateValue(cell.getDateCellValue());
            tmp.setFormula(formula);
            ret = tmp;
            
        }
        
        return ret;
    }
	
	@SuppressWarnings("boxing")
	private static final Value getValueFromFormula(String formula, Cell cell){
        
		Value ret = null;
        
        if(Cell.CELL_TYPE_NUMERIC == cell.getCachedFormulaResultType()){
            ret = getValueFromNumeric(cell,formula);
            
        }else if(Cell.CELL_TYPE_STRING == cell.getCachedFormulaResultType()){
            StringValue tmp = new StringValue(cell.getRichStringCellValue().getString());
            tmp.setFormula(formula);
            ret = tmp;
            
        }else if(Cell.CELL_TYPE_BOOLEAN == cell.getCachedFormulaResultType()){
            BooleanValue tmp = new BooleanValue(cell.getBooleanCellValue());
            tmp.setFormula(formula);
            ret = tmp;
            
        }
        
        return ret;
    }
}
