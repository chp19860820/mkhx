package com.mkhx.tool.base.legion;

public class JoinLegion extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.joinLegionPath;
	protected String requestURL = super.defaultDestHost + super.joinLegionPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public JoinLegion(String cookie) {
		this.cookie = cookie;
	}

	public JoinLegion(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public JoinLegionResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new JoinLegionResponse(this.responseText);
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
	public void setMapId(String Id){
		this.requestBody = "Type=1&Id=" + Id;
	}
}
