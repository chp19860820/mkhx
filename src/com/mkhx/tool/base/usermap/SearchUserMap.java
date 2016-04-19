package com.mkhx.tool.base.usermap;

import java.util.Vector;

public class SearchUserMap extends com.mkhx.tool.base.ToolBase {
	protected String requestHost = super.defaultDestHost;
	protected String requestPath = super.searchMapPath;
	protected String requestURL = super.defaultDestHost + super.searchMapPath;
	protected String requestBody = null;
	protected String cookie = null;
	protected String responseText = null;

	public SearchUserMap(String cookie) {
		this.cookie = cookie;
	}

	public SearchUserMap(String cookie, String requestHost) {
		this.cookie = cookie;
		this.requestHost = requestHost;
		this.requestURL = this.requestHost + this.requestPath;
	}

	public void  request() {
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
	public void setMapState(String state){
		this.requestBody = "MapStageDetailId=" + state;
	}
	public void setMapState(Vector<MapStage> data){
		MapStage mapStage = data.get( data.size() - 1 );
		setMapState(mapStage.MapStageDetailId);
	}
}
