package com.rj13.uchoose;

import java.util.List;

import com.rj13.model.User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends Activity {
	private Button b_back;
	private TextView t_id;
	private TextView t_nickname;
	private TextView t_email;
	private TextView t_phone;
	private TextView t_sex;
	private TextView t_score;
	private Context mContext = this;
	private String UserName;
	private BmobQuery<User> query;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_user);
		t_id = (TextView)findViewById(R.id.TextView_id);
		t_nickname = (TextView)findViewById(R.id.textView_nickname);
		t_email=(TextView)findViewById(R.id.textView_email);
		t_phone=(TextView)findViewById(R.id.textView_phone);
		t_sex=(TextView)findViewById(R.id.textView_sex);
		t_score=(TextView)findViewById(R.id.textView_score);
		
		Intent intent = getIntent();
		t_id.setText(intent.getStringExtra("id"));
	
		
		
		b_back = (Button)findViewById(R.id.button_user_back);
		b_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				finish();
			}
		});
		
		UserName=t_id.getText().toString();		
		query = new BmobQuery<User>();
		query.addWhereEqualTo("UserName", UserName);
		query.count(mContext, User.class, new CountListener() {

			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "数据获取失败", Toast.LENGTH_LONG).show();
			}

			public void onSuccess(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 > 0) {
					query.addWhereEqualTo("UserName", UserName);
					// query.addWhereEqualTo(key, value);
					query.findObjects(mContext,
							new FindListener<User>() {
								public void onError(int arg0, String arg1) {
									// TODO Auto-generated method stub
									Toast.makeText(mContext,"数据获取失败", Toast.LENGTH_LONG)
											.show();
								}

								@Override
								public void onSuccess(List<User> arg0) {
									for (User arg : arg0) {
										t_nickname.setText(arg.getNickName());
										t_email.setText(arg.getMailAddress());
										t_phone.setText(arg.getPhone());
										t_sex.setText(arg.getSex());
										t_score.setText(arg.getScore());
										break;
									}
								}
							});
				}
			}
		});
		
		RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout10);
		relativeLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(UserActivity.this,UserInfoActivity.class);
				intent.putExtra("id", t_id.getText().toString());
				startActivity(intent);
				finish();
			}
		});
	}

}
