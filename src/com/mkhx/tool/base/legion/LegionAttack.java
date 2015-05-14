package com.mkhx.tool.base.legion;

import java.text.SimpleDateFormat;
import java.util.Vector;

import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;

public class LegionAttack implements Runnable {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public LegionAttack(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	@Override
	public void run() {
		LegionInfo legionInfo = new LegionInfo(loginDestServer.getCookie(),
				loginMainResponse.GS_IP);
		LegionInfoResponse res = legionInfo.request();
		Vector<MapLegionInfo> v = res.info;
		UserLegionInfo uInfo = res.uinfo;
		String mapId = null;
		String time = null;
		boolean isJoin = false;
		for (int i = 0; i < v.size(); i++) {
			MapLegionInfo mapInfo = v.get(i);
			time = mapInfo.getTime();
			AttackLegion attLegion = mapInfo.getAttackLegion();
			if (attLegion.getLegionId().equals(uInfo.getLegionId())) {
				mapId = mapInfo.getId();
				isJoin = mapInfo.isIsJoin();
				break;
			}
			DefendLegion defLegion = mapInfo.getDefendLegion();
			if (defLegion.getLegionId().equals(uInfo.getLegionId())) {
				mapId = mapInfo.getId();
				isJoin = mapInfo.isIsJoin();
				break;
			}
		}
		int timeType = checkTimeType(time);
//		ToolLog.printInfo("timeType:" + timeType);
		if (mapId != null && timeType == 0 && !isJoin) {
			JoinLegion joinLegion = new JoinLegion(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			joinLegion.setMapId(mapId);
			joinLegion.request();
			ToolLog.printInfo("参加结果:" + joinLegion.getResponseText());
		}
	}

	public static int checkTimeType(String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			format.parse(time);
			return 1;
		} catch (Exception e) {
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			format.parse(time);
			return 2;
		} catch (Exception e) {
		}
		try {
			Long.parseLong(time);
			return 0;
		} catch (Exception e) {
		}
		return -1;
	}
}
