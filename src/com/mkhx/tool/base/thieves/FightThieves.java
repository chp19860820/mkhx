package com.mkhx.tool.base.thieves;

public class FightThieves extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.fightThievesPath;
	protected String requestURL = super.defaultDestHost + super.fightThievesPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public FightThieves(String cookie) {
		this.cookie = cookie;
	}

	public FightThieves(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public FightThievesResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new FightThievesResponse(this.responseText);
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
	public void setThiefId(String id){
		this.requestBody = "UserThievesId=" + id;
	}
}
