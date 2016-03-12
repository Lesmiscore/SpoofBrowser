package com.nao20010128nao.SpoofBrowser.v3.classes;
import android.app.*;
import android.os.*;
import com.nao20010128nao.SpoofBrowser.*;
import android.view.*;
import android.widget.*;
import android.text.*;
import com.nao20010128nao.SpoofBrowser.v3.*;
import com.nao20010128nao.SpoofBrowser.v4.*;
public class SlideView extends Activity{
	LinearLayout mMainView,mSlideView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.slideview);
		mMainView=(LinearLayout)findViewById(R.id.mainview);
		mSlideView=(LinearLayout)findViewById(R.id.slideview);
	}

	@Override
	public void setContentView(int layoutResID)
	{
		// TODO: Implement this method
		setContentView(LayoutInflater.from(this).inflate(R.layout.main, null));
	}

	@Override
	public void setContentView(View view)
	{
		// TODO: Implement this method
		mMainView.removeView(bv);
		bv=view;
		mMainView.addView(view);
	}
	private View bv;
	@Override
	public void setContentViewToSlide(int layoutResID)
	{
		// TODO: Implement this method
		setContentViewToSlide(LayoutInflater.from(this).inflate(R.layout.main, null));
	}

	@Override
	public void setContentViewToSlide(View view)
	{
		// TODO: Implement this method
		mSlideView.addView(view);
	}
}
