package com.mkhx.tool.base.dungeon;

public class GetUserDungeon extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.getUserDungeonPath;
	protected String requestURL = super.defaultDestHost + super.getUserDungeonPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public GetUserDungeon(String cookie) {
		this.cookie = cookie;
	}

	public GetUserDungeon(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public GetUserDungeonResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new GetUserDungeonResponse(this.responseText);
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
