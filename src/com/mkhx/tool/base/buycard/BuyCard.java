package com.mkhx.tool.base.buycard;

public class BuyCard extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.buyCardPath;
	protected String requestURL = super.defaultDestHost + super.buyCardPath;
	protected String requestBody = "GoodsId=1";
	protected String cookie = null;
	protected String responseText = null;

	public BuyCard(String cookie) {
		this.cookie = cookie;
	}

	public BuyCard(String cookie, String requestHost) {
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
	public void setRequestBody(String body){
		this.requestBody = body;
	}
	/*
	 * @function set shop type
	 * @param id:1 equals goldShop
	 */
	public void setShopId(String id){
		this.requestBody = "GoodsId=" + id;
	}
}
