package com.mkhx.tool.base.usermap;

public class CleanUserMap extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.cleanMapPath;
	protected String requestURL = super.defaultDestHost + super.cleanMapPath;
	protected String requestBody = "MapStageDetailId=1&isManual=0";
	protected String cookie = null;
	protected String responseText = null;

	public CleanUserMap(String cookie) {
		this.cookie = cookie;
	}

	public CleanUserMap(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public void request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
//		if(this.responseText!=null && !this.responseText.equals("")){
//			return new UserMapResponse(this.responseText);
//		}else{
//			return null;
//		}
	}

	public String getCookie() {
		return this.cookie;
	}

	public String getResponseText() {
		return this.responseText;
	}
	public void setMapStageID(String id){
		this.requestBody = "MapStageDetailId=" + id + "&isManual=0";
	}
}
