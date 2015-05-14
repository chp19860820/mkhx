package com.mkhx.tool.base.legion;

import org.json.simple.JSONObject;

public class AttackLegion {
	private String LegionId;
	private String Name;
	private String Emblem;
	private String EmblemLevel;
	private String FightUserList;
	public AttackLegion(JSONObject jAttackLegion){
		setLegionId(jAttackLegion.get("LegionId").toString());
		setName(jAttackLegion.get("Name").toString());
		setEmblem(jAttackLegion.get("Emblem").toString());
		setEmblemLevel(jAttackLegion.get("EmblemLevel").toString());
//		JSONArray jFightUserList = (JSONArray) jAttackLegion.get("FightUserList");
	}
	public String getLegionId() {
		return LegionId;
	}

	public void setLegionId(String legionId) {
		LegionId = legionId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmblem() {
		return Emblem;
	}

	public void setEmblem(String emblem) {
		Emblem = emblem;
	}

	public String getEmblemLevel() {
		return EmblemLevel;
	}

	public void setEmblemLevel(String emblemLevel) {
		EmblemLevel = emblemLevel;
	}

	public String getFightUserList() {
		return FightUserList;
	}

	public void setFightUserList(String fightUserList) {
		FightUserList = fightUserList;
	}
}
