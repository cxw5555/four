package com.rj13.uchoose;

import android.animation.IntEvaluator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EasyResultDetailActivity extends Activity {

	TextView t_result;

	Button b_ok, b_again;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_easyresultdetail);
		Intent intent = getIntent();
		String s = intent.getStringExtra("result");
		t_result = (TextView) findViewById(R.id.textView_easyresult);
		t_result.setText(s);
		b_ok = (Button) findViewById(R.id.button_ok);
		b_again = (Button) findViewById(R.id.Button_again);

		// 两个按钮反了大概是玄学问题
		b_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(EasyResultDetailActivity.this,
//						MainLayoutActivity.class);
//				startActivity(intent);

				finish();

			}
		});
		b_again.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(EasyResultDetailActivity.this,
				 EasyActivity.class);
				 startActivity(intent);
				finish();
			}
		});
	}
}
