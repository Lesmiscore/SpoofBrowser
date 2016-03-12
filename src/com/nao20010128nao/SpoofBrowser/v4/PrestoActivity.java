package com.nao20010128nao.SpoofBrowser.v4;
import com.nao20010128nao.SpoofBrowser.classes.*;
import android.os.*;
import com.nao20010128nao.SpoofBrowser.*;
import com.nao20010128nao.SpoofBrowser.v3.classes.*;
import com.nao20010128nao.SpoofBrowser.v4.classes.*;
import com.nao20010128nao.SpoofBrowser.v3.*;
public class PrestoActivity extends ExpandableBrowserBaseV4
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
