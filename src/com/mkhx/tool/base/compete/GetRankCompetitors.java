package com.mkhx.tool.base.compete;

public class GetRankCompetitors extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.getRankCompetitorsPath;
	protected String requestURL = super.defaultDestHost + super.getRankCompetitorsPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public GetRankCompetitors(String cookie) {
		this.cookie = cookie;
	}

	public GetRankCompetitors(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public GetRankCompetitorsResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new GetRankCompetitorsResponse(this.responseText);
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
