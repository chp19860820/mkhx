package com.mkhx.tool.base.dungeon;

public class SweepDungeon extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.sweepDungeonPath;
	protected String requestURL = super.defaultDestHost + super.sweepDungeonPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public SweepDungeon(String cookie) {
		this.cookie = cookie;
	}

	public SweepDungeon(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public GetUserDungeonResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
//			return new GetUserDungeonResponse(this.responseText);
			return null;
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
