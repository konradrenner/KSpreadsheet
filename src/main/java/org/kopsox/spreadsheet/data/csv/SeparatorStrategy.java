/*
 * Copyright (C) 2014 Konrad Renner.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.kopsox.spreadsheet.data.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.kopsox.spreadsheet.data.common.StringValue;

/**
 * Defines, how the values in an CSV file are separated
 *
 * @author Konrad Renner
 */
public enum SeparatorStrategy {

    COMMA(","),
    SEMICOLON(";");
    private final String symbol;

    private SeparatorStrategy(String s) {
        this.symbol = s;
    }

    public String getSymbol() {
        return symbol;
    }


    /**
     * Creates an Initializer for reading data from a stream
     *
     * @param stream
     * @return CSVInitializer
     */
    public CSVInitializer createInitializer(InputStream stream) {
        return new DefaultInitializer(stream, this);
    }

    /**
     * Creates an Initializer, if you don't want to fill a Workbook with data
     * (e.g. a new workbook)
     *
     * @param stream
     * @return CSVInitializer
     */
    public CSVInitializer createInitializer() {
        return new DefaultInitializer(null, this);
    }

    private class DefaultInitializer implements CSVInitializer {

        private final InputStream stream;
        private final SeparatorStrategy strategy;

        private DefaultInitializer(InputStream stream, SeparatorStrategy strat) {
            this.stream = stream;
            this.strategy = strat;
        }

        @Override
        public boolean init(CSVSheet sheet) {
            if (stream == null || sheet == null) {
                return false;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

                String line;
                int rowNumber = 0;
                while ((line = reader.readLine()) != null) {
                    String[] split = line.split(strategy.getSymbol());

                    for (int i = 0; i < split.length; i++) {
                        sheet.setValueAt(new CSVSheet.CellID(rowNumber, i), new StringValue(split[i]));
                    }

                    rowNumber++;
                }
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }


            return true;
        }

        @Override
        public SeparatorStrategy getStrategy() {
            return this.strategy;
        }
    }
}
