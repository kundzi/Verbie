package by.kunin.android.langrescue.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import by.kunin.android.langrescue.models.IrregularVerb;
import by.kunin.android.langrescue.view.IrregularVerbView;

import java.util.List;

public class IrregularVerbsListAdapter extends ArrayAdapter<IrregularVerb> {
	int resourceId;
	Context context;
	private int color;
	
	public IrregularVerbsListAdapter(Context context, int textViewResourceId,
			List<IrregularVerb> objects, int color) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
		this.color = color;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		IrregularVerbView irv;
		IrregularVerb verb = getItem(position);

		if (convertView == null) {
			irv = (IrregularVerbView)View.inflate(context, resourceId, null);
		} else {
			irv = (IrregularVerbView) convertView;
		}
		irv.setVerb(verb);
		return irv;
	}

}
