package com.nao20010128nao.SpoofBrowser.classes;
import android.webkit.*;

public class JavaScriptRunner
{
	public String JavaScriptCode="";
	public JavaScriptRunner(String code){
		this.JavaScriptCode=code;
	}
	public void RunOnWebView(WebView wv){
		wv.evaluateJavascript(JavaScriptCode,null);
	}
	public void RunDirect(){
		
	}
}
