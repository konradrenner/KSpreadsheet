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
public final class BooleanValue extends AbstractValue {
	private static final long serialVersionUID = -7625283613999856807L;

	private final Boolean value;

	public BooleanValue(final Boolean v) {
		this(v,null);
	}

	public BooleanValue(final Boolean v,final String f) {
            super(f);
            if (v == null) {
                throw new NullPointerException("value must not be null, or use BlankValue class instead");
            }
            this.value = v;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#getType()
	 */
	@Override
	public Type getType() {
		return Value.Type.BOOLEAN;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#asBoolean()
	 */
	@Override
	public Boolean asBoolean() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#asDate()
	 */
	@Override
	public Date asDate() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#asDate(java.lang.String)
	 */
	@Override
	public Date asDate(String format) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#asDouble()
	 */
	@SuppressWarnings("boxing")
	@Override
	public Double asDouble() {
		return this.value != null && this.value.booleanValue() ? 1.0 : 0.0;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#asTime()
	 */
	@Override
	public Time asTime() {
		return null;
	}

	@Override
	public String asString() {
		return this.value.toString();
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("BooleanValue[");
		sb.append(super.toString());
		sb.append("value=");
		sb.append(value);
		sb.append(']');

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooleanValue other = (BooleanValue) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


}
