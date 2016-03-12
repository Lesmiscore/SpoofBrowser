package com.nao20010128nao.SpoofBrowser.v3.classes;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.webkit.*;
import android.view.View.*;
import android.text.*;
import android.graphics.*;
import com.nao20010128nao.SpoofBrowser.*;
import com.nao20010128nao.SpoofBrowser.classes.*;
import android.content.res.*;
import java.security.*;
import com.nao20010128nao.ToolBox.*;
import android.media.*;
import android.util.*;
import android.graphics.drawable.*;
import android.content.pm.*;
import java.net.*;
import android.net.*;
import com.nao20010128nao.SpoofBrowser.v3.*;
import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.view.ContextMenu.*;
import com.nao20010128nao.SpoofBrowser.v4.*;
public class ExpandableBrowserBaseV3 extends ActivityX{
	public static final int MENU_SELECT_FULL =0;
	public static final int MENU_SELECT_THEME=1;
	public static final int MENU_SELECT_SHARE=2;
	//public static final JavaScriptRunner initscript=new JavaScriptRunner(Tools.getAssert("MakeSandBoxPlant.js"));
	String LOGTAG="DRAWER";
	WebView mBrowser;
	Button mGo,mBack,mPrev,mUpd,mShare;
	EditText mUrlBox;
	Activity Me=this;
	ProgressBar mPCircle;
	ActionBar mActionBar;
	LinearLayout mNav,mAddr,mMainLayout,mDrawerLayout;
	MenuItem fullswitcher,themeswitcher,sharebutton;
	boolean isDark=false,cancontinue;
	TextView mPageName,mPageNameTitle;
	CheckBox mPBTE;
	DrawerLayout mDrawer;
	ActionBarDrawerToggle mABDT;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.browser_v3_3);
		Integer d=Color.argb(1,2,3,4);
		Log.d("DEBUG",d.toHexString(d));
		if(Tools.canUseActionBar(this)){
			//Toast.makeText(Me,"ActionBar OK",Toast.LENGTH_SHORT).show();
			mActionBar=getActionBar();
			if(mActionBar!=null)mActionBar.hide();
		}else{
			
		}
		mGo=(Button)findViewById(R.id.btngo);
		mUrlBox=(EditText)findViewById(R.id.UrlBox);
		mAddr=(LinearLayout)findViewById(R.id.AddressBar);
		mBrowser=(WebView)findViewById(R.id.browser);
		mBack=(Button)findViewById(R.id.btnback);
		mPrev=(Button)findViewById(R.id.btnprev);
		mUpd=(Button)findViewById(R.id.btnupd);
		mPCircle=(ProgressBar)findViewById(R.id.pcircle);
		mNav=(LinearLayout)findViewById(R.id.NavigationBar);
		mPageName=(TextView)findViewById(R.id.pagename);
		mPageNameTitle=(TextView)findViewById(R.id.pagenametitle);
		mPBTE=(CheckBox)findViewById(R.id.pbte);
		mDrawer=(DrawerLayout)findViewById(R.id.drawer_layout);
		mMainLayout=(LinearLayout)findViewById(R.id.mainview);
		mDrawerLayout=(LinearLayout)findViewById(R.id.slideview);
		mShare=(Button)findViewById(R.id.btnshare);
		mGo.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View a){
					mBrowser.loadUrl(((SpannableStringBuilder)mUrlBox.getText()).toString());
				}
			});
		mBack.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View a){
					mBrowser.goBack();
				}
			});
		mPrev.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View a){
					mBrowser.goForward();
				}
			});
		mUpd.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View a){
					mBrowser.loadUrl(((SpannableStringBuilder)mUrlBox.getText()).toString());
					//mBrowser.
				}
			});
		mShare.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View a){
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(mBrowser.getUrl()));
					i.putExtra(Intent.EXTRA_TEXT,mBrowser.getUrl());
					startActivity(Intent.createChooser(i, getResources().getString(R.string.share)));
				}
			});
		mBrowser.setWebViewClient(new WebViewClient(){
				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon) {
					super.onPageStarted(view, url, favicon);
					mPCircle.setVisibility(ProgressBar.VISIBLE);
					URL u=Tools.getURL(url);
					if(u!=null&&u.getHost()=="adf.ly"){
						runJS(Tools.getAssert("SkipAdfly.js",Me));
						//Toast.makeText(Me,x(R.string.SkippedAdfly),Toast.LENGTH_LONG).show();
					}
					mUrlBox.setText(url);
					mPageName.setText(url);
				}
				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					//mUrlBox.setText(url);
					mPCircle.setVisibility(ProgressBar.INVISIBLE);
					mPageName.setText(mBrowser.getTitle());
				}
				@Override
				public boolean shouldOverrideUrlLoading(WebView webView, String url){
					return false;
				}
				@Override
				public boolean onJsAlert(WebView view, String url, String message,JsResult result) {
					try {
						Toast.makeText(Me, message, Toast.LENGTH_SHORT).show();
						return true;
					} finally {
						result.confirm();
					}
				};
			});
		mABDT=new ActionBarDrawerToggle(this,
										mDrawer,
										R.drawable.ic_drawer,
										R.string.drawer_open,
										R.string.drawer_close){
			@Override
			public void onDrawerClosed(View drawerView) {
				Log.i(LOGTAG, "onDrawerClosed");
			}
			@Override
			public void onDrawerOpened(View drawerView) {
				Log.i(LOGTAG, "onDrawerOpened");
			}
			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				// ActionBarDrawerToggleクラス内の同メソッドにてアイコンのアニメーションの処理をしている。
				// overrideするときは気を付けること。
				super.onDrawerSlide(drawerView, slideOffset);
				Log.i(LOGTAG, "onDrawerSlide : " + slideOffset);
				/*mDrawerLayout.setBackgroundColor(
					Tools.changeAlpha(
					getResources().getColor(R.color.slide_back),
					(byte)(255*slideOffset)));*/
				int d;
				Log.d("DEBUG_RESULT",Integer.toHexString(d=Tools.calcAlphaBrightness(
									getResources().getColor(
									R.color.slide_back),slideOffset)));
				mDrawerLayout.setBackgroundColor(d);
			}
			@Override
			public void onDrawerStateChanged(int newState) {
				// 表示済み、閉じ済みの状態：0
				// ドラッグ中状態:1
				// ドラッグを放した後のアニメーション中：2
				Log.i(LOGTAG, "onDrawerStateChanged  new state : " + newState);
			}
		};
		mPBTE.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View p){
				Tools.setSettings("isBack",mPBTE.isChecked());
			}
		});
		mPBTE.setChecked(Tools.getSettings("isBack",false));
		//mABDT.onDrawerSlide((View)mDrawer,(float)1.0);
		mDrawer.setDrawerListener(mABDT);
		mBrowser.setClickable(true);
		mBrowser.getSettings().setJavaScriptEnabled(true);
		mBrowser.getSettings().setBuiltInZoomControls(true);
		mBrowser.getSettings().setPluginState(WebSettings.PluginState.ON);
		if(Build.VERSION.SDK_INT>10)getWindow().setFlags(16777216,16777216);
		Typeface tf=Tools.getFontByAssert(this,"meiryo.otf.zip");
		//if(tf==null)Toast.makeText(this,"tf is null",Toast.LENGTH_LONG).show();
		int color = getResources().getColor(R.color.slide_back);
		mDrawerLayout.setBackgroundColor(color); 
		changeFont(tf);
		loadUrl("http://www.google.co.jp/");
	}
	public void changeFont(Typeface tf){
		Button[] views={mGo,mBack,mPrev,mUpd,mShare};
		for(Button i:views){
			i.setTypeface(tf);
		}
		TextView[] views2={mPageName,mPageNameTitle};
		for(TextView i:views2){
			i.setTypeface(tf);
		}
		mUrlBox.setTypeface(tf);
		mPBTE.setTypeface(tf);
	}
	private boolean irc;
	public Boolean isRotationChanged(){
		boolean t=irc;
		irc=false;
		return t;
	}
	public void rotate(){
		irc=true;
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mBrowser.loadUrl(((SpannableStringBuilder)mUrlBox.getText()).toString());
		rotate();
	}
	@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);        
        outState.putString("STRING",mUrlBox.getText().toString());
		outState.putBoolean("ISDARK",isDark);
		outState.putBoolean("CAN_CONTINUE",true);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
		mUrlBox.setText(savedInstanceState.getString("STRING"));        
        mBrowser.loadUrl(savedInstanceState.getString("STRING"));
		isDark=savedInstanceState.getBoolean("ISDARK",false);
		//applyThemeSwitch();
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{
		// TODO: Implement this method
		//return super.onMenuOpened(featureId, menu);
		mDrawer.openDrawer(Gravity.RIGHT);
		mDrawer.computeScroll();
		return true;
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
	{
		// TODO: Implement this method
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	public void applyThemeSwitch(){
		if(isDark){
			themeswitcher.setTitle(R.string.solt);
			setTheme(R.style.FullSize2_);
		}else{
			themeswitcher.setTitle(R.string.sodt);
			setTheme(R.style.FullSize2);
		}
	}
	public void fullswitch(){
		if(getAddrBarVisiblity()){
			hideAddrBar();
			mNav.setVisibility(View.GONE);
			fullswitcher.setTitle(R.string.son);
			Toast.makeText(this,R.string.BackToRestore,Toast.LENGTH_LONG).show();
		}else{
			showAddrBar();
			mNav.setVisibility(View.VISIBLE);
			fullswitcher.setTitle(R.string.sof);
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode != KeyEvent.KEYCODE_BACK){
			return super.onKeyDown(keyCode, event);
		}else{
			if(mBrowser.canGoBack()&&!mPBTE.isChecked()){
				mBrowser.goBack();
			}else if(mPBTE.isChecked()){
				finish();
			}
			return false;
		}
	}
	public void setContentView(int layoutResID){/*Not supported method. It does not any work.*/}
	public void loadUrl(String url){
		if(isRotationChanged()){
			return;
		}
		mBrowser.loadUrl(url);
		mUrlBox.setText(url);
	}
	public void addJS(Object obj,String classpath){
		mBrowser.addJavascriptInterface(obj,classpath);
	}
	public void changeUA(String ua){
		mBrowser.getSettings().setUserAgentString(ua);
	}
	public void runJS(String script){
		mBrowser.evaluateJavascript(script,null);
	}
	public String x(int id){
		Resources d=super.getResources();
		return d.getString(id);
	}
	public void changeUA(int ua){
		changeUA(x(ua));
	}
	public void hideAddrBar(){
		if(Tools.canUseActionBar(this)){
			mActionBar.hide();
		}else{
			mAddr.setVisibility(View.GONE);
		}
	}
	public void showAddrBar(){
		if(Tools.canUseActionBar(this)){
			mActionBar.show();
		}else{
			mAddr.setVisibility(View.VISIBLE);
		}
	}
	public boolean getAddrBarVisiblity(){
		if(Tools.canUseActionBar(this)){
			return mActionBar.isShowing();
		}else{
			return mAddr.getVisibility()==View.VISIBLE;
		}
	}
}
