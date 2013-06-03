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
 * This class provides properties for fonts, which can be used for the built in spreadsheet-types
 * 
 * @author Konrad Renner
 *
 */
public final class CellStyleFont {
	
	public static final byte NOT_UNDERLINED = 0;
	public static final byte SINGLE_UNDERLINED = 1;
	public static final byte DOUBLE_UNDERLINED = 2;
	

	private final String fontName;
	private final short fontHeightInPoints;
	private boolean italic;
	private byte underline;
	private boolean strikeout;
	private boolean bold;
	
	public CellStyleFont(String fontName, short height) {
		this.fontName = fontName;
		this.fontHeightInPoints = height;
		this.underline = NOT_UNDERLINED;
	}

	
	
	public boolean isBold() {
		return bold;
	}



	public void setBold(boolean bold) {
		this.bold = bold;
	}



	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public byte getUnderline() {
		return underline;
	}

	public void setUnderline(byte underline) {
		this.underline = underline;
	}

	public boolean isStrikeout() {
		return strikeout;
	}

	public void setStrikeout(boolean strikeout) {
		this.strikeout = strikeout;
	}

	public String getFontName() {
		return fontName;
	}

	public short getFontHeightInPoints() {
		return fontHeightInPoints;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CellStyleFont[fontName=");
		sb.append(fontName);
		sb.append(", fontHeightInPoints=");
		sb.append(fontHeightInPoints);
		sb.append(", italic=");
		sb.append(italic);
		sb.append(", bold=");
		sb.append(bold);
		sb.append(", underline=");
		sb.append(underline);
		sb.append(", strikeout=");
		sb.append(strikeout);
		sb.append(']');
		
		return sb.toString();
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bold ? 1231 : 1237);
		result = prime * result + fontHeightInPoints;
		result = prime * result
				+ ((fontName == null) ? 0 : fontName.hashCode());
		result = prime * result + (italic ? 1231 : 1237);
		result = prime * result + (strikeout ? 1231 : 1237);
		result = prime * result + underline;
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
		CellStyleFont other = (CellStyleFont) obj;
		if (bold != other.bold)
			return false;
		if (fontHeightInPoints != other.fontHeightInPoints)
			return false;
		if (fontName == null) {
			if (other.fontName != null)
				return false;
		} else if (!fontName.equals(other.fontName))
			return false;
		if (italic != other.italic)
			return false;
		if (strikeout != other.strikeout)
			return false;
		if (underline != other.underline)
			return false;
		return true;
	}
}
