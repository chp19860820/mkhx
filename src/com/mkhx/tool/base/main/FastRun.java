package com.mkhx.tool.base.main;

import java.util.HashMap;
import java.util.TimerTask;

import com.mkhx.tool.base.ToolCommonFunction;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;
import com.mkhx.tool.base.user.UserInfoResponse;

public class FastRun extends TimerTask {
	public static LoginMainServer loginMainServer = null;
	public static LoginMainServerResponse loginMainResponse = null;
	public static LoginDestServer loginDestServer = null;
	public static UserInfoResponse userResponse = null;
	public static HashMap<String, String> map = null;

	

	public static void runFast() {
		try {
			map = ToolCommonFunction.getInitMap();
			// 普通一键刷塔、清入侵、刷贼、搜索用户及用户密码
			String[] userList = null;
			String[] pwdList = null;
			if (map.get("account") != null && map.get("password") != null) {
				userList = map.get("account").split(";");
				pwdList = map.get("password").split(";");
			}
			String thread = map.get("thread");
			try {
				
				for (int userIndex = 0; userIndex < userList.length; userIndex++) {
					UserAuto userAuto = new UserAuto(userList[userIndex],
							pwdList[userIndex],true);
					if (thread != null && thread.equals("1")) {
						Thread t = new Thread(userAuto);
						t.start();
					} else {
						userAuto.run();
					}
					userAuto = null;
				}
			} catch (Exception e) {
			}
			
		} catch (Exception e) {
			return;
		}
	}
	
	public void run() {
		FastRun.runFast();
	}
}
