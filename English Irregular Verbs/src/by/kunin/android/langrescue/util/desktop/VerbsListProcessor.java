// Developed for NVIDIA by Softeq Development Corporation
// http://www.softeq.com
package by.kunin.android.langrescue.util.desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

public class VerbsListProcessor {
    
    static void print(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        VerbsFileProcessor verbsFileProcessor = new Verbs211Processor();
        File processed = null;
        
        try {
            processed = verbsFileProcessor.process(new FileInputStream("docs/verbs_211_raw.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        System.out.println(processed.length() + " " + (new Date(processed.lastModified())));
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

}
