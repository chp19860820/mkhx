package com.mkhx.tool.base.boss;

public class GetBoss extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.getBossPath;
	protected String requestURL = super.defaultDestHost + super.getBossPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public GetBoss(String cookie) {
		this.cookie = cookie;
	}

	public GetBoss(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public GetBossResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new GetBossResponse(this.responseText);
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
