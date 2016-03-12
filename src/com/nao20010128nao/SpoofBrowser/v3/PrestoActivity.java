package com.nao20010128nao.SpoofBrowser.v3;
import com.nao20010128nao.SpoofBrowser.classes.*;
import android.os.*;
import com.nao20010128nao.SpoofBrowser.*;
import com.nao20010128nao.SpoofBrowser.v3.classes.*;
import com.nao20010128nao.SpoofBrowser.v4.*;
public class PrestoActivity extends ExpandableBrowserBaseV3
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
		addJS(new operaObject(),"window.opera");
		changeUA(R.string.ua_presto);
	    //loadUrl("http://www.google.com/");
	}
}
