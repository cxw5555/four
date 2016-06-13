package com.rj13.robot;



import com.rj13.robot.until.HttpUtils;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase
{
	public void testSendMsg()
	{
		HttpUtils.sendMsg("��б��·�³���");
		HttpUtils.sendMsg("���");
		HttpUtils.sendMsg("����Ц��");
		HttpUtils.sendMsg("��������");
	}

}
