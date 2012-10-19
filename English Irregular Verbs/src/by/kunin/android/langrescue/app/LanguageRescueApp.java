package by.kunin.android.langrescue.app;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import by.kunin.android.langrescue.db.LangRescueDbAdapter;
import by.kunin.android.langrescue.enums.LanguageLevel;
import by.kunin.android.langrescue.models.IrregularVerb;

import java.util.ArrayList;

public class LanguageRescueApp extends Application {
	SQLiteDatabase database;
	LangRescueDbAdapter dbAdapter;
	ArrayList<IrregularVerb> irregularVerbs;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		//TO-DO make dbOpened verbs
		dbAdapter = new LangRescueDbAdapter(this);
		irregularVerbs = dbAdapter.getVerbs(LangRescueDbAdapter.TABLE_0_NAME);
	}
	
	public ArrayList<IrregularVerb> getVerds() {
		return this.irregularVerbs;
	}
	
	public void setVerbs(LanguageLevel level) {
		String tableName = "";
		switch (level) {
		case BASE_LEVEL:
			tableName = LangRescueDbAdapter.TABLE_0_NAME;
			break;
		case ADVANCED_LEVEL:
			tableName = LangRescueDbAdapter.TABLE_1_NAME;
			break;
		}
		irregularVerbs = dbAdapter.getVerbs(tableName);
	}

	public void goToHomePage() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=by.kunin.android.langrescue"));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
