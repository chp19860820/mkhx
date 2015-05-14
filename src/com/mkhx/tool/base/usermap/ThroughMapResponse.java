package com.mkhx.tool.base.usermap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ThroughMapResponse {
	public String status = "";
	public int dataLength = 0;
	public String message = null;
	public ThroughMapResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
//				JSONObject jData = (JSONObject) jsonObject.get("data");
			}else{
				this.message = jsonObject.get("message").toString();
			}
		} catch (Exception e) {
		}
	}
}
