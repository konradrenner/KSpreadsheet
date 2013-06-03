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
 * This class provides a border-style for a cell, which can be used for the built in spreadsheet-types.
 * A Border contains 4 BorderDescriptors, one for each side
 * 
 * @author Konrad Renner
 *
 */
public final class CellStyleBorder {

	private final CellStyleBorderDescriptor topBorder;
	private final CellStyleBorderDescriptor bottomBorder;
	private final CellStyleBorderDescriptor leftBorder;
	private final CellStyleBorderDescriptor rightBorder;
	
	public CellStyleBorder(CellStyleBorderDescriptor top, CellStyleBorderDescriptor bottom, CellStyleBorderDescriptor left, CellStyleBorderDescriptor right) {
		this.topBorder = top;
		this.bottomBorder = bottom;
		this.leftBorder = left;
		this.rightBorder = right;
	}

	public CellStyleBorderDescriptor getTopBorder() {
		return topBorder;
	}

	public CellStyleBorderDescriptor getBottomBorder() {
		return bottomBorder;
	}

	public CellStyleBorderDescriptor getLeftBorder() {
		return leftBorder;
	}

	public CellStyleBorderDescriptor getRightBorder() {
		return rightBorder;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CellStyleBorder [topBorder=");
		sb.append(topBorder);
		sb.append(", bottomBorder=");
		sb.append(bottomBorder);
		sb.append(", leftBorder=");
		sb.append(leftBorder);
		sb.append(", rightBorder=");
		sb.append(rightBorder);
		sb.append(']');
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bottomBorder == null) ? 0 : bottomBorder.hashCode());
		result = prime * result
				+ ((leftBorder == null) ? 0 : leftBorder.hashCode());
		result = prime * result
				+ ((rightBorder == null) ? 0 : rightBorder.hashCode());
		result = prime * result
				+ ((topBorder == null) ? 0 : topBorder.hashCode());
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
		CellStyleBorder other = (CellStyleBorder) obj;
		if (bottomBorder == null) {
			if (other.bottomBorder != null)
				return false;
		} else if (!bottomBorder.equals(other.bottomBorder))
			return false;
		if (leftBorder == null) {
			if (other.leftBorder != null)
				return false;
		} else if (!leftBorder.equals(other.leftBorder))
			return false;
		if (rightBorder == null) {
			if (other.rightBorder != null)
				return false;
		} else if (!rightBorder.equals(other.rightBorder))
			return false;
		if (topBorder == null) {
			if (other.topBorder != null)
				return false;
		} else if (!topBorder.equals(other.topBorder))
			return false;
		return true;
	}
}
