package com.mkhx.tool.base.user;

public class SendFriendEnergy extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.sendFriendEnergyPath;
	protected String requestURL = super.defaultDestHost + super.sendFriendEnergyPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public SendFriendEnergy(String cookie) {
		this.cookie = cookie;
	}

	public SendFriendEnergy(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public SendFriendEnergyResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new SendFriendEnergyResponse(this.responseText);
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
	public void setFriendId(String id){
		this.requestBody = "Fid=" + id;
	}
}
