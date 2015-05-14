package com.mkhx.tool.base.login;

public class LoginMainServer extends com.mkhx.tool.base.ToolBase {
	public final String requestURL = super.loginMainServerURL;
	protected String requestBody = "{"
			+ "\"serviceName\" : \"checkUserActivedJson\","
			+ "\"callPara\" : {" + "\"userName\" : \"\","
			+ "\"userPassword\" : \"\","
			+ "\"gameName\" : \"CARD-IPAD-CHS\","
			+ "\"udid\" : \"2:0:0:62:73:74\"," + "\"clientType\" : \"flash\","
			+ "\"releaseChannel\" : \"\"," + "\"locale\" : \"chs\"" + "}" + "}";

	protected String cookie = null;
	protected String responseText = null;

	public LoginMainServer(String cookie) {
		this.cookie = cookie;
	}

	public LoginMainServerResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if (this.responseText != null && !this.responseText.equals("")) {
			return new LoginMainServerResponse(this.responseText);
		} else {
			return null;
		}
	}

	public String getCookie() {
		return this.cookie;
	}

	public String getResponseText() {
		return this.responseText;
	}

	public void setRequestBody(String username, String userpassword) {
		this.requestBody = "{" + "\"serviceName\" : \"checkUserActivedJson\","
				+ "\"callPara\" : {" + "\"userName\" : \"" + username + "\","
				+ "\"userPassword\" : \"" + userpassword + "\","
				+ "\"gameName\" : \"CARD-IPAD-CHS\","
				+ "\"udid\" : \"2:0:0:62:73:74\","
				+ "\"clientType\" : \"flash\"," + "\"releaseChannel\" : \"\","
				+ "\"locale\" : \"chs\"" + "}" + "}";
	}
	public LoginMainServerResponse getResponse(){
		return new LoginMainServerResponse(this.responseText);
	}
}
