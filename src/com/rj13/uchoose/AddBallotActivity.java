package com.rj13.uchoose;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.SaveListener;

import com.rj13.model.Ballot;
import com.rj13.model.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBallotActivity extends Activity {

	private String title;
	private String detail;
	private String userid;
	private EditText e_title;
	private EditText e_detail;
	private Button b_add;
	private Button b_clear;
	private Context mContext = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_addballot);
		e_title = (EditText) findViewById(R.id.editText_btitle);
		e_detail = (EditText) findViewById(R.id.editText_bdetail);

		Intent intent = getIntent();
		String id = intent.getStringExtra("id");
		userid = id;
		b_clear = (Button) findViewById(R.id.Button_ballotclear);
		b_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				e_detail.setText("");
				e_title.setText("");
			}
		});
		b_add = (Button) findViewById(R.id.button_addballot);
		b_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				title = e_title.getText().toString();
				detail = e_detail.getText().toString();
				if (title.equals("") || detail.equals("")) {
					Toast.makeText(mContext, "标题和内容都不能为空哦！", Toast.LENGTH_LONG)
							.show();
				} else if (title.length() > 10) {
					Toast.makeText(mContext, "标题太长啦！", Toast.LENGTH_LONG)
							.show();
				}

				else {

					// Toast.makeText(getApplicationContext(),
					// title + " " + detail + " " + userid, Toast.LENGTH_LONG)
					// .show();
					BmobQuery<Ballot> query = new BmobQuery<Ballot>();
					query.addWhereEqualTo("detail", detail);
					query.count(mContext, Ballot.class, new CountListener() {

						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(mContext, "服务器错误", Toast.LENGTH_LONG)
									.show();
						}

						@Override
						public void onSuccess(int arg0) {
							// TODO Auto-generated method stub
							if (arg0 > 0) {
								Toast.makeText(mContext, "请再详细说说你的问题吧",
										Toast.LENGTH_LONG).show();
							} else {
								Ballot ballot = new Ballot();
								ballot.setTitle(title);
								ballot.setDetail(detail);
								ballot.setUserid(userid);
								ballot.setYes("0");
								ballot.setNo("0");
								ballot.save(mContext, new SaveListener() {

									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
										Toast.makeText(mContext, "发布成功",
												Toast.LENGTH_LONG).show();
										Intent intent = new Intent(
												AddBallotActivity.this,
												BallotActivity.class);
										intent.putExtra("id", userid);
										startActivity(intent);
										finish();
									}

									@Override
									public void onFailure(int arg0, String arg1) {
										// TODO Auto-generated method stub
										Toast.makeText(mContext, "发布失败",
												Toast.LENGTH_LONG).show();
									}
								});

							}
						}
					});

				}
			}
		});
	}
}
