package com.rj13.robot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.rj13.robot.bean.ChatMessage;
import com.rj13.robot.bean.ChatMessage.Type;
import com.rj13.robot.until.HttpUtils;
import com.rj13.uchoose.R;

public class ChatActivity extends Activity {

	private ListView mChatView;

	private EditText mMsg;

	private List<ChatMessage> mDatas = new ArrayList<ChatMessage>();

	private ChatMessageAdapter mAdapter;
	
	private String message;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ChatMessage from = (ChatMessage) msg.obj;
			mDatas.add(from);
			mAdapter.notifyDataSetChanged();
			mChatView.setSelection(mDatas.size() - 1);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_chatting);

		initView();

		mAdapter = new ChatMessageAdapter(this, mDatas);
		mChatView.setAdapter(mAdapter);

	}

	private void initView() {
		mChatView = (ListView) findViewById(R.id.id_chat_listView);
		mMsg = (EditText) findViewById(R.id.id_chat_msg);
		mDatas.add(new ChatMessage(Type.INPUT, "HI,我是小U~"));
	}

	public void sendMessage(View view) {
		message = mMsg.getText().toString();
		if (TextUtils.isEmpty(message)) {
			Toast.makeText(this, "不能发送空消息哦", Toast.LENGTH_SHORT).show();
			return;
		}
		
//		if (message.indexOf("杭州") < 0)
//			message += "杭州";

		ChatMessage to = new ChatMessage(Type.OUTPUT, message);
		to.setDate(new Date());
		mDatas.add(to);

		mAdapter.notifyDataSetChanged();
		mChatView.setSelection(mDatas.size() - 1);

		mMsg.setText("");

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}

		new Thread() {
			public void run() {
				ChatMessage from = null;
				try {
					from = HttpUtils.sendMsg(message);
				} catch (Exception e) {
					from = new ChatMessage(Type.INPUT, "我不知道啊>_<");
				}
				Message message = Message.obtain();
				message.obj = from;
				mHandler.sendMessage(message);
			};
		}.start();

	}

}