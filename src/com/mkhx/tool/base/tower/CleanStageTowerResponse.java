package com.mkhx.tool.base.tower;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolCommonFunction;

public class CleanStageTowerResponse {
	public String status = "";
	public JSONObject card = null;
	public long cardId = 0;
	public String cardName = "";
	public JSONObject extraCard = null;
	public long extraCardId = 0;
	public String extraCardName = "";
	public CleanStageTowerResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			// 解析状态码。0表示正常
			if (this.status.equals("1")) {
				JSONObject jData= (JSONObject) jsonObject.get("data");
				JSONObject jExtData = (JSONObject) jData.get("ExtData");
				JSONObject jAward= (JSONObject) jExtData.get("Award");
				this.cardId = (long)Integer.parseInt( jAward.get("CardId").toString());
				this.card = ToolCommonFunction.getCardById(cardId);
				this.cardName = ToolCommonFunction.getCardNameById(cardId);
				try{
					JSONObject jClear = (JSONObject) jExtData.get("Clear");
					this.extraCardId = (long)Integer.parseInt( jClear.get("CardId").toString());
					this.extraCard = ToolCommonFunction.getCardById(extraCardId);
					this.extraCardName = ToolCommonFunction.getCardNameById(extraCardId);
				}catch (Exception e) {
				}
			} else {
			}
		} catch (Exception e) {
		}
	}
}
