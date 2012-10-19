package by.kunin.android.langrescue.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import by.kunin.android.langrescue.R;
import by.kunin.android.langrescue.adapters.IrregularVerbsListAdapter;
import by.kunin.android.langrescue.app.LanguageRescueApp;
import by.kunin.android.langrescue.enums.LanguageLevel;
import by.kunin.android.langrescue.models.IrregularVerb;

import java.util.ArrayList;

public class IrregularVerbsReferenceActivity extends ListActivity {

	public LanguageRescueApp context;

	public ListView listView;
	private EditText filterEdit;
	private LinearLayout lookupBar;
	private ArrayList<IrregularVerb> verbs;
	private IrregularVerbsListAdapter adapter;
	private ReferenceTextWatcher textWatcher;

	private LanguageLevel level = LanguageLevel.BASE_LEVEL;
	private int itemColor;
	private int layoutId;
	private int listColor;
	private boolean isLookupVisible = true;
	private String changeLevelLabel;
	private String levelChangedMessage;
	private String lookupMenuTitle;

	private MenuItem changeLevelButton;
	private MenuItem lookupItem;

	public int getColor() {
		return this.itemColor;
	}

	private void setLevelConfig() {
		if (level == LanguageLevel.BASE_LEVEL) {
			layoutId = R.layout.irregular_verbs_reference_v1;
			listColor = getResources().getColor(R.color.dark_yellow);
			itemColor = getResources().getColor(R.color.light_yellow);
			changeLevelLabel = getResources()
					.getString(R.string.show_all_verbs);
			levelChangedMessage = getResources().getString(
					R.string.base_varbs_mess);
		} else {
			layoutId = R.layout.irregular_verbs_reference_v2;
			listColor = getResources().getColor(R.color.light_blue);
			itemColor = getResources().getColor(R.color.adv_blue);
			changeLevelLabel = getResources().getString(
					R.string.show_base_50_verbs);
			levelChangedMessage = getResources().getString(
					R.string.all_varbs_mess);
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		level = LanguageLevel.valueOf(state.getString("level"));
		isLookupVisible = state.getBoolean("lookupVisible");
		onCreate(null);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("level", level.toString());
		outState.putBoolean("lookupVisible", isLookupVisible);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setLevelConfig();
		setContentView(layoutId);
		setUpViews();
		configViews();
		verbs = ((LanguageRescueApp) getApplication()).getVerds();
		fillTable();
		registerForContextMenu(listView);
		context = (LanguageRescueApp) getApplication();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.options, menu);
		changeLevelButton = menu.findItem(R.id.change_level);
		lookupItem = menu.findItem(R.id.switch_lookupl);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		changeLevelButton.setTitle(changeLevelLabel);
		lookupItem.setTitle(lookupMenuTitle);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.change_level: {
			changeLevel();
			return true;
		}
		case R.id.exit: {
			finish();
			return true;
		}
		case R.id.switch_lookupl: {
			switchLookup(null);
			return true;
		}
		case R.id.option_rate: {
			context.goToHomePage();
			return true;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(Menu.NONE, R.id.change_level, Menu.NONE, "Change level");
		menu.add(Menu.NONE, R.id.switch_lookupl, Menu.NONE,
				"Turn look up panel on/off");
		menu.add(Menu.NONE, R.id.option_rate, Menu.NONE, "App homepage");
		menu.add(Menu.NONE, R.id.exit, Menu.NONE, "Close application");
	}

	public void switchLookup(View view) {
		isLookupVisible = !isLookupVisible;
		if (isLookupVisible) {
			lookupBar.setVisibility(View.VISIBLE);
			lookupMenuTitle = getResources().getString(
					R.string.menu_hide_lookup);
		} else {
			lookupBar.setVisibility(View.GONE);
			clearLookup(null);
			Toast.makeText(this, R.string.mess_lookup_hidden,
					Toast.LENGTH_SHORT).show();
			lookupMenuTitle = getResources().getString(
					R.string.menu_show_lookup);
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(filterEdit.getWindowToken(), 0);
		}
	}

	public void clearLookup(View view) {
		filterEdit.getText().clear();
	}

	private void changeLevel() {
		level = level == LanguageLevel.BASE_LEVEL ? LanguageLevel.ADVANCED_LEVEL
				: LanguageLevel.BASE_LEVEL;
		((LanguageRescueApp) getApplication()).setVerbs(level);
		onCreate(null);
		Toast.makeText(this, levelChangedMessage, Toast.LENGTH_SHORT).show();
	}

	private void setUpViews() {
		listView = getListView();
		filterEdit = (EditText) findViewById(R.reference.filter_text);
		lookupBar = (LinearLayout) findViewById(R.reference.lookup_bar);
	}

	private void configViews() {
		listView.setCacheColorHint(listColor);
		listView.setDividerHeight(0);
		listView.setSmoothScrollbarEnabled(true);
		listView.setTextFilterEnabled(true);
		textWatcher = new ReferenceTextWatcher();
		filterEdit.addTextChangedListener(textWatcher);

		if (isLookupVisible) {
			lookupBar.setVisibility(View.VISIBLE);
		} else {
			lookupBar.setVisibility(View.GONE);
			filterEdit.getText().clear();
		}
		if (isLookupVisible) {
			lookupMenuTitle = getResources().getString(
					R.string.menu_hide_lookup);
		} else {
			lookupMenuTitle = getResources().getString(
					R.string.menu_show_lookup);
		}

		// ((MenuItem)findViewById(R.id.switch_lookupl)).setTitle(lookupMenuTitle);
	}

	private void fillTable() {
		adapter = new IrregularVerbsListAdapter(this, R.layout.ref_item_v1,
				verbs, getColor());
		setListAdapter(adapter);
	}

	private class ReferenceTextWatcher implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			adapter.getFilter().filter(s.toString().trim());
		}

	}
}