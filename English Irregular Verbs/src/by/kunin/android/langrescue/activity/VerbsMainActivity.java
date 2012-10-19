package by.kunin.android.langrescue.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import by.kunin.android.langrescue.R;
import by.kunin.android.langrescue.activity.ViewFlipperActivity.OnViewFlippedListener;
import by.kunin.android.langrescue.view.TextSwitcherNavigator;
import by.kunin.android.langrescue.view.TextSwitcherNavigator.OnNavigationListenter;

public class VerbsMainActivity extends ViewFlipperActivity 
                               implements OnViewFlippedListener, OnNavigationListenter {
	
	Object currentViewTag;
	TextSwitcherNavigator mNavigator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verbs_main_activity);
		
		mViewSwitcher = (ViewFlipper)findViewById(R.id.view_flipper);
		mNavigator = (TextSwitcherNavigator) findViewById(R.id.navigator);
		
		mViewSwitchedListener = this;
		mNavigator.setOnNavigationListener(this);
		
		currentViewTag = getLastNonConfigurationInstance();
		if (currentViewTag != null) {
			while (!mViewSwitcher.getCurrentView().getTag().equals(currentViewTag)) {
				mViewSwitcher.showNext();
			}
		}
	}
	
	@Override
	public Object onRetainNonConfigurationInstance() {
		return currentViewTag;
	}

	@Override
	public void onNextView(View newView) {
		currentViewTag = newView.getTag();
		
		mNavigator.navigateToNext(newView.getTag().toString(), false);
	}

	@Override
	public void onPreviousView(View newView) {
		currentViewTag = newView.getTag();
		
		mNavigator.navigateToPrev(newView.getTag().toString(), false);
	}

	@Override
	public void onNextPressed() {
		showNextView();
	}

	@Override
	public void onPrevPressed() {
		showPreviousView();
	}
	
}
