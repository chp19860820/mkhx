package com.mkhx.tool.base.tower;

public class TowerLayerInfo extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.towerLayerInfoPath;
	protected String requestURL = super.defaultDestHost
			+ super.towerLayerInfoPath;
	protected String requestBody = "MapStageId=8&Layer=1";
	protected String cookie = null;
	protected String responseText = null;

	public TowerLayerInfo(String cookie) {
		this.cookie = cookie;
	}

	public TowerLayerInfo(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public TowerLayerInfoResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText == null || this.responseText.equals("")){
			return null;
		}else{
			return new TowerLayerInfoResponse(responseText);
		}
	}

	public String getCookie() {
		return this.cookie;
	}

	public String getResponseText() {
		return this.responseText;
	}

	public void setTowerLayer(String towerId, String layer) {
		this.requestBody = "MapStageId=" + towerId + "&Layer=" + layer;
	}
}
