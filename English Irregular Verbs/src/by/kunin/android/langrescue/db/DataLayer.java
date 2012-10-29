package by.kunin.android.langrescue.db;

import java.io.Closeable;
import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataLayer implements Closeable, DataBaseConstants {
	
	private Context mContext;
	private VerbsDbOpenHelper mOpenHelper;
	private SQLiteDatabase mDatabase;
	
	public DataLayer(Context context) {
		mContext = context;
		mOpenHelper = new VerbsDbOpenHelper(context);
	}
	
	public void open() {
		mDatabase = mOpenHelper.getWritableDatabase();
	}

	@Override
	public void close() throws IOException {
		mOpenHelper.close();
		mDatabase = null;
	}
	
	public Cursor getAllVerbs() {
		if (mDatabase != null) {
			return mDatabase.query(TABLE_ALL, null, null, null, null, null, COLUMN__ID + " ASC");
		} else {
			return null;
		}
	}

}
