package com.mkhx.tool.base.usermap;

import java.util.Vector;

import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;

public class CleanMap {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public CleanMap(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	public void cleanMap() {
		try {
			// 获取用户地图信息
			UserMap userMap = new UserMap(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			UserMapResponse userResponse = userMap.request();
			// 清除入侵
			Vector<MapStage> stages = userResponse.data;
			for (int i = 0; i < stages.size(); i++) {
				MapStage mapStage = stages.get(i);
				if (!mapStage.CounterAttackTime.equals("0")) {
					CleanUserMap cleanUserMap = new CleanUserMap(
							loginDestServer.getCookie(),
							loginMainResponse.GS_IP);
					cleanUserMap.setMapStageID(mapStage.Index);
					cleanUserMap.request();
//					ToolLog.printInfo("cleanUserMap result:"
//							+ cleanUserMap.getResponseText());
				}
			}
			
		} catch (Exception e) {
		}
	}
}
