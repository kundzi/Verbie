package by.kunin.android.langrescue.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import by.kunin.android.langrescue.R;

public class V2IrregularVerbListItem extends LinearLayout {
	
	private TextView textViewInfinitive;
	private TextView textViewSimple;
	private TextView textViewParticiple;
	
	private long id;
	

	public V2IrregularVerbListItem(Context context) {
		super(context);
	}
	
	public V2IrregularVerbListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setVerb(String infinitive, String simple, String participle) {
		textViewInfinitive.setText(infinitive);
		textViewSimple.setText(simple);
		textViewParticiple.setText(participle);
	}
	
	public void setVerbId(long id) {
		this.id = id;
	}
	
	public long getVerbId() {
		return id;
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		textViewSimple = (TextView) findViewById(R.id.v2_ivli_simple);
		textViewInfinitive = (TextView) findViewById(R.id.v2_ivli_infinitive);
		textViewParticiple = (TextView) findViewById(R.id.v2_ivli_participle);
	}
	
}
