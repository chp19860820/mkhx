package com.mkhx.tool.base.compete;

import com.mkhx.tool.base.user.Competitor;

public class FreeFightCompetitors extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.freefightCompetitorsPath;
	protected String requestURL = super.defaultDestHost + super.freefightCompetitorsPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public FreeFightCompetitors(String cookie) {
		this.cookie = cookie;
	}

	public FreeFightCompetitors(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public FreeFightCompetitorsResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new FreeFightCompetitorsResponse(this.responseText);
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
	public void setCompetitor(String uid){
		this.requestBody = "isManual=0&Competitor=" + uid;
	}
	public void setCompetitor(Competitor competitor){
		this.requestBody = "isManual=0&Competitor=" + competitor.Uid;
	}
}
