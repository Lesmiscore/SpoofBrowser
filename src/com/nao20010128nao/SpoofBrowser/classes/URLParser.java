package com.nao20010128nao.SpoofBrowser.classes;
import java.net.*;
import android.webkit.*;

public class URLParser{
	URL bd;
	URLParser(String str){
		bd=Tools.getURL(str);
	}
	URLParser(URL url){
		bd=url;
	}
	URLParser(WebView wv){
		bd=Tools.getURL(wv.getUrl());
	}
	URLParser(ExpandableBrowserBase ebb){
		bd=Tools.getURL(ebb.mBrowser.getUrl());
	}
	@JavascriptInterface
	public String getHost(){
		return bd.getHost();
	}
	@JavascriptInterface
	public Integer getPort(){
		return bd.getPort();
	}
	@JavascriptInterface
	public String getPath(){
		return bd.getPath();
	}
	@JavascriptInterface
	public String getProtocol(){
		return bd.getProtocol();
	}
	@JavascriptInterface
	public String getQuery(){
		return bd.getQuery();
	}
	@JavascriptInterface
	public String getRef(){
		return bd.getRef();
	}
	@JavascriptInterface
	public String getUserInfo(){
		return bd.getUserInfo();
	}
}
