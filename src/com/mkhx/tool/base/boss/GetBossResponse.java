package com.mkhx.tool.base.boss;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class GetBossResponse {
	public String status = "";
	public GetBossResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
//				JSONObject jData = (JSONObject) jsonObject.get("data");
			} else {
				ToolLog.printInfo("解析用户地下城信息失败！");
			}
		} catch (Exception e) {
		}
	}
	
}
