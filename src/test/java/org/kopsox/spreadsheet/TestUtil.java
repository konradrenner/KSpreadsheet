package org.kopsox.spreadsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class TestUtil {


    public static InputStream getSpreadSheetStrean(String filename) throws Exception {
        return new FileInputStream(new File(System.getProperty("path_to_spreadsheets") + filename));
    }
}
