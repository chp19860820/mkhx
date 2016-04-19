package com.mkhx.tool.base.cardgroup;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;


public class CardGroupResponse {
	public String status = "";
	public Vector<String> groupids = new Vector<String>();
	public CardGroupResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				JSONArray jGroups = (JSONArray) jData.get("Groups");
				for(int i = 0; i < jGroups.size(); i++ ){
					JSONObject jGroup = (JSONObject) jGroups.get(i);
					String groupid = jGroup.get("GroupId").toString();
					if(!groupids.contains(groupid)){
						groupids.add(groupid);
					}
				}
			} else {
				ToolLog.printInfo("解析用户信息失败！");
			}
		} catch (Exception e) {
		}
	}
}
