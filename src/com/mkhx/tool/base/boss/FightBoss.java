package com.mkhx.tool.base.boss;

public class FightBoss extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.fightBossPath;
	protected String requestURL = super.defaultDestHost + super.fightBossPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public FightBoss(String cookie) {
		this.cookie = cookie;
	}

	public FightBoss(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public void request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
	}

	public String getCookie() {
		return this.cookie;
	}

	public String getResponseText() {
		return this.responseText;
	}
}
