package com.mkhx.tool.base.usermap;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class UserMapResponse {
	public String status = "";
	public int dataLength = 0;
	public Vector<MapStage> data = new Vector<MapStage>();
	private final int MAX_STATGE = 180;

	public UserMapResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				for (int i = 1; i <= MAX_STATGE; i++) {
					JSONObject jStage = (JSONObject) jData.get(i + "");
					if (jStage != null) {
						data.add(new MapStage(jStage,i+""));
					}
				}
			} else {
				ToolLog.printInfo("解析用户开图信息失败！");
			}
		} catch (Exception e) {
		}
	}
}
