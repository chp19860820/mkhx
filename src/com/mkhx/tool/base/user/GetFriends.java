package com.mkhx.tool.base.user;

public class GetFriends extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.getFriendsPath;
	protected String requestURL = super.defaultDestHost + super.getFriendsPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public GetFriends(String cookie) {
		this.cookie = cookie;
	}

	public GetFriends(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public GetFriendsResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new GetFriendsResponse(this.responseText);
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
