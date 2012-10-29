// Developed for NVIDIA by Softeq Development Corporation
// http://www.softeq.com
package by.kunin.android.langrescue.util.desktop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import by.kunin.android.langrescue.models.IrregularVerb;
import by.kunin.android.langrescue.util.VerbsUtils;

public class VerbsListProcessor {
    
    static void print(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        createResources();
    }
    
    public interface VerbsFileProcessor {
        
        public File process(InputStream source);
        
    }
    
    public static class Verbs211Processor implements VerbsFileProcessor {

        @Override
        public File process(InputStream source) {
            File output = new File("res/raw/verbs_211.txt");
            try {
                FileWriter fileWriter = new FileWriter(output);
                
                Scanner verbsScanner = new Scanner(source);
                StringBuilder builder;
                verbsScanner.useDelimiter("\\n");
                String s;
                String[] verbs;
                
                while (verbsScanner.hasNext()) {
                    builder = new StringBuilder();
                    
                    s = verbsScanner.next().toLowerCase();
                    s = s.replaceAll("/[\\w-]+", "");
                    verbs = s.split("\\s");
                    
                    for (int i = 0; i < 3; i++)
                    builder.append(verbs[i]).append(i == 2 ? "\n": " ");
                    
                    fileWriter.write(builder.toString());
                    
                    print(builder.toString());
                }
                fileWriter.flush();
                 
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            
            
            return output;
        }
        
    }
    
    public static void createResources() {
    	
    	List<IrregularVerb> advanced = VerbsUtils.getVerbsFromFile("res/raw/verbs_211.txt");
    	List<IrregularVerb> interm = VerbsUtils.getVerbsFromFile("res/raw/verbs_135.txt");
    	List<IrregularVerb> bacis = VerbsUtils.getVerbsFromFile("res/raw/verbs_50.txt");
    	
    	Collections.sort(advanced);
    	
    	findAndWriteRelation(bacis, advanced, "res/raw/basic.txt");
    	findAndWriteRelation(interm, advanced, "res/raw/interm.txt");
    	
    }
    
    private static void findAndWriteRelation(List<IrregularVerb> to, List<IrregularVerb> from, String writeTo) {
    	List<Integer> ids = new ArrayList<Integer>();
    	
    	for (IrregularVerb iv : to) {
    		int position = from.indexOf(iv);
    		if (position != -1) ids.add(position);
    		else throw new RuntimeException("Cant find position of " + iv);
    	}
    	
    	VerbsUtils.writeIdsToFile(ids, writeTo);
    }

}
