package com.mkhx.tool.base.medite;

import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;

public class Medition {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public Medition(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	public void medition(String times) {
		try {
			medition(Integer.parseInt(times));
		} catch (Exception e) {
		}
	}

	public void medition(int times) {
		long defautNPC = 1;
		for (int i = 0; i < times; i++) {
			if (i % 10 == 0) {
				DealMedite dealMedite = new DealMedite(
						loginDestServer.getCookie(), loginMainResponse.GS_IP);
				dealMedite.request();
				ToolLog.printDebugMsg("deal:" + dealMedite.getResponseText());
			}
			Medite medite = new Medite(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			medite.setNPC(defautNPC);
			MediteResponse mediteResponse = medite.request();
			ToolLog.printDebugMsg("medit:" + medite.getResponseText());
			defautNPC = mediteResponse.nextNPC;
		}
	}
}
