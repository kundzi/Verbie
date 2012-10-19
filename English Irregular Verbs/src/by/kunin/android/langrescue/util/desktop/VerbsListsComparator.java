// Developed for NVIDIA by Softeq Development Corporation
// http://www.softeq.com
package by.kunin.android.langrescue.util.desktop;

import by.kunin.android.langrescue.models.IrregularVerb;
import by.kunin.android.langrescue.models.IrregularVerbsFactory;
import by.kunin.android.langrescue.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class VerbsListsComparator {
    
    
    private static final String EOL = "\n";

    public static void main(String[] args) {
         VerbsListsComparator comparator = new VerbsListsComparator();
         comparator.setUp("res/raw/verbs_211.txt");
         
         List<IrregularVerb> list50 = getVerbsFromFile("res/raw/verbs_50.txt");
         List<IrregularVerb> list135 = getVerbsFromFile("res/raw/verbs_135.txt");
         
         comparator.compare(list50, "docs/diff_report_50.txt");
         comparator.compare(list135, "docs/diff_report_135.txt");
    }
    
    private List<IrregularVerb> origin;
    
    public void setUp(String path) {
        origin = getVerbsFromFile(path);
    }
    
    public void compare(List<IrregularVerb> listToCompare, String reportPath) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(reportPath);
            //report header 
            writer.append("Generation date: " + (new Date())).append(EOL);
            int total = listToCompare.size();
            int diffs = 0;
            
            //calculate difference
            for (IrregularVerb alt : listToCompare) {
                
                for (IrregularVerb o : origin) {
                    if (alt.same(o) && !alt.equals(o)) {
                        diffs++;
                        //write differ
                        String diffString = String.format("inf:%S || [o:%S != alt:%S] || [o:%S != alt:%S]\n", 
                                o.getInfinitive(), o.getPastSimple(), alt.getPastSimple(), 
                                o.getPastParticiple(), alt.getPastParticiple());
                        
                        writer.append(diffString);
                    }
                }
            }
            
            //report footer
            writer.append(String.format("Total size: %d, diffs: %d", total, diffs)).append(EOL);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeIfNotNull(writer);
        }
    }
    
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
