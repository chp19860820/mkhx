package com.mkhx.tool.base.medite;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class MediteResponse {
	public String status = "";
	public long nextNPC = 1;
	public MediteResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				JSONArray jArr = (JSONArray) jData.get("NpcList");
				for( int i = 0; i < jArr.size(); i++ ){
					long tmpNpc =(long)Integer.parseInt( jArr.get(i).toString());
					if(tmpNpc > this.nextNPC){
						this.nextNPC = tmpNpc;
					}
				}
			} else {
				ToolLog.printInfo("解析用户信息失败！");
			}
		} catch (Exception e) {
		}
	}
}
