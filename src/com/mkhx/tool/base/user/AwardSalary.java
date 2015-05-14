package com.mkhx.tool.base.user;

public class AwardSalary extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.awardSalaryPath;
	protected String requestURL = super.defaultDestHost + super.awardSalaryPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public AwardSalary(String cookie) {
		this.cookie = cookie;
	}

	public AwardSalary(String cookie, String requestHost) {
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
}
