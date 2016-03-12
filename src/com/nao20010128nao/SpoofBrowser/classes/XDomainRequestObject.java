package com.nao20010128nao.SpoofBrowser.classes;
import org.apache.http.client.methods.*;
import android.os.*;
import java.net.*;
import java.io.*;
import java.nio.charset.*;
import android.webkit.*;

public class XDomainRequestObject{
	@JavascriptInterface
	public Runnable onerror=null;
	@JavascriptInterface
	public Runnable onload=null;
	@JavascriptInterface
	public Runnable onprogress=null;
	@JavascriptInterface
	public String contentType=null;
	@JavascriptInterface
	public String responseText=null;
	private Thread t=null;
	private String rm=null;
	private String urlx=null;
	private Handler mHandler=null;
	@JavascriptInterface
	public void open(String respmode,String url){
		rm=respmode;
		urlx=url;
		mHandler=new Handler();
		if(onerror==null)return;/*Protect from force stop*/
		if(onload==null||onprogress==null)mHandler.post(onerror);
		t=new Thread(){
			@Override
			public void run(){
				try{
					URL uri = new URL(urlx);
					HttpURLConnection connection = null;
                    StringBuilder sb=new StringBuilder();
					try {
						connection = (HttpURLConnection) uri.openConnection();
						connection.setRequestMethod(rm);
						if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
							try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),StandardCharsets.UTF_8);
							BufferedReader reader = new BufferedReader(isr)) {
								String line;
								while ((line = reader.readLine()) != null) {
									//System.out.println(line);
									mHandler.post(onprogress);
									sb.append(line+"¥n");
								}
							}
						}else mHandler.post(onerror);
					} finally {
						if (connection != null) {
							connection.disconnect();
						}else{
							mHandler.post(onerror);
						}
					}
					responseText=sb.toString();
					mHandler.post(onload);
				}catch(Throwable e){
					mHandler.post(onerror);
				}
			}
		};
	}
	@JavascriptInterface
	public void send(Object param){
		t.start();
	}
	@JavascriptInterface
	public void abort(){
		t.stop();
	}
}
