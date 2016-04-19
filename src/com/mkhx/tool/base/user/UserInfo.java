package com.mkhx.tool.base.user;

public class UserInfo extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.userInfoPath;
	protected String requestURL = super.defaultDestHost + super.userInfoPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public UserInfo(String cookie) {
		this.cookie = cookie;
	}

	public UserInfo(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public UserInfoResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new UserInfoResponse(this.responseText);
		}else{
			return null;
		}
	}

	public String getCookie() {
		return this.cookie;
	}

	public String getResponseText() {
		return this.responseText;
	}

}
