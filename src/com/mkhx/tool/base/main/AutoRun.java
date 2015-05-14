package com.mkhx.tool.base.main;

import java.util.HashMap;
import java.util.TimerTask;

import com.mkhx.tool.base.ToolCommonFunction;
import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.buycard.BuyCards;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;
import com.mkhx.tool.base.medite.Medition;
import com.mkhx.tool.base.user.UserInfoResponse;

public class AutoRun extends TimerTask {
	public static LoginMainServer loginMainServer = null;
	public static LoginMainServerResponse loginMainResponse = null;
	public static LoginDestServer loginDestServer = null;
	public static UserInfoResponse userResponse = null;
	public static HashMap<String, String> map = null;

	public static void runProgram() {
		try {
			map = ToolCommonFunction.getInitMap();
			// 普通一键刷塔、清入侵、刷贼、搜索用户及用户密码
			String[] userList = null;
			String[] pwdList = null;
			if (map.get("account") != null && map.get("password") != null) {
				userList = map.get("account").split(";");
				pwdList = map.get("password").split(";");
			}
			// 清除入侵
			// 特殊一键购卡、一键冥想用户列表
			String[] userListSpe = null;
			String[] pwdListSpe = null;
			if (map.get("accountspe") != null && map.get("passwordspe") != null) {
				userListSpe = map.get("accountspe").split(";");
				pwdListSpe = map.get("passwordspe").split(";");
			}
			// 购卡接口
			String buycard = map.get("buycard");
			String shop = map.get("shop");
			String cardno = map.get("cardno");
			// 冥想接口
			String medite = map.get("medite");
			String thread = map.get("thread");
			try {
				if (userList == null || userList.length == 0 || pwdList == null
						|| userList.length != pwdList.length) {
					ToolLog.printInfo("用户名及密码长度不匹配或用户列表为空!请检查配置文件");
					throw new Exception();
				}
				if (thread != null && thread.equals("1")) {
					ToolLog.printInfo("开启多线程模式");
				} else {
					ToolLog.printInfo("开启单线程模式");
				}
				for (int userIndex = 0; userIndex < userList.length; userIndex++) {
					UserAuto userAuto = new UserAuto(userList[userIndex],
							pwdList[userIndex]);
					if (thread != null && thread.equals("1")) {
						Thread t = new Thread(userAuto);
						t.start();
					} else {
						userAuto.run();
					}
				}
			} catch (Exception e) {
			}
			try {
				if (userListSpe == null || userListSpe.length == 0
						|| pwdListSpe == null
						|| userListSpe.length != pwdListSpe.length) {
					ToolLog.printInfo("特殊用户名及密码长度不匹配或用户列表为空!将跳过一键冥想及一键购卡功能");
					throw new Exception();
				}
				for (int userIndexSpe = 0; userIndexSpe < userListSpe.length; userIndexSpe++) {
					ToolLog.printInfo("****************************开始特殊处理用户："
							+ userListSpe[userIndexSpe] + "******************");
					loginMainServer = new LoginMainServer(null);
					loginMainServer.setRequestBody(userListSpe[userIndexSpe],
							pwdListSpe[userIndexSpe]);

					loginMainResponse = loginMainServer.request();
					// 登陆目标服务器
					loginDestServer = new LoginDestServer(
							loginMainServer.getCookie(),
							loginMainResponse.GS_IP);
					loginDestServer.setRequestBody(loginMainResponse.U_ID,
							loginMainResponse.userName, loginMainResponse.key,
							loginMainResponse.timestamp);
					loginDestServer.request();
					// 自动购卡
					if (buycard != null && buycard.equals("1")) {
						ToolLog.printInfo("开始一键购卡功能，购卡商店为：" + shop + "。购卡数量为："
								+ cardno);
						BuyCards buycards = new BuyCards(loginMainResponse,
								loginDestServer);
						buycards.buyCards(shop, cardno);
						ToolLog.printInfo("购卡结束。。。");
					}
					// 自动冥想
					if (medite != null && Integer.parseInt(medite) > 0) {
						ToolLog.printInfo("开始一键冥想功能，冥想次数为：" + medite);
						Medition medition = new Medition(loginMainResponse,
								loginDestServer);
						medition.medition(medite);
						ToolLog.printInfo("冥想结束！");
					}
					ToolLog.printInfo("****************************结束特殊处理用户："
							+ userListSpe[userIndexSpe] + "******************");
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
			return;
		}
	}

	public void run() {
		System.out.println("AutoRun");
		AutoRun.runProgram();
	}
}

