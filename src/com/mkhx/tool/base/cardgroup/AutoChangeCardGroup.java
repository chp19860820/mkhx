package com.mkhx.tool.base.cardgroup;

import java.util.Vector;

import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;
import com.mkhx.tool.base.user.UserInfoResponse;

public class AutoChangeCardGroup {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;
	private String defaultGroupId = null;
	private Vector<String> groupids = null;
	public AutoChangeCardGroup(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer,
			UserInfoResponse userResponse,
			CardGroupResponse cardGroupResponse) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
		this.defaultGroupId = userResponse.DefaultGroupId;
		this.groupids = cardGroupResponse.groupids;
	}
	public void changeCardGroup(int i ){
		String cardGroupId = groupids.get(i-1).toString();
		SetCardGroup setCardGroup = new SetCardGroup(
				loginDestServer.getCookie(),
				loginMainResponse.GS_IP);
		setCardGroup.setDefaultGroupId(cardGroupId);
		setCardGroup.request();
		ToolLog.printInfo("切换为第"+i+"卡组!");
	}
	public void returnGroup(){
		for(int i = 0; i < groupids.size(); i++ ){
			if(groupids.get(i).toString().equals(defaultGroupId)){
				changeCardGroup((i+1));
			}
		}
	}
	public String getDefaultGroupId(){
		return this.defaultGroupId;
	}
	public void changeCardGroup(String i) throws Exception{
		changeCardGroup(Integer.parseInt(i));
	}
}
