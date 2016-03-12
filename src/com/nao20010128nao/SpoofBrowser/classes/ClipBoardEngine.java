package com.nao20010128nao.SpoofBrowser.classes;
import android.content.*;
import android.app.*;
import com.nao20010128nao.SpoofBrowser.*;
import android.webkit.*;
import com.nao20010128nao.SpoofBrowser.v3.*;
import com.nao20010128nao.SpoofBrowser.v4.*;
public class ClipBoardEngine
{
	private Boolean allowed,asked;
	private Context a=new Activity();private String c;
	/*ClipBoardEngine(Context act){
		a=act;
	}*/
	@JavascriptInterface
	public void setData(String data){
		if(!asked){
			ask();
		}
		if(allowed){
			//(String)((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).getText()
			((ClipboardManager)a.getSystemService(a.CLIPBOARD_SERVICE)).setText(data);
		}else{
			c=data;
		}
	}
	@JavascriptInterface
	public String getData(String format){
		if(!asked){
			ask();
		}
		if(allowed){
			return(String)((ClipboardManager)a.getSystemService(a.CLIPBOARD_SERVICE)).getText();
			//((ClipboardManager)a.getSystemService(a.CLIPBOARD_SERVICE)).setText(data);
		}else{
			return c;
		}
	}
	private String x(int id){
		return a.getResources().getString(id);
	}
	private void ask(){
		new AlertDialog.Builder(a)
			.setTitle(x(android.R.string.dialog_alert_title))
			.setMessage(x(R.string.cbdialog_mes))
			.setPositiveButton(a.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					asked=true;
					allowed=true;
				}
			})
			.setNegativeButton(a.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					asked=true;
					allowed=false;
				}
			})
			.show();
	}
}
