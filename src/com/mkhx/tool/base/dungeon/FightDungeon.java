package com.mkhx.tool.base.dungeon;

public class FightDungeon extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.fightDungeonPath;
	protected String requestURL = super.defaultDestHost + super.fightDungeonPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public FightDungeon(String cookie) {
		this.cookie = cookie;
	}

	public FightDungeon(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public FightDungeonResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new FightDungeonResponse(this.responseText);
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
	public void setLayer(String layer){
		this.requestBody = "Layer=" + layer + "&isManual=0";
	}
}
