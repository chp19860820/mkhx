package com.mkhx.tool.base.user;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SendFriendEnergyResponse {
	public String message = null;
	public String status = null;
	public SendFriendEnergyResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (!this.status.equals("1")) {
				this.message = jsonObject.get("message").toString();
				System.out.println("message:" + this.message);
			}
		} catch (Exception e) {
		}
	}
}

