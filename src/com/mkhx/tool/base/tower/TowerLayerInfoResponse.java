package com.mkhx.tool.base.tower;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TowerLayerInfoResponse {
	public final long EMPTY = 1;
	public final long BOX = 2;
	public final long MONSTER = 3;
	public final long BEFORESTEP = 4;
	public final long NEXTSTEP = 5;
	public final long CLEAN = 6;
	public String status = "";
	public long ItemIndex[] = new long[32];
	public int RemainBoxNum = -1;
	public TowerLayerInfoResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jobj = (JSONObject) jsonObject.get("data");
				this.RemainBoxNum = Integer.parseInt(jobj.get("RemainBoxNum").toString());
				jobj = (JSONObject) jobj.get("Map");
				JSONArray jsonArr = (JSONArray) jobj.get("Items");
				for(int i = 0; i < 32; i ++ ){
					ItemIndex[i] = (long)Integer.parseInt(jsonArr.get(i).toString());
				}
			} else {
			}
		} catch (Exception e) {
		}
	}
}
