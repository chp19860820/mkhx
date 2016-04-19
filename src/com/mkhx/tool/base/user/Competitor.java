package com.mkhx.tool.base.user;

import org.json.simple.JSONObject;

public class Competitor{
	public String Uid = null;
	public String NickName = null;
	public String Rank = null;
	public String Chip = null;
	public Competitor(JSONObject jCompetor){
		this.Uid = jCompetor.get("Uid").toString();
		this.NickName = jCompetor.get("NickName").toString();
		this.Rank = jCompetor.get("Rank").toString();
		//竞技场则没有这个参数，无视之
		try{
			this.Chip = jCompetor.get("Chip").toString();
		}catch (Exception e) {
		}
	}
}
