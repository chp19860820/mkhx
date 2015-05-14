package com.mkhx.tool.base.usermap;

import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;
import com.mkhx.tool.base.user.UserInfo;
import com.mkhx.tool.base.user.UserInfoResponse;

public class SearchMap {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public SearchMap(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	public void searchMap() {
		try {
			UserInfo userInfo = new UserInfo(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			UserInfoResponse userResponse = userInfo.request();
			int energy = Integer.parseInt(userResponse.Energy);
			
			UserMap userMap = new UserMap(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			UserMapResponse userMapResponse = userMap.request();
			for(int i = 0;i < energy/2; i++ ){
				ToolLog.printInfo("搜索中。。。。");
				SearchUserMap searchMap = new SearchUserMap(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
				searchMap.setMapState(userMapResponse.data);
				searchMap.request();
			}
			
		} catch (Exception e) {
		}
	}
}
