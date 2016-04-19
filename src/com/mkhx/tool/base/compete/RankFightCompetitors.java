package com.mkhx.tool.base.compete;

import com.mkhx.tool.base.user.Competitor;

public class RankFightCompetitors extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.rankfightPath;
	protected String requestURL = super.defaultDestHost + super.rankfightPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public RankFightCompetitors(String cookie) {
		this.cookie = cookie;
	}

	public RankFightCompetitors(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public RankFightCompetitorsResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new RankFightCompetitorsResponse(this.responseText);
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
	public void setCompetitor(String rank){
		this.requestBody = "CompetitorRank=" + rank;
	}
	public void setCompetitor(Competitor competitor){
		this.requestBody = "CompetitorRank=" + competitor.Rank;
	}
}
