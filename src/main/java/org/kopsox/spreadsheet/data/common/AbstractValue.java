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

import org.kopsox.spreadsheet.data.Value;

/**
 * @author Konrad Renner
 *
 */
public abstract class AbstractValue implements Value {

	private static final long serialVersionUID = -4979191262151075814L;
	private String formula;
	
	public AbstractValue(String f) {
		this.formula = f;
	}

	@Override
	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("AbstractValue[");
		sb.append("formula=");
		sb.append(formula);
		sb.append(']');
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formula == null) ? 0 : formula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractValue other = (AbstractValue) obj;
		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;
		return true;
    }

    @Override
    public int compareTo(Value o) {
        if (this.equals(o)) {
            return 0;
        }
        return asString().compareTo(o.asString());
    }
}
