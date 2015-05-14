package com.mkhx.tool.base.boss;

import java.io.IOException;

import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;

public class Boss {
	public static LoginMainServer loginMainServer = null;
	public static LoginMainServerResponse loginMainResponse = null;
	public static LoginDestServer loginDestServer = null;

	public static void main(String[] args) throws IOException {
		// 登陆主服务器
		loginMainServer = new LoginMainServer(null);
		loginMainServer.setRequestBody("ahbcd2", "wwwww");

		loginMainResponse = loginMainServer.request();
		// 登陆目标服务器
		loginDestServer = new LoginDestServer(loginMainServer.getCookie(),
				loginMainResponse.GS_IP);
		loginDestServer.setRequestBody(loginMainResponse.U_ID,
				loginMainResponse.userName, loginMainResponse.key,
				loginMainResponse.timestamp);
		loginDestServer.request();
		GetBoss get = new GetBoss(loginDestServer.getCookie(),
				loginMainResponse.GS_IP);
		get.request();
		ToolLog.printInfo(get.getResponseText());
		FightBoss fight = new FightBoss(loginDestServer.getCookie(),
				loginMainResponse.GS_IP);
		fight.request();
		ToolLog.printInfo(fight.getResponseText());
	}
}
