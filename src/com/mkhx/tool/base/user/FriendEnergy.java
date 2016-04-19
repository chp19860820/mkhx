package com.mkhx.tool.base.user;

import java.util.Vector;

import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;

public class FriendEnergy {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public FriendEnergy(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	public void getAndsendEnergy() {
		GetFriends getFriends = new GetFriends(loginDestServer.getCookie(),
				loginMainResponse.GS_IP);
		GetFriendsResponse getFriendsResponse = getFriends.request();
		Vector<Friend> vFriends = getFriendsResponse.friends;
		//送体力
		for(int i = 0; i < vFriends.size(); i++ ){
			Friend friend = vFriends.get(i);
			if(friend.FEnergySend.equals("1")){
				SendFriendEnergy sendFE = new SendFriendEnergy(loginDestServer.getCookie(),
						loginMainResponse.GS_IP);
				sendFE.setFriendId(friend.Uid);
				SendFriendEnergyResponse res = sendFE.request();
				if(res.message != null && res.message.equals("今日赠送达到上限！")){
					break;
				}
			}
		}
		//取体力
		for(int i = 0; i < vFriends.size(); i++ ){
			Friend friend = vFriends.get(i);
			if(friend.FEnergySurplus.equals("1")){
				GetFriendEnergy getFE = new GetFriendEnergy(loginDestServer.getCookie(),
						loginMainResponse.GS_IP);
				getFE.setFriendId(friend.Uid);
				getFE.request();
			}
		}
		//获取工资
		AwardSalary award = new AwardSalary(loginDestServer.getCookie(),
						loginMainResponse.GS_IP);
		award.request();
	}
}
