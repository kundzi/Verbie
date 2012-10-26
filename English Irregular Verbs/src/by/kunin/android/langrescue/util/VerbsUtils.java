package by.kunin.android.langrescue.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import by.kunin.android.langrescue.models.IrregularVerb;
import by.kunin.android.langrescue.models.IrregularVerbsFactory;

public class VerbsUtils {
	private VerbsUtils() {};
	
	public static List<IrregularVerb> getVerbsFromFile(String path) {
        List<IrregularVerb> verbs = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            verbs = IrregularVerbsFactory.createManyFromStream(fileInputStream);
        } catch (FileNotFoundException e) {
            //hope it will be alright
            e.printStackTrace();
        } finally {
            IOUtils.closeIfNotNull(fileInputStream);
        }
        
        return verbs;
    }
}
