package com.rj13.uchoose;

import java.io.ObjectOutputStream.PutField;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.rj13.model.User;
import com.rj13.robot.ChatActivity;

public class MainLayoutActivity extends Activity {
	public String id;
	public String date;

	public boolean check(String s1, String s2) {
		String year1 = s1.substring(0, 4);
		String year2 = s2.substring(0, 4);
		String month1 = s1.substring(4, 6);
		String month2 = s2.substring(4, 6);
		String day1 = s1.substring(6, 8);
		String day2 = s2.substring(6, 8);
		if (!year1.equals(year2)) {
			return true;
		} else {
			if (!month1.equals(month2)) {
				return true;
			} else {
				if (!day1.equals(day2)) {
					return true;
				} else
					return false;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);
		Bmob.initialize(this, "c033030d2a55e24b04e42447a1763e6e");
		Intent intent = getIntent();
		id = intent.getStringExtra("id");

		RelativeLayout r_game = (RelativeLayout)findViewById(R.id.game);
		r_game.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "这个功能正在开发中...", Toast.LENGTH_LONG).show();
			}
		});
		
		RelativeLayout r_history = (RelativeLayout)findViewById(R.id.history);
		r_history.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "这个功能正在开发中...", Toast.LENGTH_LONG).show();
			}
		});
		RelativeLayout r_drawing = (RelativeLayout)findViewById(R.id.drawing);
		r_drawing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "这个功能正在开发中...", Toast.LENGTH_LONG).show();
			}
		});
		
		RelativeLayout r_password = (RelativeLayout) findViewById(R.id.password);
		r_password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainLayoutActivity.this,
						PasswordActivity.class);
				intent.putExtra("id", id);
				startActivity(intent);
			}
		});

		RelativeLayout r_qiandao = (RelativeLayout) findViewById(R.id.qiandao);
		r_qiandao.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				date = sdf.format(new java.util.Date());

				// Toast.makeText(getApplicationContext(),date,
				// Toast.LENGTH_LONG).show();
				BmobQuery<User> query = new BmobQuery<User>();
				query.addWhereEqualTo("UserName", id);

				query.findObjects(MainLayoutActivity.this,
						new FindListener<User>() {

							@Override
							public void onSuccess(List<User> arg0) {
								// TODO Auto-generated method stub
								for (User arg : arg0) {

									if (check(date, arg.getQianDao()))// 如果上一次签到和现在的时间超过一天了
									{
										User u = new User();
										int num = Integer.parseInt(arg
												.getScore());
										num += 2;
										u.setScore(String.valueOf(num));
										u.setQianDao(date);
										u.update(MainLayoutActivity.this,
												arg.getObjectId(),
												new UpdateListener() {

													@Override
													public void onSuccess() {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																getApplicationContext(),
																"签到成功！积分加2",
																Toast.LENGTH_LONG)
																.show();
													}

													@Override
													public void onFailure(
															int arg0,
															String arg1) {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																getApplicationContext(),
																"签到失败！",
																Toast.LENGTH_LONG)
																.show();
													}
												});
										break;
									} else {
										Toast.makeText(getApplicationContext(),
												"你今天已经签过到啦~明天再来吧！",
												Toast.LENGTH_LONG).show();
									}
								}
							}

							@Override
							public void onError(int arg0, String arg1) {
								// TODO Auto-generated method stub

							}
						});

			}
		});

		Button imageButton1 = (Button) findViewById(R.id.Button_easy);
		imageButton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(MainLayoutActivity.this,
						EasyActivity.class);
				startActivity(intent1);
				// finish();

			}
		});
		Button imageButton3 = (Button) findViewById(R.id.Button_ballot);
		imageButton3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent2 = new Intent(MainLayoutActivity.this,
						BallotActivity.class);
				intent2.putExtra("id", id);
				startActivity(intent2);

			}
		});
		Button imageButton2 = (Button) findViewById(R.id.Button_ai);
		imageButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent3 = new Intent(MainLayoutActivity.this,
						ChatActivity.class);
				startActivity(intent3);

			}
		});
		ImageButton imageButton4 = (ImageButton) findViewById(R.id.imageButton_introduce);
		imageButton4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = getIntent();
				String id = intent.getStringExtra("id");
				Intent intent4 = new Intent(MainLayoutActivity.this,
						UserActivity.class);
				intent4.putExtra("id", id);
				startActivity(intent4);
			}
		});
		Button Button4 = (Button) findViewById(R.id.Button_expert);
		Button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent5 = new Intent(MainLayoutActivity.this,
						ExpertActivity.class);
				startActivity(intent5);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
