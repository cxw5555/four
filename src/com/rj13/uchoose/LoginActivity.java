package com.rj13.uchoose;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import com.rj13.model.User;

public class LoginActivity extends Activity {
	private EditText edname;
	private EditText edpassword;
	private Context mContext = this;
	public static int flag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bmob.initialize(this, "c033030d2a55e24b04e42447a1763e6e");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		flag = 0;
		edname = (EditText) findViewById(R.id.login_name);
		edpassword = (EditText) findViewById(R.id.login_password);
		Button loginbButton = (Button) findViewById(R.id.button_login);
		loginbButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (flag == 0) {
					login();
				}
			}
		});

		Button registeredButton = (Button) findViewById(R.id.button_clearinfo);
		registeredButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(intent2);

			}
		});
	}

	public void login() {
		final String UserName = edname.getText().toString();
		final String UserPwd = edpassword.getText().toString();
		if (UserName.equals("")) {
			Toast.makeText(LoginActivity.this, "用户名不能为空!", Toast.LENGTH_LONG)
					.show();
			return;
		} else if (UserPwd.equals("")) {
			Toast.makeText(LoginActivity.this, "密码不能为空!", Toast.LENGTH_LONG)
					.show();
			return;
		} else {
			flag = 1;
			final BmobQuery<User> query = new BmobQuery<User>();
			query.addWhereEqualTo("UserName", UserName);
			query.count(mContext, User.class, new CountListener() {

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					flag = 0;
					Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_LONG).show();
				}

				public void onSuccess(int arg0) {
					// TODO Auto-generated method stub
					if (arg0 > 0) {
						query.addWhereEqualTo("UserName", UserName);
						// query.addWhereEqualTo(key, value);
						query.findObjects(LoginActivity.this,
								new FindListener<User>() {
									public void onError(int arg0, String arg1) {
										// TODO Auto-generated method stub
										Toast.makeText(LoginActivity.this,
												"查询错误", Toast.LENGTH_LONG)
												.show();
										flag = 0;
									}

									@Override
									public void onSuccess(List<User> arg0) {
										// TODO Auto-generated method stub

										for (User arg : arg0) {
											if (UserPwd.equals(arg.getUserPwd())) {

												SharedPreferences sharedPreferences = getSharedPreferences(
														"user",
														Context.MODE_PRIVATE);
												SharedPreferences.Editor editor = sharedPreferences
														.edit();
												editor.putString("user_id",
														UserName);
												// �ҽ��û���Ϣ���浽���У���Ҳ���Ա����¼״̬
												editor.commit();

												Intent intent1 = new Intent(
														LoginActivity.this,
														MainLayoutActivity.class);
												intent1.putExtra("id", UserName);
												startActivity(intent1);
												finish();
												break;
											} else {
												flag = 0;
												Toast.makeText(
														LoginActivity.this,
														"密码错误！",
														Toast.LENGTH_LONG)
														.show();
												edpassword.setText("");
											}

										}
									}
								});
					}

					else {
						flag = 0;
						Toast.makeText(mContext, "该用户未注册", Toast.LENGTH_LONG)
								.show();
					}

				}
			});
		}
	}

}
