package com.nao20010128nao.SpoofBrowser.v4;
import com.nao20010128nao.SpoofBrowser.classes.*;
import android.os.*;
import android.transition.*;
import com.nao20010128nao.SpoofBrowser.*;
import com.nao20010128nao.SpoofBrowser.v3.classes.*;
import com.nao20010128nao.SpoofBrowser.v4.classes.*;
import com.nao20010128nao.SpoofBrowser.v3.*;
public class ChromeActivity extends ExpandableBrowserBaseV4
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addJS(new chromeObject(),"window.chrome");
		addJS(new webkitIsFullScreenObject(),"document.webkitIsFullScreen");
		changeUA(R.string.ua_chrome);
	    //loadUrl("http://www.google.com/");
	}
}
