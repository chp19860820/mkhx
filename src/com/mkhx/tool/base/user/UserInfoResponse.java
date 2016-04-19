package com.mkhx.tool.base.user;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class UserInfoResponse {
	public String status = "";
	public String NickName = null;
	public String Energy = null;
	public String DefaultGroupId = null;
	public UserInfoResponse(String jsonString) {
		try {
			
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				this.NickName = jData.get("NickName").toString();
				this.Energy = jData.get("Energy").toString();
				this.DefaultGroupId = jData.get("DefaultGroupId").toString();
			} else {
				ToolLog.printInfo("解析用户信息失败！");
			}
		} catch (Exception e) {
		}
	}
}
