package com.rj13.uchoose;

import java.security.PublicKey;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.rj13.model.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.StaticLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserInfoActivity extends Activity {
	public static String s[] = new String[4];

	private EditText e_name;
	private EditText e_mail;
	private EditText e_phone;
	private RadioButton radio;
	public String id;

	public static Boolean isEmail(String str) {
		Boolean isEmail = false;
		String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if (str.matches(expr) || str.equals("")) {
			isEmail = true;
		}
		return isEmail;
	}

	public static boolean isPhone(String phone) {
		if (phone.equals(""))
			return true;
		Pattern pattern = Pattern
				.compile("^(13[0-9]|15[0-9]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$");
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_userinfo);

		e_name = (EditText) findViewById(R.id.editText_name);
		e_mail = (EditText) findViewById(R.id.editText_mail);
		e_phone = (EditText) findViewById(R.id.editText_phone);
		radio = (RadioButton) findViewById(R.id.radioButton1);
		Button btnclear = (Button) findViewById(R.id.button_clearinfo);
		btnclear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				e_name.setText("");
				e_mail.setText("");
				e_phone.setText("");
				radio.setChecked(true);
			}
		});
		Button btn = (Button) findViewById(R.id.button_submitinfo);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String sex = "";
				Intent intent = getIntent();
				id = intent.getStringExtra("id");
				if (radio.isChecked())
					sex = "男";
				else
					sex = "女";
				s[0] = e_name.getText().toString();
				s[1] = e_mail.getText().toString();
				s[2] = e_phone.getText().toString();
				s[3] = sex;
				if (!isEmail(s[1]))
					Toast.makeText(UserInfoActivity.this, "请输入正确的邮箱地址",
							Toast.LENGTH_LONG).show();
				else if (!isPhone(s[2]))
					Toast.makeText(UserInfoActivity.this, "请输入正确的手机号",
							Toast.LENGTH_LONG).show();
				else {
					final BmobQuery<User> query = new BmobQuery<User>();
					query.addWhereEqualTo("UserName", id);

					query.count(UserInfoActivity.this, User.class,
							new CountListener() {

								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub
									Toast.makeText(UserInfoActivity.this,
											"修改失败", Toast.LENGTH_LONG).show();
								}

								public void onSuccess(int arg0) {
									if (arg0 > 0) {
										query.addWhereEqualTo("UserName", id);
										query.findObjects(
												UserInfoActivity.this,
												new FindListener<User>() {
													public void onError(
															int arg0,
															String arg1) {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																UserInfoActivity.this,
																"查询错误",
																Toast.LENGTH_LONG)
																.show();
													}

													@Override
													public void onSuccess(
															List<User> arg0) {
														// TODO Auto-generated
														// method stub
														for (User arg : arg0) {
															if (!s[0]
																	.equals("")) {
																arg.setNickName(s[0]);
															}
															if (!s[1]
																	.equals("")) {
																arg.setMailAddress(s[1]);
															}
															if (!s[2]
																	.equals("")) {
																arg.setPhone(s[2]);
															}
															arg.setSex(s[3]);
															arg.update(
																	UserInfoActivity.this,
																	arg.getObjectId(),
																	new UpdateListener() {

																		@Override
																		public void onSuccess() {
																			// TODO
																			// Auto-generated
																			// method
																			// stub
																			Toast.makeText(
																					getApplicationContext(),
																					"信息修改成功",
																					Toast.LENGTH_LONG)
																					.show();

																		}

																		@Override
																		public void onFailure(
																				int arg0,
																				String arg1) {
																			// TODO
																			// Auto-generated
																			// method
																			// stub
																			Toast.makeText(
																					getApplicationContext(),
																					"失败了",
																					Toast.LENGTH_LONG)
																					.show();
																		}
																	});
														}
													}

												});

									}
								}
							});
					// Intent intent2 = new Intent(UserInfoActivity.this,
					// userActivity.class);
					// intent2.putExtra("id", id);
					// startActivity(intent2);
					// finish();
					Thread thread = new Thread(myRun);
					thread.start();
				}
			}
		});
	}

	Runnable myRun = new Runnable() {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				Intent intent2 = new Intent(UserInfoActivity.this,
						UserActivity.class);
				intent2.putExtra("id", id);
				startActivity(intent2);
				finish();

			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	};
}
