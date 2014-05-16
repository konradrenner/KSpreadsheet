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
package org.kopsox.spreadsheet.data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Represents a Value from a spreadsheet cell, the compareTo-Method uses the
 * String representation of the Value for the natural ordering. Note: just the
 * asString method guarantees, that you will get the String representation of
 * the internal value, the toString method may create a String a technical
 * representation of a subclass (e.g. for logging)
 *
 * @author Konrad Renner
 *
 */
public interface Value extends Serializable, Comparable<Value> {

	enum Type{
		BLANK,STRING,DATE,BOOLEAN,TIME,DOUBLE;
	}
	
	/**
     * Gets the Type of the Value
     * 
     * @return int
     */
    public Type getType();
    
    /**
     * Gets the formula of a cell (or null if the cell has no formular)
     * 
     * @return String
     */
    public String getFormula();
    
    
    /**
     * If possible to convert (with the format 'dd-MM-yyyy'), this method returns the Date-Representation of this value (null if the value cannot be converted)
     * 
     * @return Date
     */
    public Date asDate();
    
    /**
     * If possible to convert (with the given format), this method returns the Date-Representation of this value (null if the value cannot be converted)
     * 
     * @param format 
     * @return Date
     */
    public Date asDate(String format);
    
    /**
     * If possible to convert, this method returns the Time-Representation of this value (null if the value cannot be converted)
     * 
     * @return Time
     */
    public Time asTime();
    
    /**
     * If possible to convert, this method returns the Double-Representation of this value (null if the value cannot be converted)
     * 
     * @return Double
     */
    public Double asDouble();
    
    /**
     * If possible to convert, this method returns the Boolean-Representation of
     * this value (FALSE if the value cannot be converted)
      * 
     * @return Boolean
     */
    public Boolean asBoolean();
    
    /**
     * If possible to convert, this method returns the String-Representation of this value (null if the value cannot be converted)
     * 
     * @return Boolean
     */
    public String asString();
}
