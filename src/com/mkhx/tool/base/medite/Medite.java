package com.mkhx.tool.base.medite;

public class Medite extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.meditePath;
	protected String requestURL = super.defaultDestHost + super.meditePath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public Medite(String cookie) {
		this.cookie = cookie;
	}

	public Medite(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public MediteResponse request() {
		String response[] = super.request(requestURL, cookie, requestBody);
		this.responseText = response[0];
		this.cookie = response[1];
		if(this.responseText!=null && !this.responseText.equals("")){
			return new MediteResponse(this.responseText);
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
	public void setNPC(String NPC){
		this.requestBody = "NpcId=" + NPC;
	}
	public void setNPC(long NPC){
		this.requestBody = "NpcId=" + NPC;
	}
}
