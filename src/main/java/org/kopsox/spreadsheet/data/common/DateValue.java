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
import java.util.Objects;
import org.kopsox.spreadsheet.data.Value;

/**
 * @author Konrad Renner
 *
 */
public final class DateValue extends AbstractValue {
	
    private static final long serialVersionUID = 3101530032915344295L;

    private final Date value;

    public DateValue(final Date v) {
        this(v, null);
    }

    public DateValue(final Date v, final String f) {
        super(f);
        Objects.requireNonNull(v, "value must not be null, or use BlankValue class instead");
        this.value = v;
    }


    /* (non-Javadoc)
     * @see org.kopsox.spreadsheet.data.Value#getType()
     */
    @Override
    public Type getType() {
        return Value.Type.DATE;
    }

    /* (non-Javadoc)
     * @see org.kopsox.spreadsheet.data.Value#toBoolean()
     */
    @Override
    public Boolean asBoolean() {
        return Boolean.FALSE;
    }

    /* (non-Javadoc)
     * @see org.kopsox.spreadsheet.data.Value#toDate()
     */
    @Override
    public Date asDate() {
        return this.value;
    }

    /* (non-Javadoc)
     * @see org.kopsox.spreadsheet.data.Value#toDate(java.lang.String)
     */
    @Override
    public Date asDate(String format) {
        return this.value;
    }

    /* (non-Javadoc)
     * @see org.kopsox.spreadsheet.data.Value#toDouble()
     */
    @Override
    public Double asDouble() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.kopsox.spreadsheet.data.Value#toTime()
     */
    @Override
    public Time asTime() {
        return new Time(this.value.getTime());
    }


    @Override
    public String asString() {
        return new java.sql.Date(this.value.getTime()).toString();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("DateValue[");
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
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DateValue other = (DateValue) obj;
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
}
