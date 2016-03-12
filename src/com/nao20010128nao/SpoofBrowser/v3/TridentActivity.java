package com.nao20010128nao.SpoofBrowser.v3;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.webkit.*;
import android.view.View.*;
import android.text.*;
import android.graphics.*;
import com.nao20010128nao.SpoofBrowser.classes.*;
import com.nao20010128nao.SpoofBrowser.*;
import com.nao20010128nao.SpoofBrowser.v3.classes.*;
import com.nao20010128nao.SpoofBrowser.v4.*;
public class TridentActivity extends ExpandableBrowserBaseV3
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addJS(new ClipBoardEngine(), "document.clipboardData");
		addJS(new uniqueIDObject(),"window.uniqueID");
		addJS(new XDomainRequestObject(),"window.XDomainRequest");
		changeUA(R.string.ua_msie);
	    //loadUrl("http://www.google.com/");
	}
}
