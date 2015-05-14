package com.mkhx.tool.base.usermap;

public class UserMap extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.userMapPath;
	protected String requestURL = super.defaultDestHost + super.userMapPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public UserMap(String cookie) {
		this.cookie = cookie;
	}

	public UserMap(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public UserMapResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new UserMapResponse(this.responseText);
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
