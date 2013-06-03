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
package org.kopsox.spreadsheet.style;

/**
 * This class descripes a line of a border
 * 
 * @author Konrad Renner
 *
 */
public final class CellStyleBorderDescriptor {

	private final CellStyleBorderLook borderLook;
	private final CellStyleColor borderColor;
	
	public CellStyleBorderDescriptor(CellStyleBorderLook look, CellStyleColor color) {
		this.borderColor = color;
		this.borderLook = look;
	}

	public CellStyleBorderLook getBorderLook() {
		return borderLook;
	}

	public CellStyleColor getBorderColor() {
		return borderColor;
	}
	
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CellStyleBorderDescriptor [borderLook=");
		sb.append(borderLook);
		sb.append(", borderColor=");
		sb.append(borderColor);
		sb.append(']');
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((borderColor == null) ? 0 : borderColor.hashCode());
		result = prime * result
				+ ((borderLook == null) ? 0 : borderLook.hashCode());
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
		CellStyleBorderDescriptor other = (CellStyleBorderDescriptor) obj;
		if (borderColor != other.borderColor)
			return false;
		if (borderLook != other.borderLook)
			return false;
		return true;
	}
}
