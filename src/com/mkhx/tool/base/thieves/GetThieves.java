package com.mkhx.tool.base.thieves;

public class GetThieves extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.getThievesPath;
	protected String requestURL = super.defaultDestHost + super.getThievesPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public GetThieves(String cookie) {
		this.cookie = cookie;
	}

	public GetThieves(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public GetThievesResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new GetThievesResponse(this.responseText);
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
