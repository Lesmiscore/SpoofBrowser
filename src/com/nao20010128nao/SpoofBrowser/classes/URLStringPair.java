package com.nao20010128nao.SpoofBrowser.classes;
import java.net.*;

public class URLStringPair
{
	public URLStringPair(URL url,String string){
		this.url=url;this.string=string;
	}
	private URL url;private String string;
	public URL getUrl(){
		return url;
	}
	public String getString(){
		return string;
	}
}
