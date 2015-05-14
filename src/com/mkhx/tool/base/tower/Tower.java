package com.mkhx.tool.base.tower;

public class Tower extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.towerPath;
	protected String requestURL = super.defaultDestHost + super.towerPath;
	protected String requestBody = "MapStageId=8";
	protected String cookie = null;
	protected String responseText = null;

	public Tower(String cookie) {
		this.cookie = cookie;
	}

	public Tower(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public TowerResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText == null || this.responseText.equals("")){
			return null;
		}else{
			return new TowerResponse(this.responseText);
		}
	}

	public String getCookie() {
		return this.cookie;
	}

	public String getResponseText() {
		return this.responseText;
	}
	public void setMapStageId(String towerNum){
		this.requestBody = "MapStageId=" + towerNum;
	}
}
