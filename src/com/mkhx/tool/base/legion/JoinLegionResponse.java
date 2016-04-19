package com.mkhx.tool.base.legion;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class JoinLegionResponse {
	public String status = "";
	public JoinLegionResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				//JSONObject jData = (JSONObject) jsonObject.get("data");
			} else {
				ToolLog.printInfo("参加用户军团信息失败！");
			}
		} catch (Exception e) {
		}
	}
}
