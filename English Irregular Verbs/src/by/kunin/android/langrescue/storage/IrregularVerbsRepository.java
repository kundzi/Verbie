package by.kunin.android.langrescue.storage;

import java.util.ArrayList;

import android.content.Context;
import by.kunin.android.langrescue.models.IrregularVerb;

public class IrregularVerbsRepository {
	
	
	public final static int VERBS_FROM_RESOURSES = 0;

	public static ArrayList<IrregularVerb> getVerbs(int sourceCode, Context context) {
		ArrayList<IrregularVerb> result = new ArrayList<IrregularVerb>();
//		switch (sourceCode) {
//			case VERBS_FROM_RESOURSES :
//			{
//				String[] resStrings = ((Application)context).getResources()
//														 .getStringArray(R.array.verbs);
//				int i = 1;
//				for (String string : resStrings) {
//					IrregularVerb verb = new IrregularVerb(string);
//					verb.setId(i++);
//					result.add(verb);
//				}
//				break;
//			}
//			default :
//			{
//				throw new UnsupportedOperationException();
//			}
//		}
		return result;
	}
}
