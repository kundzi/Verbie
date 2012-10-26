package by.kunin.android.langrescue.util;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
    private IOUtils() {};
    
    public static void closeIfNotNull(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                //do nothig, should be ok
                e.printStackTrace();
            }
        }
    }
}
