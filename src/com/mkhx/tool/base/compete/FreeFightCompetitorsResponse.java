package com.mkhx.tool.base.compete;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FreeFightCompetitorsResponse {
	public String status = "";
	public String Win = null;
//	public String ExtData = null;
	public String Chip = "";
	public String msg = "";
	public FreeFightCompetitorsResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				this.Win = jData.get("Win").toString();
			}else{
				
				this.msg = jsonObject.get("message").toString();
			}
		} catch (Exception e) {
		}
	}
}
