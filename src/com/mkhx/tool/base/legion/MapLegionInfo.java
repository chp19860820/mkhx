package com.mkhx.tool.base.legion;

import org.json.simple.JSONObject;

public class MapLegionInfo {
	private String Id;
	private String Name;
	private String LegionId;
	private String LegionName;
	private String Emblem;
	private String EmblemLevel;
	private String Coins;
	private AttackLegion AttackLegion;
	private DefendLegion DefendLegion;
	private boolean IsJoin;
	private String UserLegionId;
	private String Status;
	private String Time;
	public MapLegionInfo(JSONObject jMapLegionInfo){
		setId(jMapLegionInfo.get("Id").toString());
		setName(jMapLegionInfo.get("Name").toString());
		setLegionId(jMapLegionInfo.get("LegionId").toString());
		setLegionName(jMapLegionInfo.get("LegionName").toString());
		try{
			setEmblem(jMapLegionInfo.get("Emblem").toString());
		}catch (Exception e) {
		}
		try{
			setEmblemLevel(jMapLegionInfo.get("EmblemLevel").toString());
		}catch (Exception e) {
		}
		try{
			setCoins(jMapLegionInfo.get("Coins").toString());
		}catch (Exception e) {
		}
		setAttackLegion(new AttackLegion((JSONObject) jMapLegionInfo.get("AttackLegion")));
		setDefendLegion(new DefendLegion((JSONObject) jMapLegionInfo.get("DefendLegion")));
		setIsJoin(Boolean.parseBoolean(jMapLegionInfo.get("IsJoin").toString()));
		setUserLegionId(jMapLegionInfo.get("UserLegionId").toString());
		setStatus(jMapLegionInfo.get("Status").toString());
		setTime(jMapLegionInfo.get("Time").toString());
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getLegionId() {
		return LegionId;
	}
	public void setLegionId(String legionId) {
		LegionId = legionId;
	}
	public String getEmblem() {
		return Emblem;
	}
	public void setEmblem(String emblem) {
		Emblem = emblem;
	}
	public String getLegionName() {
		return LegionName;
	}
	public void setLegionName(String legionName) {
		LegionName = legionName;
	}
	public String getEmblemLevel() {
		return EmblemLevel;
	}
	public void setEmblemLevel(String emblemLevel) {
		EmblemLevel = emblemLevel;
	}
	public String getCoins() {
		return Coins;
	}
	public void setCoins(String coins) {
		Coins = coins;
	}
	public AttackLegion getAttackLegion() {
		return AttackLegion;
	}
	public void setAttackLegion(AttackLegion attackLegion) {
		AttackLegion = attackLegion;
	}
	public DefendLegion getDefendLegion() {
		return DefendLegion;
	}
	public void setDefendLegion(DefendLegion defendLegion) {
		DefendLegion = defendLegion;
	}
	public boolean isIsJoin() {
		return IsJoin;
	}
	public void setIsJoin(boolean isJoin) {
		IsJoin = isJoin;
	}
	public String getUserLegionId() {
		return UserLegionId;
	}
	public void setUserLegionId(String userLegionId) {
		UserLegionId = userLegionId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
}
