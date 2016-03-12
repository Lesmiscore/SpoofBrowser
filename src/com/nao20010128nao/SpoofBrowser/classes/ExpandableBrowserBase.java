package com.nao20010128nao.SpoofBrowser.classes;
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
import com.nao20010128nao.SpoofBrowser.v4.*;
public class ExpandableBrowserBase extends ActivityX{
	public static final int MENU_SELECT_FULL =0;
	public static final int MENU_SELECT_THEME=1;
	public static final int MENU_SELECT_SHARE=2;
	//public static final JavaScriptRunner initscript=new JavaScriptRunner(Tools.getAssert("MakeSandBoxPlant.js"));
	WebView mBrowser;
	Button mGo,mBack,mPrev,mUpd;
	EditText mUrlBox;
	Activity Me=this;
	ProgressBar mPCircle;
	ActionBar mActionBar;
	LinearLayout mNav,mAddr;
	MenuItem fullswitcher,themeswitcher,sharebutton;
	boolean isDark=false,cancontinue;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.browser);
		if(Tools.canUseActionBar()){
			//Toast.makeText(Me,"ActionBar OK",Toast.LENGTH_SHORT).show();
			mActionBar=getActionBar();
			mActionBar.setDisplayUseLogoEnabled(false);
			mActionBar.setDisplayShowTitleEnabled(false);
			mActionBar.setIcon(Consts.zero);
			mActionBar.setLogo(Consts.zero);
			int options = mActionBar.getDisplayOptions();
			if ((options & ActionBar.DISPLAY_SHOW_CUSTOM) != ActionBar.DISPLAY_SHOW_CUSTOM) {
				int abopt=options|ActionBar.DISPLAY_SHOW_CUSTOM;
				if((abopt&ActionBar.DISPLAY_USE_LOGO)==ActionBar.DISPLAY_USE_LOGO){
					abopt=abopt^ActionBar.DISPLAY_USE_LOGO;
				}
				mActionBar.setDisplayOptions(abopt);
				if (mActionBar.getCustomView() == null) {
					mActionBar.setCustomView(R.layout.ablayout);
					ViewGroup group = (ViewGroup) mActionBar.getCustomView();
					mGo=(Button)group.findViewById(R.id.btngo);
					mUrlBox=(EditText)group.findViewById(R.id.UrlBox);
				}
			}
		}else{
			mGo=(Button)findViewById(R.id.btngo);
			mUrlBox=(EditText)findViewById(R.id.UrlBox);
			mAddr=(LinearLayout)findViewById(R.id.AddressBar);
		}
		mBrowser=(WebView)findViewById(R.id.browser);
		mBack=(Button)findViewById(R.id.btnback);
		mPrev=(Button)findViewById(R.id.btnprev);
		mUpd=(Button)findViewById(R.id.btnupd);
		mPCircle=(ProgressBar)findViewById(R.id.pcircle);
		mNav=(LinearLayout)findViewById(R.id.NavigationBar);
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
				}
				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					//mUrlBox.setText(url);
					mPCircle.setVisibility(ProgressBar.INVISIBLE);
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
		mBrowser.setClickable(true);
		mBrowser.getSettings().setJavaScriptEnabled(true);
		mBrowser.getSettings().setBuiltInZoomControls(true);
		mBrowser.getSettings().setPluginState(WebSettings.PluginState.ON);
		addJS(new AndroidObject(this,mBrowser),"window.Android");
		if(Build.VERSION.SDK_INT>10)getWindow().setFlags(16777216,16777216);
		Typeface tf=Tools.getFontByAssert(this,"meiryo.otf.zip");
		if(tf==null)Toast.makeText(this,"tf is null",Toast.LENGTH_LONG).show();
		Button[] views={mGo,mBack,mPrev,mUpd};
		for(Button i:views){
			i.setTypeface(tf);
		}
		mUrlBox.setTypeface(tf);
		loadUrl("http://www.google.co.jp/");
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
	public boolean onCreateOptionsMenu(Menu menu){
		fullswitcher=menu.add(0, MENU_SELECT_FULL, 0, R.string.sof);
		//themeswitcher=menu.add(0, MENU_SELECT_THEME, 0, R.string.sodt);
		sharebutton=menu.add(0, MENU_SELECT_SHARE, 0, R.string.share);
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item){
		switch (item.getItemId()) {
			case MENU_SELECT_FULL:
				fullswitch();
				break;
			case MENU_SELECT_THEME:
				applyThemeSwitch();
				isDark=!isDark;
				break;
			case MENU_SELECT_SHARE:
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(mBrowser.getUrl()));
				startActivity(Intent.createChooser(i, getResources().getString(R.string.share)));
				break;
		}
		return true;
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
			if(!getAddrBarVisiblity()){
				fullswitch();
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
