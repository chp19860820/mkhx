package com.mkhx.tool.base.legion;

import org.json.simple.JSONObject;

public class UserLegionInfo {
	private String LegionId;
	private boolean IsBaoming;
	private String Duty;
	private String MapId;
	public UserLegionInfo(JSONObject jUinfo){
		setLegionId(jUinfo.get("LegionId").toString());
		setIsBaoming(Boolean.parseBoolean(jUinfo.get("IsBaoming").toString()));
		setDuty(jUinfo.get("Duty").toString());
		setMapId(jUinfo.get("MapId").toString());
	}
	public String getLegionId() {
		return LegionId;
	}

	public void setLegionId(String legionId) {
		LegionId = legionId;
	}

	public boolean isIsBaoming() {
		return IsBaoming;
	}

	public void setIsBaoming(boolean isBaoming) {
		IsBaoming = isBaoming;
	}

	public String getDuty() {
		return Duty;
	}

	public void setDuty(String duty) {
		Duty = duty;
	}

	public String getMapId() {
		return MapId;
	}

	public void setMapId(String mapId) {
		MapId = mapId;
	}
	
}
