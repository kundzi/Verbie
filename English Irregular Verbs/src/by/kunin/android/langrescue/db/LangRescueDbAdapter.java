package by.kunin.android.langrescue.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import by.kunin.android.langrescue.models.IrregularVerb;

import java.util.ArrayList;

public class LangRescueDbAdapter {
	
	public final static String DB_NAME = "lang_rescue.sqlite3";
	public final static String TABLE_0_NAME = "irregular_verbs_0";
	public final static String TABLE_1_NAME = "irregular_verbs_1";
	public final static int DB_VERSION = 1;
	
	public static final String KEY_ID = "_id";
	
	public static final String KEY_INFINITIVE = "infinitive";
	public static final int INFINITIVE_COLUMN = 1;
	
	public static final String KEY_SIMPLE = "past_simple";
	public static final int SIMPLE_COLUMN = 2;
	
	public static final String KEY_PARTICIPLE= "past_participle";
	public static final int PARTICIPLE_COLUMN = 3;
	
	private ExternalDbOpenHelper dbOpenHelper;
	private SQLiteDatabase database;
	private final Context context;
	
	public LangRescueDbAdapter(Context context) {
		this.context = context;
		dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
		database = dbOpenHelper.openDataBase();
	}
	
	public void close() {
		database.close();
	}
	
	public Cursor getAllEntries(String tableName) {
		Cursor result;
		String[] columns = {KEY_ID, KEY_INFINITIVE, KEY_SIMPLE, KEY_PARTICIPLE};
		result = database.query(tableName,
						    columns, null, null, null, null, KEY_ID);
		return result;
	}
	
	public ArrayList<IrregularVerb> getVerbs(String tableName) {
		ArrayList<IrregularVerb> result = new ArrayList<IrregularVerb>();
		Cursor cursor = getAllEntries(tableName);
		
		IrregularVerb verb;
		int id;
		String inf, past, part;
		cursor.moveToFirst();
		do {
//			id = cursor.getInt(0);
			inf = cursor.getString(INFINITIVE_COLUMN);
			past = cursor.getString(SIMPLE_COLUMN);
			part = cursor.getString(PARTICIPLE_COLUMN);
//			verb = new IrregularVerb(id, inf, past, part);
			verb = new IrregularVerb(inf, past, part);
			result.add(verb);
		} while (cursor.moveToNext());
		cursor.close();
		
		return result;
	}
	
	
}
