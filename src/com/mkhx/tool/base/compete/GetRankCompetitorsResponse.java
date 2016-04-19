package com.mkhx.tool.base.compete;

import java.util.Random;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mkhx.tool.base.user.Competitor;

public class GetRankCompetitorsResponse {
	public String status = "";
	public Vector<Competitor> competitors = new Vector<Competitor>();
	public Competitor suitableCompetitor = null;

	public GetRankCompetitorsResponse(String jsonString) {
		try {
			JSONParser jsonP = new JSONParser();
			// 解析JSON中
			JSONObject jsonObject = (JSONObject) jsonP.parse(jsonString);
			this.status = jsonObject.get("status").toString();
			if (this.status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				JSONArray jCompetitors = (JSONArray) jData.get("Competitors");
				for (int i = 0; i < jCompetitors.size(); i++) {
					JSONObject jCompetitor = (JSONObject) jCompetitors.get(i);
					Competitor competitor = new Competitor(jCompetitor);
					competitors.add(competitor);
				}
				this.suitableCompetitor = getSuitableCometitor();
			}
		} catch (Exception e) {
		}
	}

	private Competitor getSuitableCometitor(){
			int index = 0;
			Random r = new Random();
			index = r.nextInt(competitors.size());
			return competitors.get(index);
	}
}
