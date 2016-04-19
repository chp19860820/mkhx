package com.mkhx.tool.base.login;

public class LoginDestServer extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.loginDestServerPath;
	protected String requestURL = super.defaultDestHost + super.loginDestServerPath;
	protected String requestBody = "";
	protected String cookie = null;
	protected String responseText = null;

	public LoginDestServer(String cookie) {
		this.cookie = cookie;
	}

	public LoginDestServer(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public LoginDestServerResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if (this.responseText != null && !this.responseText.equals("")) {
			return new LoginDestServerResponse(this.responseText);
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

	public void setRequestBody(String password, String username,
			String key, String time) {
		this.requestBody = "Udid=2%3A0%3A0%3A62%3A73%3A74&Password=" + password + "&UserName="
				+ username + "&key=" + key + "&time=" + time
				+ "&Devicetoken=&Origin=android";
	}
}
