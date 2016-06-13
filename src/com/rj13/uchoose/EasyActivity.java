package com.rj13.uchoose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EasyActivity extends Activity {
	private EditText e_question;
	private EditText e_1;
	private EditText e_2;
	private EditText e_3;
	private EditText e_4;
	private Button b_add;
	private Button b_submit;
	private String s1, s2, s3, s4;
	private ImageButton i_back;

	public int add;
	public int num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_easy);
		
		add = 0;
		num = 0;
		e_question = (EditText) findViewById(R.id.editText_question);
		e_1 = (EditText) findViewById(R.id.editText_1);
		e_2 = (EditText) findViewById(R.id.editText_2);
		e_3 = (EditText) findViewById(R.id.editText_3);
		e_4 = (EditText) findViewById(R.id.editText_4);
		
		i_back = (ImageButton)findViewById(R.id.imageButtonBack);
		i_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		b_add = (Button) findViewById(R.id.button_add);
		b_submit = (Button) findViewById(R.id.button_submit);
		b_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				add++;
				switch (add) {
				case 1:
					e_1.setVisibility(1);
					e_1.requestFocus();
					num = 1;

					break;
				case 2:
					e_2.setVisibility(1);
					e_2.requestFocus();
					num = 2;
					break;
				case 3:
					e_3.setVisibility(1);
					e_3.requestFocus();
					num = 3;
					break;
				case 4:
					e_4.setVisibility(1);
					e_4.requestFocus();
					num = 4;
					break;
				default:
					Toast.makeText(getApplicationContext(), "选项太多啦！",
							Toast.LENGTH_LONG).show();
					break;
				}
			}
		});
		b_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				s1 = e_1.getText().toString();
				s2 = e_2.getText().toString();
				s3 = e_3.getText().toString();
				s4 = e_4.getText().toString();
				Intent intent = new Intent(EasyActivity.this,
						EasyResultActivity.class);
				if (num < 2)
					Toast.makeText(EasyActivity.this, "请输入至少2个选项",
							Toast.LENGTH_LONG).show();
				else {
					if (num == 2) {
						if (s1.equals("") || s2.equals(""))
							Toast.makeText(EasyActivity.this, "选项不能为空",
									Toast.LENGTH_LONG).show();
						else {
							intent.putExtra("num", num);
							intent.putExtra("s1", s1);
							intent.putExtra("s2", s2);
							startActivity(intent);
							finish();
						}
					} else if (num == 3) {
						if (s1.equals("") || s2.equals("") || s3.equals(""))
							Toast.makeText(EasyActivity.this, "选项不能为空",
									Toast.LENGTH_LONG).show();
						else {
							intent.putExtra("num", num);
							intent.putExtra("s1", s1);
							intent.putExtra("s2", s2);
							intent.putExtra("s3", s3);
							startActivity(intent);
							finish();
						}
					} else if (num == 4) {
						if (s1.equals("") || s2.equals("") || s3.equals("")
								|| s4.equals(""))
							Toast.makeText(EasyActivity.this, "选项不能为空",
									Toast.LENGTH_LONG).show();
						else {
							intent.putExtra("num", num);
							intent.putExtra("s1", s1);
							intent.putExtra("s2", s2);
							intent.putExtra("s3", s3);
							intent.putExtra("s4", s4);
							startActivity(intent);
							finish();
						}
					}

					
				}
			}
		});

	}

}
