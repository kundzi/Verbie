package by.kunin.android.langrescue.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import by.kunin.android.langrescue.models.IrregularVerb;
import by.kunin.android.langrescue.models.IrregularVerbsFactory;

import com.google.common.base.Joiner;

public class VerbsUtils {
	private VerbsUtils() {};
	
	public static List<IrregularVerb> getVerbsFromFile(String path) {
        List<IrregularVerb> verbs = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            verbs = IrregularVerbsFactory.createManyFromStream(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeIfNotNull(fileInputStream);
        }
        
        return verbs;
    }
	
	public static boolean writeVerbsToFile(Collection<IrregularVerb> verbs, String path) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path);
			for (IrregularVerb iv : verbs) {
				String writeIt = Joiner.on(" ").join(iv.getInfinitive(), iv.getPastSimple(), iv.getPastParticiple());
				fileWriter.append(writeIt).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			IOUtils.closeIfNotNull(fileWriter);
		}
		
		
		return true;
	}
	
	public static boolean writeIdsToFile(Collection<Integer> ids, String path) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path);
			for (Integer i : ids) {
				String writeIt = String.valueOf(i);
				fileWriter.append(writeIt).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			IOUtils.closeIfNotNull(fileWriter);
		}
		
		
		return true;
	}
	
	
}
