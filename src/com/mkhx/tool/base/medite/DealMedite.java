package com.mkhx.tool.base.medite;

public class DealMedite extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.dealMeditePath;
	protected String requestURL = super.defaultDestHost + super.dealMeditePath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public DealMedite(String cookie) {
		this.cookie = cookie;
	}

	public DealMedite(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public  void request() {
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
