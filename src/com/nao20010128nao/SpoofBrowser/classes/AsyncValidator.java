package com.nao20010128nao.SpoofBrowser.classes;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * AsyncTaskの引数は、デフォルトでは AsyncTask<Params, Progress, Result>です。これはそれぞれ <入力パラメータ、進行度、結果のデータ型> を表しています。
 * 入力パラメータはActivityのexecute()で渡した引数の型です。
 * 今回は、入力パラメータをStringで渡し、進行度をIntegerで指定してUIを更新し、最終結果をLongで返すようにしています。
 */
public class AsyncValidator extends AsyncTask<String, Integer, Long> {

    Context context;
    ValidatingFragment progressDialog = null;    // ロード中画面のプログレスダイアログ作成

    /**
     * コンストラクタ
     */
    public AsyncValidator(Context context){
        this.context = context;
		this.mActivity=((Activity) context);
    }

    /**
     * バッググラウンド処理の前処理（準備）
     * UI Thread処理
     */
    @Override
    protected void onPreExecute(){
        progressDialog = ValidatingFragment.newInstance("処理中", "しばらくお待ちください", true);
        progressDialog.show(mActivity.getFragmentManager(), "progress");
    }
    protected Activity mActivity=null;
    /**
     * バックグラウンド処理
     */
    @Override
    protected Long doInBackground(String... params) {
        try{
            String vali=Tools.encryptStr(Tools.getAssert("Test.binary"));
			if(vali==""||vali.toLowerCase()!="b3a34dd940c6c3405bcc598b9bef8c743e53287f")return Consts.VALIDATE_HashError;
			
        } catch (Throwable e) {
            Log.d("test", "Error");
			return Consts.VALIDATE_Error;
        }  
        return Consts.VALIDATE_OK;
    }

    /**
     * バックグラウンド処理が終わった後の処理（表示の更新）
     */
    @Override
    protected void onPostExecute(Long result){
        if (progressDialog.getShowsDialog())
            progressDialog.dismiss();

        if (result != null){
            Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "NG", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 中止された際の処理
     */
    @Override
    protected void onCancelled(){
        if (progressDialog.getShowsDialog()){
            progressDialog.dismiss();
        }
        Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
    }
}
