package com.mkhx.tool.base.usermap;

public class ThroughMap extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.throughMapPath;
	protected String requestURL = super.defaultDestHost + super.throughMapPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public ThroughMap(String cookie) {
		this.cookie = cookie;
	}

	public ThroughMap(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public ThroughMapResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new ThroughMapResponse(this.responseText);
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
	public void setMapStage(String id){
		this.requestBody = "isManual=0&MapStageDetailId=" + id;
	}
}
