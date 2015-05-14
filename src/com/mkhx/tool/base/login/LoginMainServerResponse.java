package com.mkhx.tool.base.login;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.ToolLog;

public class LoginMainServerResponse {
	public String returnCode = "";
	public String returnMsg = "";
	public String GS_IP = "";
	public String GS_PORT = "";
	public String timestamp = "";
	public String userName = "";
	public String U_ID = "";
	public String G_TYPE = "";
	public String key = "";
	public String GS_CHAT_PORT = "";

	public LoginMainServerResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.returnCode = jsonObject.get("returnCode").toString();
			this.returnMsg   = jsonObject.get("returnMsg").toString();
			//解析状态码。0表示正常
			if (this.returnCode.equals("0")) {
				JSONObject jobj = (JSONObject) jsonObject.get("returnObjs");
				this.GS_IP = jobj.get("GS_IP").toString();
				this.GS_PORT = jobj.get("GS_PORT").toString();
				this.timestamp = jobj.get("timestamp").toString();
				this.userName = jobj.get("userName").toString();
				this.U_ID = jobj.get("U_ID").toString();
				this.G_TYPE = jobj.get("G_TYPE").toString();
				this.key = jobj.get("key").toString();
				this.GS_CHAT_PORT = jobj.get("GS_CHAT_PORT").toString();
//				ToolLog.printInfo("登陆成功,帐号：" + this.userName);
			} else {
//				ToolLog.printInfo("登陆失败！请重新登陆");
			}
		} catch (Exception e) {
		}
	}
}
