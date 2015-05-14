package com.mkhx.tool.base.cardgroup;

public class CardGroup extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.getCardGroupPath;
	protected String requestURL = super.defaultDestHost + super.getCardGroupPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public CardGroup(String cookie) {
		this.cookie = cookie;
	}

	public CardGroup(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public CardGroupResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new CardGroupResponse(this.responseText);
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
}
