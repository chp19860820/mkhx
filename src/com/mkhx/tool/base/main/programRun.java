package com.mkhx.tool.base.main;

import java.util.HashMap;
import java.util.Timer;

import com.mkhx.tool.base.ToolCommonFunction;
import com.mkhx.tool.base.ToolLog;

public class programRun {

	public static void main(String[] args) {

		try {
			HashMap<String, String> map = ToolCommonFunction.getInitMap();
			String mainsleep = map.get("mainsleep");
			int sleep = 30;
			try {
				if (mainsleep != null && !mainsleep.equals("")) {
					sleep = Integer.parseInt(mainsleep);
					ToolLog.printInfo("程序自动休眠" + sleep + "分钟后重新启动。。。");
				}
			} catch (Exception e) {
			}
			Timer timer = new Timer();
			timer.schedule(new AutoRun(), 0, sleep * 60 * 1000);
			ToolLog.printInfo("自动参加军团功能已经开放！");
			Timer timer2 = new Timer();
			timer2.schedule(new FastRun(), 0, 5 * 60 * 1000);
		} catch (Exception e) {
			return;
		}
	}
}