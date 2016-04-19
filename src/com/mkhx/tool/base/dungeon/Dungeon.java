package com.mkhx.tool.base.dungeon;

import com.mkhx.tool.base.ToolCommonFunction;
import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;

public class Dungeon {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;
	private int keepTimes = 2;

	public Dungeon(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	public void doDungeon(String keepTimes) {
		try {
			doDungeon(Integer.parseInt(keepTimes));
		} catch (Exception e) {
			doDungeon(this.keepTimes);
		}
	}

	public void doDungeon() {
		doDungeon(ToolCommonFunction.getInitMap().get("dungeon"));
	}

	public void doDungeon(int keepTimes) {
		this.keepTimes = keepTimes;
		ToolLog.printInfo("开始扫荡。。");
		SweepDungeon sweepDungeon = new SweepDungeon(
				loginDestServer.getCookie(), loginMainResponse.GS_IP);
		sweepDungeon.request();
		ToolLog.printInfo("扫荡完成。。");
		for (int i = 1; i <= 100; i++) {
			int CurrentLayer = 1;
			int Resurrection = 2;
			GetUserDungeon getUserDungeon = new GetUserDungeon(
					loginDestServer.getCookie(), loginMainResponse.GS_IP);
			GetUserDungeonResponse getUserDungeonResponse = getUserDungeon
					.request();
			try {
				CurrentLayer = Integer
						.parseInt(getUserDungeonResponse.userDungeon.CurrentLayer);
				Resurrection = Integer
						.parseInt(getUserDungeonResponse.userDungeon.Resurrection);
			} catch (Exception e) {
				break;
			}
			if (Resurrection < this.keepTimes) {
				ToolLog.printInfo("剩余复活次数为：" + Resurrection + "。保留次数给玩家自己玩。");
				ToolLog.printInfo("目前层数为：" + CurrentLayer);
				break;
			}
			FightDungeon fightDungeon = new FightDungeon(
					loginDestServer.getCookie(), loginMainResponse.GS_IP);
			fightDungeon.setLayer((CurrentLayer + 1) + "");
			FightDungeonResponse fightDungeonResponse = fightDungeon.request();
			//if is not the right time to fight dungeon ,then end
			if(fightDungeonResponse.status.equals("0")){
				break;
			}
		}
	}
}
