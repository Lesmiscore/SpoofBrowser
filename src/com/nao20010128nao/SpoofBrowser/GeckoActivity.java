package com.nao20010128nao.SpoofBrowser;
import com.nao20010128nao.SpoofBrowser.classes.*;
import android.os.*;
import com.nao20010128nao.SpoofBrowser.v3.*;
import com.nao20010128nao.SpoofBrowser.v4.*;
public class GeckoActivity extends ExpandableBrowserBase
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    addJS(new sidebarObject(),"window.sidebar");
		changeUA(R.string.ua_ff);
	    //loadUrl("http://www.google.com/");
	}
}
