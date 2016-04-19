package com.mkhx.tool.base.tower;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class TowerResponse {
	public String status = "";
	public String Name = "";
	public String Layer = "";
	public String Clear = "";
	public String FreeReset = "";

	public TowerResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jobj = (JSONObject) jsonObject.get("data");
				this.Name = jobj.get("Name").toString();
				this.Layer = jobj.get("Layer").toString();
				this.Clear = jobj.get("Clear").toString();
				this.FreeReset = jobj.get("FreeReset").toString();
				ToolLog.printInfo("成功获取Tower的信息:" + this.Name);
			} else {
			}
		} catch (Exception e) {
		}
	}
}
