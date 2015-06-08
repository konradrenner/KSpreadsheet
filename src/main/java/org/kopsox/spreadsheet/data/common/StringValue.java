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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kopsox.spreadsheet.data.Value;

/**
 * @author Konrad Renner
 *
 */
public final class StringValue extends AbstractValue {

	private static final long serialVersionUID = -6753441907581378287L;

	private final String value;
	private final String dateFormat;

	public StringValue(final String value) {
		this(value,null);
	}

	public StringValue(final String value,final String formula) {
            super(formula);
            if (value == null) {
                throw new NullPointerException("value must not be null, or use BlankValue class instead");
            }
            this.value = value;
            this.dateFormat = "dd-MM-yyyy";
	}


	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("StringValue[");
		sb.append(super.toString());
		sb.append("value=");
		sb.append(value);
		sb.append(",dateFormat=");
		sb.append(dateFormat);
		sb.append(']');

		return sb.toString();
	}


	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#getType()
	 */
	@Override
	public Type getType() {
		return Value.Type.STRING;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#toBoolean()
	 */
	@Override
	public Boolean asBoolean() {
		if(this.value == null) {
                    return Boolean.FALSE;
		}

		return Boolean.valueOf(this.value);
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#toDate()
	 */
	@Override
	public Date asDate() {
            if (this.value == null) {
                return null;
            }

            try {
                return DateFormat.getInstance().parse(this.value);
            } catch (ParseException e) {
                try {
                    DateFormat format = new SimpleDateFormat(dateFormat);
                    return format.parse(this.value);
                } catch (ParseException ex) {
                    //nothing
                }
            }

            return null;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#toDouble()
	 */
	@Override
	public Double asDouble() {
            if (this.value == null) {
                return null;
            }

            try {
                return Double.valueOf(this.value);
            } catch (NumberFormatException e) {
                try {
                    return NumberFormat.getInstance().parse(this.value).doubleValue();
                } catch (ParseException ex) {
                    //nothing
                }
            }
            return null;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Value#toTime()
	 */
	@Override
	public Time asTime() {
		if(this.value == null) {
			return null;
		}

		try {
			return Time.valueOf(this.value);
		}catch(IllegalArgumentException e) {
			//return null
		}

		return null;
	}

	@Override
	public Date asDate(String format) {
		if(this.value == null) {
			return null;
		}

		DateFormat formatter = new SimpleDateFormat(format);

		try {
			return formatter.parse(this.value);
		} catch (ParseException e) {
			//return null
		}

		return null;
	}

	@Override
	public String asString() {
		return this.value;
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
		StringValue other = (StringValue) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
