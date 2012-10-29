package by.kunin.android.langrescue.db;

import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import by.kunin.android.langrescue.R;
import by.kunin.android.langrescue.models.IrregularVerb;
import by.kunin.android.langrescue.models.IrregularVerbsFactory;

import com.google.common.base.Joiner;

public class VerbsDbOpenHelper extends SQLiteOpenHelper
								implements DataBaseConstants {
	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "verbs.sqlite";
	private Context mContext;
	
	private String CREATE_ALL = Joiner.on(" ").join("CREATE TABLE", TABLE_ALL, "(", COLUMN__ID, "integer primary key autoincrement,",
								 TABLE_ALL_INF, "text not null,", TABLE_ALL_SIMPLE, "text not null,", TABLE_ALL_PARTICIPLE, "text not null);");
	
	private String CREATE_BASIC = Joiner.on(" ").join("CREATE TABLE", TABLE_BASIC, "(", COLUMN__ID, "integer primary key autoincrement,",
								   COLUMN_ID_REF, "integer references", TABLE_ALL, "(", COLUMN__ID, "));");
	
	private String CREATE_INTERM = Joiner.on(" ").join("CREATE TABLE", TABLE_INTERM, "(", COLUMN__ID, "integer primary key autoincrement,",
			   COLUMN_ID_REF, "integer references", TABLE_ALL, "(", COLUMN__ID, "));");
	
	public VerbsDbOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ALL);
		db.execSQL(CREATE_BASIC);
		db.execSQL(CREATE_INTERM);
		
		populataTables(db);
	}

	private void populataTables(SQLiteDatabase db) {
		List<IrregularVerb> allVerbs = IrregularVerbsFactory.createManyFromStream(mContext.getResources().openRawResource(R.raw.advanced_full));
		Collections.sort(allVerbs);
		
		for (IrregularVerb iv : allVerbs) {
			ContentValues cv = new ContentValues();
			cv.put(TABLE_ALL_INF, iv.getInfinitive());
			cv.put(TABLE_ALL_SIMPLE, iv.getPastSimple());
			cv.put(TABLE_ALL_PARTICIPLE, iv.getPastParticiple());
			db.insert(TABLE_ALL, null, cv);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALL);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BASIC);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERM);
		onCreate(db);
	}

}
