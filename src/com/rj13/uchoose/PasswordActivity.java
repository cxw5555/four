package com.rj13.uchoose;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.rj13.model.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends Activity {

	private Context mContext = this;
	private EditText oldpwdEditText;
	private EditText newpwdEditText;
	private EditText confirmpwdeditEditText;
	private String s1, s2, s3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_password);
		Intent intent = getIntent();
		final String UserName = intent.getStringExtra("id");
		oldpwdEditText = (EditText) findViewById(R.id.editText_oldpwd);
		newpwdEditText = (EditText) findViewById(R.id.editText_newpwd);
		confirmpwdeditEditText = (EditText) findViewById(R.id.editText_confirmpwd);
		Button okButton = (Button) findViewById(R.id.buttonOK);
		Button backButton = (Button) findViewById(R.id.ButtonBACK);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		okButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				s1 = oldpwdEditText.getText().toString();
				s2 = newpwdEditText.getText().toString();
				s3 = confirmpwdeditEditText.getText().toString();
				if (s1.equals(""))
					Toast.makeText(PasswordActivity.this, "原密码不能为空",
							Toast.LENGTH_LONG).show();
				else if (s2.equals(""))
					Toast.makeText(PasswordActivity.this, "新密码不能为空",
							Toast.LENGTH_LONG).show();
				else if (s3.equals(""))
					Toast.makeText(PasswordActivity.this, "确认新密码不能为空",
							Toast.LENGTH_LONG).show();
				else {
					final BmobQuery<User> query = new BmobQuery<User>();
					query.addWhereEqualTo("UserName", UserName);
					query.findObjects(mContext, new FindListener<User>() {

						@Override
						public void onSuccess(List<User> arg0) {
							// TODO Auto-generated method stub
							for (User arg : arg0) {
								if (s1.equals(arg.getUserPwd())) {
									if (!s2.equals(s3)) {
										Toast.makeText(mContext, "两次输入密码不一样",
												Toast.LENGTH_LONG).show();
										newpwdEditText.setText("");
										confirmpwdeditEditText.setText("");
									} else {
										if (s1.equals(s2)) {
											Toast.makeText(mContext,
													"新密码不能与原密码一致",
													Toast.LENGTH_LONG).show();
											newpwdEditText.setText("");
											confirmpwdeditEditText.setText("");
										} else {
											arg.setUserPwd(s2);
											arg.update(mContext,
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
																	"密码修改成功",
																	Toast.LENGTH_LONG)
																	.show();

															finish();
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
											break;
										}
									}

								} else {
									Toast.makeText(mContext, "密码错误！",
											Toast.LENGTH_LONG).show();
									oldpwdEditText.setText("");
								}

							}
						}

						@Override
						public void onError(int arg0, String arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_LONG)
									.show();
						}
					});
				}
			}
		});
	}
}
