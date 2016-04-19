package com.mkhx.tool.base.tower;

public class CleanStageTower extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.cleanStagePath;
	protected String requestURL = super.defaultDestHost + super.cleanStagePath;
	protected String requestBody = "MapStageId=8&Layer=1&manual=1&ItemIndex=11";
	protected String cookie = null;
	protected String responseText = null;

	public CleanStageTower(String cookie) {
		this.cookie = cookie;
	}

	public CleanStageTower(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public CleanStageTowerResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText != null && !this.responseText.equals("")){
			return new CleanStageTowerResponse(this.responseText);
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

	public void setStageLayerItem(String towerId, String layer, String itemIndex) {
		this.requestBody = "manual=0&MapStageId=" + towerId + "&Layer=" + layer
				+ "&ItemIndex=" + itemIndex;
	}

}
