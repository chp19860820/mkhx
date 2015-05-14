package com.mkhx.tool.base.compete;

import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;
import com.mkhx.tool.base.user.Competitor;

public class FreeFight {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public FreeFight(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	public void freeFight() {
		ToolLog.printInfo("开始自由切磋。。。。。");
		Competitor competitor = this.getCompetitor();
		if (competitor == null) {
			ToolLog.printInfo("找不到可以自由切磋的玩家。。。");
			return;
		}
		FreeFightCompetitors fight = new FreeFightCompetitors(
				loginDestServer.getCookie(), loginMainResponse.GS_IP);
		fight.setCompetitor(competitor);
		FreeFightCompetitorsResponse res = fight.request();
		String msg = "";
		if (res.status.equals("1")) {
			msg += "战斗对象：" + competitor.NickName;
			msg += " 战斗对象名次：" + competitor.Rank;
			msg += ".战斗结果:" + ((res.Win).equals("1") ? "win" : "Lost") + "。";
		}else{
			msg += res.msg;
		}
		ToolLog.printInfo(msg);
	}

	private Competitor getCompetitor() {
		try {
			Competitor competitorAll = null;
			for (int i = 1; i <= 20; i++) {
				GetCompetitors getCompetitors = new GetCompetitors(
						loginDestServer.getCookie(), loginMainResponse.GS_IP);
				GetCompetitorsResponse res = getCompetitors.request();
				Competitor competitor = res.suitableCompetitor;
				if (competitorAll == null && competitor != null) {
					competitorAll = competitor;
				}
				// 前七次，获取参照对象
				if (i <= 7) {
					if (competitor != null
							&& Integer.parseInt(competitor.Rank) > Integer
									.parseInt(competitorAll.Rank)) {
						competitorAll = competitor;
					}
				} else {
					// 最后一个，则不论如何，都取了
					if (i == 20) {
						competitorAll = competitor;
					}
					if (competitor != null
							&& Integer.parseInt(competitor.Rank) > Integer
									.parseInt(competitorAll.Rank)) {
						competitorAll = competitor;
						break;
					}
				}
			}
			return competitorAll;
		} catch (Exception e) {
			return null;
		}
	}

}
