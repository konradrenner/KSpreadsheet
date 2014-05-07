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
import java.util.Objects;
import org.kopsox.spreadsheet.data.Sheet;
import org.kopsox.spreadsheet.data.Workbook;

/**
 * 
 * @author Konrad Renner
 */
public abstract class AbstractSheet implements Sheet {

    protected final Workbook workbook;
    private String name;
    private int index;
   
  //For merged regions
	protected final Map<CellRegion, CellRegion> regions;
	
    public AbstractSheet(final Workbook workbook, final String nam, final int idx) {
        Objects.requireNonNull(workbook, "Sheet must belong to a workbook");

        this.name = nam;
        this.index = idx;
        this.workbook = workbook;
        this.regions = new HashMap<AbstractSheet.CellRegion, AbstractSheet.CellRegion>();
	}
	
	

	@Override
	public Workbook getWorkbook() {
		return this.workbook;
	}



	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#getSheetIndex()
	 */
	@Override
	public int getSheetIndex() {
		return index;
	}

	/* (non-Javadoc)
	 * @see org.kopsox.spreadsheet.data.Sheet#getSheetName()
	 */
	@Override
	public String getSheetName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AbstractSheet other = (AbstractSheet) obj;
		if (index != other.index)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
   
   protected class CellRegion{
		protected final int startRow;
		protected final int endRow;
		protected final int startColumn;
		protected final int endColumn;
		
		//this index is needed for POI-Sheets
		public final int index;
		
		public CellRegion(int sRow,int eRow,int sColumn,int eColumn,int idx){
			this.startColumn = sColumn;
			this.startRow = sRow;
			this.endRow = eRow;
			this.endColumn = eColumn;
			this.index = idx;
		}
		
		public CellRegion(int sRow,int eRow,int sColumn,int eColumn){
			this(sRow,eRow,sColumn,eColumn,-1);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + endColumn;
			result = prime * result + endRow;
			result = prime * result + startColumn;
			result = prime * result + startRow;
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
			CellRegion other = (CellRegion) obj;
			if (endColumn != other.endColumn)
				return false;
			if (endRow != other.endRow)
				return false;
			if (startColumn != other.startColumn)
				return false;
			if (startRow != other.startRow)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "CellRegion [startRow=" + startRow + ", endRow=" + endRow
					+ ", startColumn=" + startColumn + ", endColumn="
					+ endColumn + "]";
		}
	}
	
}
