package com.rj13.uchoose;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.SaveListener;

import com.rj13.model.User;

public class RegisterActivity extends Activity {

	private EditText e_name;
	private EditText e_pwd;
	private EditText e_confirm;
	private Button b_register;
	private Button b_clear;
	private Button b_back;
	private Context mContext = this;
	private ImageButton imagerbutton;
	private String userName;
	private String userPwd;
	private String userConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bmob.initialize(this, "c033030d2a55e24b04e42447a1763e6e");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		e_name = (EditText) findViewById(R.id.edname1);
		e_pwd = (EditText) findViewById(R.id.EditText_pwd);
		e_confirm = (EditText) findViewById(R.id.EditText_confirm);
		b_register = (Button) findViewById(R.id.btregister1);
		b_clear = (Button) findViewById(R.id.Button_clear);
		b_back = (Button) findViewById(R.id.Button_back);

		b_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(RegisterActivity.this,
						LoginActivity.class);
				startActivity(intent1);
				finish();
			}
		});
		b_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				e_name.setText("");
				e_pwd.setText("");
				e_confirm.setText("");
			}
		});
		b_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Submit();
			}
		});
	}

	public void Submit() {
		userName = e_name.getText().toString();
		userPwd = e_pwd.getText().toString();
		userConfirm = e_confirm.getText().toString();
		if (userName.equals("")) {
			Toast.makeText(mContext, "用户名不能为空！", Toast.LENGTH_LONG).show();
			
		} else if (userPwd.equals("")) {
			Toast.makeText(mContext, "密码不能为空！", Toast.LENGTH_LONG).show();
			
		} else if (userConfirm.equals("")) {
			Toast.makeText(mContext, "请再次输入密码！", Toast.LENGTH_LONG).show();
			
		} else if (!userPwd.equals(userConfirm)) {
			Toast.makeText(mContext, "确认密码和密码不一致！", Toast.LENGTH_LONG).show();
			e_pwd.setText("");
			e_confirm.setText("");
			
		} else {
			BmobQuery<User> query = new BmobQuery<User>();
			query.addWhereEqualTo("UserName", userName);
			query.count(this, User.class, new CountListener() {
				@Override
				public void onSuccess(int count) {
					// TODO Auto-generated method stub
					if (count > 0) {
						Toast.makeText(mContext, "该用户名已经被注册，请更换用户名！",
								Toast.LENGTH_LONG).show();
						e_name.setText("");
						e_pwd.setText("");
						e_confirm.setText("");
					} else {
						final User user = new User();
						user.setUserName(userName);
						user.setUserPwd(userPwd);
						user.setScore("50");
						user.setQianDao("10000101");
						user.save(mContext, new SaveListener() {

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								Toast.makeText(mContext,
										"注册成功，恭喜你获得系统赠送的50积分，赶快登陆看看吧！",
										Toast.LENGTH_LONG).show();
								Intent intent2 = new Intent(
										RegisterActivity.this,
										LoginActivity.class);
								startActivity(intent2);
								finish();

							}

							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								Toast.makeText(mContext, "注册失败，请重新尝试",
										Toast.LENGTH_LONG).show();

							}
						});
					}

				}

				@Override
				public void onFailure(int code, String msg) {
					// TODO Auto-generated method stub
					Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_LONG).show();
				}
			});

			// 这个查询有毒
			// BmobQuery<User> query = new BmobQuery<User>();
			// query.addWhereEqualTo("UserName", userName);
			// query.findObjects(RegistersActivity.this, new
			// FindListener<BmobUser>() {
			// public void onSuccess(List<User> arg0) {
			// // TODO Auto-generated method stub
			// Toast.makeText(mContext, "exist", Toast.LENGTH_LONG)show();
			//
			// }
			//
			// @Override
			// public void onError(int arg0, String arg1) {
			// // TODO Auto-generated method stub
			// Toast.makeText(mContext, "error", Toast.LENGTH_LONG);
			// }
			// });
			// if (object.size() > 0) {
			// User user = new User();
			// user.setUserName(userName);
			// user.setUserPwd(userPwd);
			// user.save(mContext, new SaveListener() {
			//
			// @Override
			// public void onSuccess() {
			// // TODO Auto-generated method stub
			// Toast.makeText(mContext, "注册成功，将跳转到登录页面",
			// Toast.LENGTH_LONG).show();
			// Intent intent2 = new Intent(
			// RegistersActivity.this, login.class);
			// startActivity(intent2);
			// finish();
			//
			// }
			//
			// @Override
			// public void onFailure(int arg0, String arg1) {
			// // TODO Auto-generated method stub
			// Toast.makeText(mContext, "注册失败，请重新尝试",
			// Toast.LENGTH_LONG).show();
			//
			// }
			// });
			// } else {
			// Toast.makeText(mContext, "该用户名已经被注册，请更换用户名！",
			// Toast.LENGTH_LONG).show();
			// e_name.setText("");
			// e_pwd.setText("");
			// e_confirm.setText("");
			// }
		}

	}
}
