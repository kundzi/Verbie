package by.kunin.android.langrescue.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import by.kunin.android.langrescue.R;
import by.kunin.android.langrescue.db.DataBaseConstants;
import by.kunin.android.langrescue.db.DataLayer;
import by.kunin.android.langrescue.util.IOUtils;

public class VerbsListFragment extends ListFragment
								implements DataBaseConstants {
	
	private DataLayer mDataLayer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_verbs_list, null);
		
		return view;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		mDataLayer = new DataLayer(getActivity());
		mDataLayer.open();
		Cursor verbs = mDataLayer.getAllVerbs();
		
		String[] from =  {TABLE_ALL_INF, TABLE_ALL_SIMPLE, TABLE_ALL_PARTICIPLE};
		int[] to = {R.id.v2_ivli_infinitive, R.id.v2_ivli_simple, R.id.v2_ivli_participle};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.v2_irregular_verb, verbs, from, to);
		setListAdapter(adapter);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Cursor cursor = ((SimpleCursorAdapter)getListAdapter()).getCursor();
		((SimpleCursorAdapter)getListAdapter()).swapCursor(null);
		
		IOUtils.closeCursorIfNotNull(cursor);
		IOUtils.closeIfNotNull(mDataLayer);
		mDataLayer = null;
	}
}
