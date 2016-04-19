package com.mkhx.tool.base.legion;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class LegionInfoResponse {
	public String status = "";
	public UserLegionInfo uinfo = null;
	public Vector<MapLegionInfo> info = new Vector<MapLegionInfo>();
	public LegionInfoResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				JSONObject jUinfo = (JSONObject) jData.get("uinfo");
				this.uinfo = new UserLegionInfo(jUinfo);
				JSONArray jInfos = (JSONArray) jData.get("info");
				for( int i = 0; i < jInfos.size(); i++ ){
					MapLegionInfo mapInfo = new MapLegionInfo((JSONObject) jInfos.get(i));
					info.add(mapInfo);
				}
			} else {
				ToolLog.printInfo("解析用户军团信息失败！");
			}
		} catch (Exception e) {
		}
	}
}
