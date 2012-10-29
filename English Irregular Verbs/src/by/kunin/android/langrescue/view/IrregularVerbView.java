package by.kunin.android.langrescue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import by.kunin.android.langrescue.R;
import by.kunin.android.langrescue.activity.IrregularVerbsReferenceActivity;
import by.kunin.android.langrescue.models.IrregularVerb;

public class IrregularVerbView extends LinearLayout {

	private IrregularVerb verb;
	private TextView infinitive;
	private TextView simple;
	private TextView participle;
	private int advColor;

	public IrregularVerbView(Context context, AttributeSet set) {
		super(context, set);
	}

	public void setAdvColor(int color) {
		advColor = color;
	}

	public void setVerb(IrregularVerb verb) {
		this.verb = verb;
		initValues();
	}

	public IrregularVerb getVerb() {
		return verb;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		IrregularVerbsReferenceActivity context = (IrregularVerbsReferenceActivity) getContext();
		setAdvColor(context.getColor());
		setUpViews();
	}

	private void setUpViews() {
		infinitive = ((TextView) findViewById(R.id.item_infinitive));
		simple = ((TextView) findViewById(R.id.item_simple));
		participle = ((TextView) findViewById(R.id.item_participle));
	}

	private void initValues() {
		infinitive.setText(verb.getInfinitive());
		simple.setText(verb.getPastSimple());
		participle.setText(verb.getPastParticiple());
		setColor();
	}

	private void setColor() {
		int color;
		int position = verb.getId();
		if (position % 2 == 0) {
			color = advColor;
		} else {
			color = getResources().getColor(android.R.color.white);
		}
		infinitive.setBackgroundColor(color);
		simple.setBackgroundColor(color);
		participle.setBackgroundColor(color);
	}

}
