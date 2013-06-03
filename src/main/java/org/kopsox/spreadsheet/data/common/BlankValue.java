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
package org.kopsox.spreadsheet.data.common;

import java.sql.Time;
import java.util.Date;

import org.kopsox.spreadsheet.data.Value;

/**
 * @author Konrad Renner
 *
 */
public final class BlankValue extends AbstractValue {
	private static final long serialVersionUID = 2469599896601308211L;


	public BlankValue() {
		super(null);
	}
   
  public BlankValue(String formula) {
		super(formula);
	}


	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#getType()
	 */
	@Override
	public Type getType() {
		return Value.Type.BLANK;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#Boolean()
	 */
	@Override
	public Boolean asBoolean() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#Date()
	 */
	@Override
	public Date asDate() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#Date(java.lang.String)
	 */
	@Override
	public Date asDate(String format) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#Double()
	 */
	@Override
	public Double asDouble() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#Time()
	 */
	@Override
	public Time asTime() {
		return null;
	}

	@Override
	public String asString() {
		return null;
	}


	@Override
	public String toString() {
		
		
		return Value.Type.BLANK.toString();
	}

	
}
