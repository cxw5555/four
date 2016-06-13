package com.rj13.uchoose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class LoadingActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_loading);
		Thread thread=new Thread(myRun);
		thread.start();
	}
	Runnable myRun=new Runnable() {
		
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				Intent intent=new Intent(LoadingActivity.this,LoginActivity.class);
			    startActivity(intent);
			    LoadingActivity.this.finish();
				
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	};


}
