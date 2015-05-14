package com.mkhx.tool.base.cardgroup;

public class SetCardGroup extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.setCardGroupPath;
	protected String requestURL = super.defaultDestHost + super.setCardGroupPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public SetCardGroup(String cookie) {
		this.cookie = cookie;
	}

	public SetCardGroup(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public void request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
	}

	public String getCookie() {
		return this.cookie;
	}

	public String getResponseText() {
		return this.responseText;
	}
	public void setDefaultGroupId(String GroupId){
		this.requestBody = "GroupId=" + GroupId;
	}
}
