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

import java.sql.Time;

import org.kopsox.spreadsheet.data.Value;
import org.kopsox.spreadsheet.data.common.BlankValue;
import org.kopsox.spreadsheet.data.common.BooleanValue;
import org.kopsox.spreadsheet.data.common.DateValue;
import org.kopsox.spreadsheet.data.common.DoubleValue;
import org.kopsox.spreadsheet.data.common.TimeValue;
import org.odftoolkit.odfdom.dom.attribute.office.OfficeValueTypeAttribute;
import org.odftoolkit.simple.table.Cell;


/**
 * Utility class for the usage of the ODFDom-Jar
 * 
 * @author Konrad Renner
 */
public final class ODFDomUtil {

	private ODFDomUtil() {
		//Utility class
	}
	
	/**
	 * Gets a Value from the given Cell
	 * 
	 * @param cell
	 * @return Value
	 */
	public static final Value getValueFromCell(Cell cell) {
		
		if(cell == null) {
			return new BlankValue();
		}
		
		//FLOAT
		if(OfficeValueTypeAttribute.Value.FLOAT.toString().equalsIgnoreCase(cell.getValueType())) {
			
			DoubleValue value = new DoubleValue(cell.getDoubleValue());
			value.setFormula(cell.getFormula());
			
			return value;
			
		//CURRENCY
		}else if(OfficeValueTypeAttribute.Value.CURRENCY.toString().equalsIgnoreCase(cell.getValueType())) {
			
			DoubleValue value = new DoubleValue(cell.getCurrencyValue());
			value.setFormula(cell.getFormula());
			
			return value;
			
		//PERCENTAGE
		}else if(OfficeValueTypeAttribute.Value.PERCENTAGE.toString().equalsIgnoreCase(cell.getValueType())) {
			
			DoubleValue value = new DoubleValue(cell.getPercentageValue());
			value.setFormula(cell.getFormula());

			return value;

		//STRING
		}else if(OfficeValueTypeAttribute.Value.STRING.toString().equalsIgnoreCase(cell.getValueType())) {
			
			org.kopsox.spreadsheet.data.common.StringValue value = new org.kopsox.spreadsheet.data.common.StringValue(cell.getStringValue());
			value.setFormula(cell.getFormula());
			
			return value;
			
		//DATE
		}else if(OfficeValueTypeAttribute.Value.DATE.toString().equalsIgnoreCase(cell.getValueType())) {
			
			DateValue value = new DateValue(cell.getDateValue()==null?null:cell.getDateValue().getTime());
			value.setFormula(cell.getFormula());
			
			return value;
			
		//TIME
		}else if(OfficeValueTypeAttribute.Value.TIME.toString().equalsIgnoreCase(cell.getValueType())) {
			
			TimeValue value = new TimeValue(cell.getTimeValue()==null?null:new Time(cell.getTimeValue().getTime().getTime()));
			value.setFormula(cell.getFormula());
			
			return value;
			
		//BOOLEAN
		}else if(OfficeValueTypeAttribute.Value.BOOLEAN.toString().equalsIgnoreCase(cell.getValueType())) {
			BooleanValue value = new BooleanValue(cell.getBooleanValue());
			value.setFormula(cell.getFormula());
			
			return value;
		}
		
		
		return new BlankValue(cell.getFormula());
	}


}
