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
 * This builder creates an DefaultCellStyle
 * 
 * @author Konrad Renner
 *
 */
public final class CellStyleBuilder {
	
	private CellStyleBorder border;
	private CellStyleColor background;
	private CellStyleColor foreground;
	private CellStyleFont font;
	private CellStyleHorizontalAlignment halgin;
	private CellStyleVerticalAlignment valign ;
	
	public CellStyleBuilder() {
		this(null);
	}
	
	public CellStyleBuilder(CellStyle styleFrom) {
		if(styleFrom == null) {
			this.border = null;
			this.background = null;
			this.font = null;
			this.foreground = null;
			this.halgin = null;
			this.valign = null;
		}else {
			this.border = styleFrom.getBorder();
			this.background = styleFrom.getBackgroundColor();
			this.font = styleFrom.getFont();
			this.foreground = styleFrom.getForegroundColor();
			this.halgin = styleFrom.getHorizontalAlignment();
			this.valign = styleFrom.getVerticalAlignment();
		}
	}
	
	public CellStyleBuilder border(CellStyleBorder value) {
		this.border = value;
		
		return this;
	}
	
	public CellStyleBuilder background(CellStyleColor value) {
		this.background = value;
		
		return this;
	}
	
	public CellStyleBuilder foreground(CellStyleColor value) {
		this.foreground = value;
		
		return this;
	}
	
	public CellStyleBuilder font(CellStyleFont value) {
		this.font = value;
		
		return this;
	}
	
	public CellStyleBuilder horizontalAlignment(CellStyleHorizontalAlignment value) {
		this.halgin = value;
		
		return this;
	}
	
	public CellStyleBuilder verticalAlignment(CellStyleVerticalAlignment value) {
		this.valign = value;
		
		return this;
	}
	
	public CellStyle build() {
		return new DefaultCellStyle(this.border,this.background,this.foreground,this.font,this.halgin,this.valign);
	}

	final class DefaultCellStyle implements CellStyle{
		
		private final CellStyleBorder dborder;
		private final CellStyleColor dbackground;
		private final CellStyleColor dforeground;
		private final CellStyleFont dfont;
		private final CellStyleHorizontalAlignment dhalgin;
		private final CellStyleVerticalAlignment dvalign ;
		
		DefaultCellStyle(CellStyleBorder border,CellStyleColor background,CellStyleColor foreground,
				CellStyleFont font,CellStyleHorizontalAlignment halgin,CellStyleVerticalAlignment valign ){
			
			this.dborder = border;
			this.dbackground = background;
			this.dforeground = foreground;
			this.dfont = font;
			this.dhalgin = halgin;
			this.dvalign = valign;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("DefaultCellStyle[border=");
			sb.append(dborder);
			sb.append(", background=");
			sb.append(dbackground);
			sb.append(", foreground=");
			sb.append(dforeground);
			sb.append(", font=");
			sb.append(dfont);
			sb.append(", horziontalalgin=");
			sb.append(dhalgin);
			sb.append(", verticalalgin=");
			sb.append(dvalign);
			sb.append(']');
			
			return sb.toString();
		}

		@Override
		public CellStyleBorder getBorder() {
			return this.dborder;
		}

		@Override
		public CellStyleColor getBackgroundColor() {
			return this.dbackground;
		}

		@Override
		public CellStyleColor getForegroundColor() {
			return this.dforeground;
		}

		@Override
		public CellStyleFont getFont() {
			return this.dfont;
		}

		@Override
		public CellStyleHorizontalAlignment getHorizontalAlignment() {
			return this.dhalgin;
		}

		@Override
		public CellStyleVerticalAlignment getVerticalAlignment() {
			return this.dvalign;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((dbackground == null) ? 0 : dbackground.hashCode());
			result = prime * result
					+ ((dborder == null) ? 0 : dborder.hashCode());
			result = prime * result + ((dfont == null) ? 0 : dfont.hashCode());
			result = prime * result
					+ ((dforeground == null) ? 0 : dforeground.hashCode());
			result = prime * result
					+ ((dhalgin == null) ? 0 : dhalgin.hashCode());
			result = prime * result
					+ ((dvalign == null) ? 0 : dvalign.hashCode());
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
			DefaultCellStyle other = (DefaultCellStyle) obj;
			if (dbackground != other.dbackground)
				return false;
			if (dborder != other.dborder)
				return false;
			if (dfont != other.dfont)
				return false;
			if (dforeground != other.dforeground)
				return false;
			if (dhalgin != other.dhalgin)
				return false;
			if (dvalign != other.dvalign)
				return false;
			return true;
		}
	}
}
