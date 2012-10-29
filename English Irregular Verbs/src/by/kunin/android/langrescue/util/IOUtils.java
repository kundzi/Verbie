package by.kunin.android.langrescue.util;

import java.io.Closeable;
import java.io.IOException;

import android.database.Cursor;

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
    
    public static void closeCursorIfNotNull(Cursor closeable) {
        if (closeable != null) {
            closeable.close();
        }
    }
}
