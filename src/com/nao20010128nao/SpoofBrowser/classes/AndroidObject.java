package com.nao20010128nao.SpoofBrowser.classes;
import android.widget.*;
import android.app.*;
import android.webkit.*;

public class AndroidObject{
	private Activity act;
	private WebView wv;
	public URLParser URLParse;
	AndroidObject(Activity a,WebView w){
		act=a;
		wv=w;
		URLParse=new URLParser(w);
	}
	AndroidObject(ExpandableBrowserBase ebb){
		act=ebb;
		wv=ebb.mBrowser;
	}
	@JavascriptInterface
	public void Toast(String str){
		Toast.makeText(act,str,Toast.LENGTH_LONG);
	}
	@JavascriptInterface
	public void disableOnMove(){
		wv.evaluateJavascript(Tools.getAssert("DisableOnMove.js",act),null);
	}
}
