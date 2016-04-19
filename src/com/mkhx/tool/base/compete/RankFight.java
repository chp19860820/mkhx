package com.mkhx.tool.base.compete;

import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;
import com.mkhx.tool.base.user.Competitor;

public class RankFight {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public RankFight(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	public void rankFight() {
		ToolLog.printInfo("开始竞技场切磋。。。。。");
		Competitor competitor = this.getCompetitor();
		if (competitor == null) {
			ToolLog.printInfo("找不到可以竞技场切磋的玩家。。。");
			return;
		}
		RankFightCompetitors fight = new RankFightCompetitors(
				loginDestServer.getCookie(), loginMainResponse.GS_IP);
		fight.setCompetitor(competitor);
		RankFightCompetitorsResponse res = fight.request();
		String msg = "";
		if (res.status.equals("1")) {
			msg += "战斗对象：" + competitor.NickName;
			msg += " 战斗对象名次：" + competitor.Rank;
			msg += ".战斗结果:" + ((res.Win).equals("1") ? "win" : "Lost") + "。";
		} else {
			msg += res.msg;
		}
		ToolLog.printInfo(msg);
	}

	private Competitor getCompetitor() {
		try {
			GetRankCompetitors getRankCompetitors = new GetRankCompetitors(
					loginDestServer.getCookie(), loginMainResponse.GS_IP);
			GetRankCompetitorsResponse res = getRankCompetitors.request();
			return res.suitableCompetitor;
		} catch (Exception e) {
			return null;
		}
	}

}
