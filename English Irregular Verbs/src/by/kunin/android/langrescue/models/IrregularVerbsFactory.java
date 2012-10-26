package by.kunin.android.langrescue.models;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IrregularVerbsFactory { 
   private IrregularVerbsFactory() {};
   
   public static IrregularVerb createFromString(String threeForms) {
       Scanner scanner = new Scanner(threeForms);
       scanner.useDelimiter("\\s+");
       
       IrregularVerb verb = new IrregularVerb(scanner.next(),
                                              scanner.next(), scanner.next());
       
       
       return verb;
   }
   
   public static List<IrregularVerb> createManyFromStream(InputStream inputStream) {
       ArrayList<IrregularVerb> verbs = new ArrayList<IrregularVerb>();
       Scanner scanner = new Scanner(inputStream);
       scanner.useDelimiter("\\n");
       
       IrregularVerb verb;
       while (scanner.hasNext()) {
           verb = createFromString(scanner.next());
           verbs.add(verb);
       }
       
       return verbs;
   }
    
}
