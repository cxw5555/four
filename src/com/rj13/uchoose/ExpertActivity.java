package com.rj13.uchoose;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class ExpertActivity extends Activity {
	// private boolean touch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.expert);
		// touch = false;
		ImageView iv = (ImageView) findViewById(R.id.im_expert);
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Dialog dialog;
				Builder b = new AlertDialog.Builder(ExpertActivity.this);
				b.setTitle("提示");
				b.setMessage("这个功能程序猿哥哥正在日以继夜的开发中...");
				b.setPositiveButton("好的",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub

							}
						});
				dialog = b.create();
				dialog = b.show();
			}
		});
	}
	// @Override
	// public boolean onTouchEvent(MotionEvent event) {
	// // TODO Auto-generated method stub
	//
	// if (touch == false) {
	// Dialog dialog;
	// Builder b = new AlertDialog.Builder(this);
	// b.setTitle("提示");
	// b.setMessage("这个功能程序猿哥哥正在日以继夜的开发中...");
	// dialog = b.create();
	// dialog = b.show();
	// }
	// return false;
	//
	// }
}
