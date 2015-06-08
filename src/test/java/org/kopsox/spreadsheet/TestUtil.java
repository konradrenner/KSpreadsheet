package org.kopsox.spreadsheet;

import java.io.InputStream;
import org.junit.Assert;

public class TestUtil {


    public static InputStream getSpreadSheetStream(String filename) throws Exception {
        InputStream resourceAsStream = TestUtil.class.getResourceAsStream(filename);
        Assert.assertNotNull(resourceAsStream);
        return resourceAsStream;
    }
}
