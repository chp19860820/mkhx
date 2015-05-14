package com.mkhx.tool.base.tower;

public class ResetTower extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.resetTowerPath;
	protected String requestURL = super.defaultDestHost + super.resetTowerPath;
	protected String requestBody = "MapStageId=8";
	protected String cookie = null;
	protected String responseText = null;

	public ResetTower(String cookie) {
		this.cookie = cookie;
	}

	public ResetTower(String cookie, String requestHost) {
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

	public void setTowerId(String ID) {
		this.requestBody = "MapStageId=" + ID;
	}

}
