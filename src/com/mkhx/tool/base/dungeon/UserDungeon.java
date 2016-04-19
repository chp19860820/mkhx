package com.mkhx.tool.base.dungeon;

import org.json.simple.JSONObject;

public class UserDungeon{
	public String Uid = null;
	public String CurrentLayer = null;
	public String MaxFinishLayer = null;
	public String Resurrection = null;
	public String BuyTimes = null;
	public String Anger = null;
	public String Status = null;
	public String FinishedBoss = null;
	public String RaidsStatus = null;
	public String RaidsLayer = null;
	public UserDungeon(JSONObject jUserDungeon){
		this.Uid = jUserDungeon.get("Uid").toString();
		this.CurrentLayer = jUserDungeon.get("CurrentLayer").toString();
		this.MaxFinishLayer = jUserDungeon.get("MaxFinishLayer").toString();
		this.Resurrection = jUserDungeon.get("Resurrection").toString();
		this.BuyTimes = jUserDungeon.get("BuyTimes").toString();
		this.Anger = jUserDungeon.get("Anger").toString();
//		this.Status = jUserDungeon.get("Status").toString();
		this.FinishedBoss = jUserDungeon.get("FinishedBoss").toString();
		this.RaidsStatus = jUserDungeon.get("RaidsStatus").toString();
		this.RaidsLayer = jUserDungeon.get("RaidsLayer").toString();
	}
}