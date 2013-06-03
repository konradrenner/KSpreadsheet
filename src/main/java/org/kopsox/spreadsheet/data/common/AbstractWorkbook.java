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

import java.util.HashMap;
import java.util.Map;

import org.kopsox.spreadsheet.data.Sheet;
import org.kopsox.spreadsheet.data.Workbook;

/**
 * @author Konrad Renner
 *
 */
public abstract class AbstractWorkbook implements Workbook {

	private final Map<String, Sheet> cachedSheets;
	private final String nameOfWorkbook;
	
	public AbstractWorkbook(String name) {
		this.cachedSheets = new HashMap<String, Sheet>();
		this.nameOfWorkbook = name;
	}

	protected Sheet getSheet(String name){
		return this.cachedSheets.get(name);
	}
	
	protected void registerSheet(String name,Sheet sheet) {
		this.cachedSheets.put(name, sheet);
	}
	
	protected boolean containsSheet(String name) {
		return this.cachedSheets.containsKey(name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nameOfWorkbook == null) ? 0 : nameOfWorkbook.hashCode());
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
		AbstractWorkbook other = (AbstractWorkbook) obj;
		if (nameOfWorkbook == null) {
			if (other.nameOfWorkbook != null)
				return false;
		} else if (!nameOfWorkbook.equals(other.nameOfWorkbook))
			return false;
		return true;
	}
}
