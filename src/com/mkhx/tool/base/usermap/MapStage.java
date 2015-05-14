package com.mkhx.tool.base.usermap;

import org.json.simple.JSONObject;

public class MapStage {
	public String Index = null;
	public String Uid = null;
	public String MapStageDetailId = null;
	public String Type = null;
	public String MapStageId = null;
	public String FinishedStage = null;
	public String LastFinishedTime = null;
	public String CounterAttackTime = null;
	MapStage(JSONObject jStage,String Index){
		this.Index = Index;
		this.Uid = jStage.get("Uid").toString();
		this.MapStageDetailId = jStage.get("MapStageDetailId").toString();
		this.Type = jStage.get("Type").toString();
		this.MapStageId = jStage.get("MapStageId").toString();
		this.FinishedStage = jStage.get("FinishedStage").toString();
		this.LastFinishedTime = jStage.get("LastFinishedTime").toString();
		this.CounterAttackTime = jStage.get("CounterAttackTime").toString();
	}
}
