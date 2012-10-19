package by.kunin.android.langrescue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;

import by.kunin.android.langrescue.R;
import by.kunin.android.langrescue.activity.ViewFlipperActivity.OnViewFlippedListener;

public class TextSwitcherNavigator extends RelativeLayout implements
															OnViewFlippedListener {
	
	protected TextSwitcher mTextSwitcher;
	protected Button mNextButton;
	protected Button mPrevButton;
	protected View mRoot;

	private OnNavigationListenter mNavigationListenter;
	
	public TextSwitcherNavigator(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		
		LayoutInflater la = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		la.inflate(R.layout.text_switcher_navigator, this);
	}

	public TextSwitcherNavigator(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public void setOnNavigationListener(OnNavigationListenter onNavigationListenter) {
		mNavigationListenter = onNavigationListenter;
	}
	
	@Override
	public void onNextView(View newView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPreviousView(View newView) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		
		setUpViews();
		setUpListeners();
	}

	private void setUpListeners() {
		mNextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onNextClicked();
//				navigateToNext("Next", true);
				
				if (mNavigationListenter != null) {
					mNavigationListenter.onNextPressed();
				}
			}
		});
		
		mPrevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onPrevClicked();
//				navigateToPrev("Previous", true);
				
				if (mNavigationListenter != null) {
					mNavigationListenter.onPrevPressed();
				}
			}
		});
	}

	private void setUpViews() {
		mTextSwitcher = (TextSwitcher) findViewById(R.id.text_switcher);
		mNextButton = (Button) findViewById(R.id.button_next);
		mPrevButton = (Button) findViewById(R.id.button_prev);
		mRoot = findViewById(R.id.root);
	}
	
	public void navigateToNext(CharSequence text, boolean callback) {
		mTextSwitcher.setInAnimation(getContext(), R.anim.in_animation1);
		mTextSwitcher.setOutAnimation(getContext(), R.anim.out_animation1);
		mTextSwitcher.setText(text);
		
		if (mNavigationListenter != null && callback) {
			mNavigationListenter.onNextPressed();
		}
		
	}
	
	public void navigateToPrev(CharSequence text, boolean callback) {
		mTextSwitcher.setInAnimation(getContext(), R.anim.in_animation);
		mTextSwitcher.setOutAnimation(getContext(), R.anim.out_animation);
		mTextSwitcher.setText(text);
		
		if (mNavigationListenter != null && callback) {
			mNavigationListenter.onPrevPressed();
		}
	}
	
	private void onNextClicked() {
		Animation animationNext = AnimationUtils.loadAnimation(getContext(), R.anim.btn_style_next);
		mNextButton.startAnimation(animationNext);
	}
	
	private void onPrevClicked() {
		Animation animationPrev = AnimationUtils.loadAnimation(getContext(), R.anim.btn_style_previous);
		mPrevButton.startAnimation(animationPrev);
	}
	
	public interface OnNavigationListenter {
		public void onNextPressed();
		public void onPrevPressed();
	}

}
