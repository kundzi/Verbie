package by.kunin.android.langrescue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import by.kunin.android.langrescue.R;

public class IrregularVerbsModeActivity extends Activity {
	private Button referenceButton;
	private Button flashcardsButton;
	private Button guessmodeButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.irregular_verbs_mode);
		setUpViews();
	}

	private void setUpViews() {
		referenceButton = (Button)findViewById(R.id.irverbs_button_reference);
	}
	
	public void startReference(View view) {
		Intent intent = new Intent(this, IrregularVerbsReferenceActivity.class);
		startActivity(intent);
	}
}
