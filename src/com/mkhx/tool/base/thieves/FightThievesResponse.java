package com.mkhx.tool.base.thieves;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FightThievesResponse {
	public String status = "";
	public Vector<Thief> vThieves = new Vector<Thief>();
	public FightThievesResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
		} catch (Exception e) {
		}
	}
}
