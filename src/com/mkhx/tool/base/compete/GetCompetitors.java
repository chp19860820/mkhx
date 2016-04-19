package com.mkhx.tool.base.compete;

public class GetCompetitors extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.getCompetitorsPath;
	protected String requestURL = super.defaultDestHost + super.getCompetitorsPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public GetCompetitors(String cookie) {
		this.cookie = cookie;
	}

	public GetCompetitors(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public GetCompetitorsResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new GetCompetitorsResponse(this.responseText);
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
