package com.rj13.uchoose;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.rj13.model.Ballot;
import com.rj13.model.Ballot_User;

public class BallotActivity extends Activity {
	/** Called when the activity is first created. */
	public List<Ballot> mData;
	public ListView listView;
	public BallotAdapter adapter;
	public Context mContext = this;
	// Handler myHandler;
	public String id;
	public int exist;// 判断当前用户是不是投过票

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ballot_layout);
		Bmob.initialize(this, "c033030d2a55e24b04e42447a1763e6e");
		Intent intent = getIntent();
		id = intent.getStringExtra("id");

//		BigInteger b = BigInteger.valueOf(1);
//		for (int i = 0; i < 10000; i++) {
//			b.multiply(BigInteger.valueOf(i));
//		}
		mData = new ArrayList<Ballot>();
		getData();
		adapter = new BallotAdapter(this, mData);
		listView = (ListView) findViewById(R.id.listView_ballot);
		listView.setAdapter(adapter);

		Button b_load = (Button) findViewById(R.id.button_loadballot);
		b_load.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// mData = new ArrayList<Ballot>();
				getData();
				listView = (ListView) findViewById(R.id.listView_ballot);
				// adapter = new BallotAdapter(mContext, mData);
				listView.setAdapter(adapter);
			}
		});
		Button b_add = (Button) findViewById(R.id.button_submitballot);
		b_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent2 = new Intent(BallotActivity.this,
						AddBallotActivity.class);
				intent2.putExtra("id", id);
				startActivity(intent2);
				finish();

			}
		});

	}

	public List<Ballot> getData() {
		// mData = new ArrayList<Ballot>();
		// Ballot ba = new Ballot();
		// ba.setTitle("123");
		// mData.add(ba);
		mData = new ArrayList<Ballot>();
		BmobQuery<Ballot> query = new BmobQuery<Ballot>();
		query.addWhereNotEqualTo("title", "");
		query.setLimit(100);
		query.findObjects(BallotActivity.this, new FindListener<Ballot>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(), "服务器连接失败",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(List<Ballot> arg0) {
				for (Ballot arg : arg0) {
					// Toast.makeText(getApplicationContext(), arg.getTitle(),
					// Toast.LENGTH_LONG).show();
					Ballot ba = new Ballot();
					ba.setTitle(arg.getTitle());
					ba.setUserid(arg.getUserid());
					ba.setYes(arg.getYes());
					ba.setNo(arg.getNo());
					ba.setDetail(arg.getDetail());
					mData.add(ba);
				}
			}
		});

		return mData;
	}

	class BallotAdapter extends BaseAdapter {
		private List<Ballot> mData;
		private LayoutInflater mInflater;

		public BallotAdapter(Context context, List<Ballot> mData) {
			this.mInflater = LayoutInflater.from(context);
			this.mData = mData;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mData.get(position);
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub

			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();

				Ballot ba = mData.get(position);
				convertView = mInflater.inflate(R.layout.ballot_item, null);

				holder.t_title = (TextView) convertView
						.findViewById(R.id.textView_label);
				holder.t_user = (TextView) convertView
						.findViewById(R.id.textView_user);
				holder.t_yes = (TextView) convertView
						.findViewById(R.id.textView_yes);
				holder.t_no = (TextView) convertView
						.findViewById(R.id.TextView_no);
				holder.b_detail = (Button) convertView
						.findViewById(R.id.button_detail);
				// holder.t_detail = (TextView) convertView
				// .findViewById(R.id.textView_detail);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.t_title.setText("标题：  " + mData.get(position).getTitle());
			holder.t_user.setText("发起人：  " + mData.get(position).getUserid());
			holder.t_yes.setText("支持数：  " + mData.get(position).getYes());
			holder.t_no.setText("反对数：  " + mData.get(position).getNo());
			// holder.t_detail.setText(mData.get(position).getDetail());
			holder.b_detail.setTag(position);

			holder.b_detail.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					showInfo(position);
				}
			});

			return convertView;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

	}

	// public void getBallotList()
	// {
	// getData();
	// listView = (ListView) findViewById(R.id.listView_ballot);
	// adapter = new BallotAdapter(this, mData);
	// listView.setAdapter(adapter);
	// }

	class ViewHolder {
		public TextView t_title;
		public TextView t_user;
		public TextView t_yes;
		public TextView t_no;
		public Button b_detail;
	}

	public void showInfo(final int position) {

		// ImageView img = new ImageView(BallotActivity.this);
		// img.setImageResource(R.drawable.bg);
		exist = 0;
		BmobQuery<Ballot_User> query_exist = new BmobQuery<Ballot_User>();
		query_exist.addWhereEqualTo("BallotDetail", mData.get(position)
				.getDetail());
		query_exist.findObjects(BallotActivity.this,
				new FindListener<Ballot_User>() {

					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "服务器错误",
								Toast.LENGTH_LONG).show();
					}

					@Override
					public void onSuccess(List<Ballot_User> arg0) {
						// TODO Auto-generated method stub
						for (Ballot_User arg : arg0) {
							if (arg.getP_User().equals(id)
									&& arg.getUser().equals(
											mData.get(position).getUserid())) {
								exist = 1;
								break;
							}
						}
					}

				});

		Dialog dialog;
		Builder b = new AlertDialog.Builder(this);
		b.setTitle("问题详情");
		b.setMessage(mData.get(position).getDetail());

		b.setPositiveButton("反对", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				if (exist == 0) {

					Ballot_User bu = new Ballot_User();
					bu.setBallotDetail(mData.get(position).getDetail());
					bu.setUser(mData.get(position).getUserid());
					bu.setP_User(id);
					bu.setBallotInfo("false");
					bu.save(mContext, new SaveListener() {

						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub

						}
					});

					// TODO Auto-generated method stub
					final BmobQuery<Ballot> query = new BmobQuery<Ballot>();
					query.addWhereEqualTo("detail", mData.get(position)
							.getDetail());
					query.findObjects(BallotActivity.this,
							new FindListener<Ballot>() {

								@Override
								public void onSuccess(List<Ballot> arg0) {
									// TODO Auto-generated method stub
									for (Ballot arg : arg0) {

										Ballot ba = new Ballot();
										int num = Integer.parseInt(mData.get(
												position).getNo());
										num++;
										ba.setNo(String.valueOf(num));
										ba.update(BallotActivity.this,
												arg.getObjectId(),
												new UpdateListener() {

													@Override
													public void onSuccess() {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																getApplicationContext(),
																"投票成功！",
																Toast.LENGTH_SHORT)
																.show();
														// Thread th = new
														// Thread(MyThread);
														// th.start();

													}

													@Override
													public void onFailure(
															int arg0,
															String arg1) {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																getApplicationContext(),
																"投票失败！",
																Toast.LENGTH_SHORT)
																.show();
													}
												});
										break;
									}
								}

								@Override
								public void onError(int arg0, String arg1) {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"错误！", Toast.LENGTH_SHORT).show();
								}
							});
				}

				else {
					Toast.makeText(getApplicationContext(), "你已经投过票了，感谢参与！",
							Toast.LENGTH_LONG).show();
				}

			}
		});// postive

		b.setNegativeButton("支持", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				if (exist == 0) {

					Ballot_User bu = new Ballot_User();
					bu.setBallotDetail(mData.get(position).getDetail());
					bu.setUser(mData.get(position).getUserid());
					bu.setP_User(id);
					bu.setBallotInfo("true");
					bu.save(mContext, new SaveListener() {

						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub

						}

						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub

						}
					});
					final BmobQuery<Ballot> query = new BmobQuery<Ballot>();
					query.addWhereEqualTo("detail", mData.get(position)
							.getDetail());
					query.findObjects(BallotActivity.this,
							new FindListener<Ballot>() {

								@Override
								public void onSuccess(List<Ballot> arg0) {
									// TODO Auto-generated method stub
									for (Ballot arg : arg0) {

										Ballot ba = new Ballot();
										int num = Integer.parseInt(mData.get(
												position).getYes());
										num++;
										ba.setYes(String.valueOf(num));
										ba.update(BallotActivity.this,
												arg.getObjectId(),
												new UpdateListener() {

													@Override
													public void onSuccess() {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																getApplicationContext(),
																"投票成功！",
																Toast.LENGTH_SHORT)
																.show();
														// Thread th = new
														// Thread(MyThread);
														// th.start();
													}

													@Override
													public void onFailure(
															int arg0,
															String arg1) {
														// TODO Auto-generated
														// method stub
														Toast.makeText(
																getApplicationContext(),
																"投票失败！",
																Toast.LENGTH_SHORT)
																.show();
													}
												});
										break;
									}
								}

								@Override
								public void onError(int arg0, String arg1) {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"错误！", Toast.LENGTH_SHORT).show();
								}
							});

				}

				else {
					Toast.makeText(getApplicationContext(), "你已经投过票了，感谢参与！",
							Toast.LENGTH_LONG).show();
				}
			}

		});
		dialog = b.create();
		dialog = b.show();

	}
}
