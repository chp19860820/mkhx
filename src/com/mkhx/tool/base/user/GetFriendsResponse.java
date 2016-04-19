package com.mkhx.tool.base.user;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class GetFriendsResponse {
	public String status = "";
	public Vector<Friend> friends = new Vector<Friend>();
	public GetFriendsResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				JSONArray jFriends = (JSONArray) jData.get("Friends");
				for(int i = 0; i < jFriends.size(); i++ ){
					JSONObject jFriend = (JSONObject)jFriends.get(i);
					Friend frind = new Friend(jFriend);
//					frind.printf();
					friends.add(frind);
				}
			} else {
				ToolLog.printInfo("解析用户好友信息失败！");
			}
		} catch (Exception e) {
		}
	}
}
class Friend{
	public String Uid = null;
	public String NickName = null;
	public String FEnergySurplus = null;
	public String FEnergySend = null;
	public Friend(JSONObject friend){
		this.Uid = friend.get("Uid").toString();
		this.NickName = friend.get("NickName").toString();
		this.FEnergySurplus = friend.get("FEnergySurplus").toString();
		this.FEnergySend = friend.get("FEnergySend").toString();
	}
	public void printf(){
		ToolLog.printInfo("Uid:" + this.Uid);
		ToolLog.printInfo("NickName:" + this.NickName);
		ToolLog.printInfo("FEnergySurplus:" + this.FEnergySurplus);
		ToolLog.printInfo("FEnergySurplus:" + this.FEnergySurplus);
	}
}
