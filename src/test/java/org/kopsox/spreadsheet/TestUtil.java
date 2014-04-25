package org.kopsox.spreadsheet;

import java.io.InputStream;
import org.junit.Assert;

public class TestUtil {


    public static InputStream getSpreadSheetStrean(String filename) throws Exception {
        InputStream resourceAsStream = TestUtil.class.getResourceAsStream(filename);
        Assert.assertNotNull(resourceAsStream);
        return resourceAsStream;
    }
}
