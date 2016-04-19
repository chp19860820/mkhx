package com.mkhx.tool.base.thieves;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class GetThievesResponse {
	public String status = "";
	public Vector<Thief> vThieves = new Vector<Thief>();
	public GetThievesResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				JSONArray jThieves = (JSONArray) jData.get("Thieves");
				for(int i = 0; i < jThieves.size(); i++ ){
					JSONObject jThief = (JSONObject)jThieves.get(i);
					Thief thief = new Thief();
					thief.Uid = jThief.get("Uid").toString();
					thief.NickName =jThief.get("NickName").toString();
					thief.Avatar =jThief.get("Avatar").toString();
					thief.ThievesId =jThief.get("ThievesId").toString();
					thief.Time =jThief.get("Time").toString();
					thief.Status =jThief.get("Status").toString();
					thief.HPCount =jThief.get("HPCount").toString();
					thief.HPCurrent =jThief.get("HPCount").toString();
					thief.Type =jThief.get("Type").toString();
					thief.FleeTime =jThief.get("FleeTime").toString();
					thief.UserThievesId = jThief.get("UserThievesId").toString();
					if(!vThieves.contains(thief)){
						vThieves.add(thief);
					}
				}
			} else {
				ToolLog.printInfo("解析用户盗贼信息失败！");
			}
		} catch (Exception e) {
		}
	}
	public Thief getSuitableThief(){
		Thief retThief = null;
		int suitable = 0;
		try{
			for(int i = 0; i < this.vThieves.size(); i++ ){
				Thief tmpThief = this.vThieves.get(i);
				//贼的等级
				if(tmpThief.getThiefProfile() > suitable ){
					retThief = tmpThief;
					suitable = tmpThief.getThiefProfile();
				}
			}
			return retThief;
		}catch(Exception e){
			return null;
		}
	}
}
