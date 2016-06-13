package com.rj13.uchoose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class EasyResultActivity extends Activity {

	private ImageButton b_s1;
	private ImageButton b_s2;
	private ImageButton b_s3;
	private ImageButton b_s4;
	private static String s[] = new String[4];
	private int num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// flag = 0;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_easyresult);
		Intent intent = getIntent();
		num = intent.getIntExtra("num", 0);
		s[0] = intent.getStringExtra("s1");
		s[1] = intent.getStringExtra("s2");
		s[2] = intent.getStringExtra("s3");
		s[3] = intent.getStringExtra("s4");
		b_s1 = (ImageButton) findViewById(R.id.imageButton_s1);
		b_s2 = (ImageButton) findViewById(R.id.imageButton_s2);
		b_s3 = (ImageButton) findViewById(R.id.imageButton_s3);
		b_s4 = (ImageButton) findViewById(R.id.imageButton_s4);
		switch (num) {
		case 1:
			b_s1.setVisibility(1);
			break;
		case 2:
			b_s1.setVisibility(1);
			b_s2.setVisibility(1);
			break;
		case 3:
			b_s1.setVisibility(1);
			b_s2.setVisibility(1);
			b_s3.setVisibility(1);
			break;
		case 4:
			b_s1.setVisibility(1);
			b_s2.setVisibility(1);
			b_s3.setVisibility(1);
			b_s4.setVisibility(1);
			break;
		default:
			break;
		}
		b_s1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EasyResultActivity.this,
						EasyResultDetailActivity.class);
				int tmp = (int) (Math.random() * 1000);
				// Toast.makeText(EasyResultActivity.this, tmp+"    "+s[tmp%3],
				// Toast.LENGTH_LONG).show();
				intent.putExtra("result", s[tmp % num]);
				startActivity(intent);

				finish();

			}
		});
		b_s2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EasyResultActivity.this,
						EasyResultDetailActivity.class);
				int tmp = (int) (Math.random() * 1000);
				// Toast.makeText(EasyResultActivity.this, tmp+"    "+s[tmp%3],
				// Toast.LENGTH_LONG).show();
				intent.putExtra("result", s[tmp % num]);
				startActivity(intent);
				finish();

			}
		});
		b_s3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EasyResultActivity.this,
						EasyResultDetailActivity.class);
				int tmp = (int) (Math.random() * 1000);
				// Toast.makeText(EasyResultActivity.this, tmp+"    "+s[tmp%3],
				// Toast.LENGTH_LONG).show();
				intent.putExtra("result", s[tmp % num]);
				startActivity(intent);
				finish();

			}
		});
		b_s4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EasyResultActivity.this,
						EasyResultDetailActivity.class);
				int tmp = (int) (Math.random() * 1000);
				// Toast.makeText(EasyResultActivity.this, tmp+"    "+s[tmp%3],
				// Toast.LENGTH_LONG).show();
				intent.putExtra("result", s[tmp % num]);
				startActivity(intent);
				finish();

			}
		});
	}
}
