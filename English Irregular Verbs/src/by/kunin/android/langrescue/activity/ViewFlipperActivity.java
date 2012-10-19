package by.kunin.android.langrescue.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import by.kunin.android.langrescue.R;

public class ViewFlipperActivity extends Activity implements OnGestureListener {
	
	protected static final int MIN_VELOCYTY = 100;
	protected static final int MIN_DISTANCE = 100;
	
	protected ViewFlipper mViewSwitcher;
	protected GestureDetector mGestureDetector;
	protected OnViewFlippedListener mViewSwitchedListener;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGestureDetector = new GestureDetector(this, this);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		
		final float distanse = e1.getX() - e2.getX();
		if (Math.abs(distanse) > MIN_DISTANCE && Math.abs(velocityX) > MIN_VELOCYTY) {
			if (distanse > 0) {
				showNextView();
			} else {
				showPreviousView();
			}
		}
		
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	
	public void showNextView() {
		mViewSwitcher.setInAnimation(this, R.anim.in_animation1);
		mViewSwitcher.setOutAnimation(this, R.anim.out_animation1);
		mViewSwitcher.showNext();
		if (mViewSwitchedListener != null) {
			mViewSwitchedListener.onNextView(mViewSwitcher.getCurrentView());
		}
	}
	
	public void showPreviousView() {
		mViewSwitcher.setInAnimation(this, R.anim.in_animation);
		mViewSwitcher.setOutAnimation(this, R.anim.out_animation);
		mViewSwitcher.showPrevious();
		if (mViewSwitchedListener != null) {
			mViewSwitchedListener.onPreviousView(mViewSwitcher.getCurrentView());
		}
	}
	
	public static interface OnViewFlippedListener {
		public void onNextView(View newView);
		public void onPreviousView(View newView);
	}
	

}
