package com.mkhx.tool.base.main;

import java.io.IOException;
import java.util.HashMap;

import com.mkhx.tool.base.ToolCommonFunction;
import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.cardgroup.AutoChangeCardGroup;
import com.mkhx.tool.base.cardgroup.CardGroup;
import com.mkhx.tool.base.cardgroup.CardGroupResponse;
import com.mkhx.tool.base.compete.FreeFight;
import com.mkhx.tool.base.compete.RankFight;
import com.mkhx.tool.base.dungeon.Dungeon;
import com.mkhx.tool.base.legion.LegionAttack;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;
import com.mkhx.tool.base.thieves.FightThieves;
import com.mkhx.tool.base.thieves.GetThieves;
import com.mkhx.tool.base.thieves.GetThievesResponse;
import com.mkhx.tool.base.tower.CleanTower;
import com.mkhx.tool.base.user.FriendEnergy;
import com.mkhx.tool.base.user.UserInfo;
import com.mkhx.tool.base.user.UserInfoResponse;
import com.mkhx.tool.base.usermap.CleanMap;
import com.mkhx.tool.base.usermap.MapStage;
import com.mkhx.tool.base.usermap.SearchMap;
import com.mkhx.tool.base.usermap.ThroughMap;
import com.mkhx.tool.base.usermap.ThroughMapResponse;
import com.mkhx.tool.base.usermap.UserMap;
import com.mkhx.tool.base.usermap.UserMapResponse;

public class UserAuto implements Runnable {
	private boolean isFast = false;
	private LoginMainServer loginMainServer = null;
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;
	private UserInfoResponse userResponse = null;
	private HashMap<String, String> map = null;
	private String username;
	private String password;

	public UserAuto(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserAuto(String username, String password, boolean isFast) {
		this.username = username;
		this.password = password;
		this.isFast = isFast;
	}

	public boolean isFastRun() {
		return this.isFast;
	}

	private void doThings() {
		try {
			map = ToolCommonFunction.getInitMap();
			if (!isFast) {
				ToolLog.printInfo("****************开始处理用户：" + username
						+ "*****************");
			}
			login(username, password);
			if (isFast) {
				legionAttack();
				return;
			}
			
			// 好友互送体力
			sendEnergy();
			// 自动清除入侵
			cleanMonste();
			// 自动扫塔
			cleanTower();
			// 一键通关
			oneKeyThrough();
			// 自动搜索
			searchMap();
			// 自动打贼
			fightThief(userResponse);
			// 自动自由切磋
			freeFight();
			// 自动打排位站
			rankFight();
			// 开始闯地下城
			dungeon();
			// 设置默认卡组
			setDefautlCardGroup();
		} catch (Exception e) {
		}
	}

	private void oneKeyThrough() {
		try {
			String automap = map.get("automap");
			if(!(automap!=null && automap.equals("1"))){
				return;
			}
			ToolLog.printInfo("自动通图开始：");
			UserInfo userInfo = new UserInfo(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			UserInfoResponse userResponse = userInfo.request();
			int energy = Integer.parseInt(userResponse.Energy);

			UserMap userMap = new UserMap(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			UserMapResponse userMapResponse = userMap.request();
			System.out.println(((MapStage)userMapResponse.data.get(
					userMapResponse.data.size() - 1)).Index);
			int errorTry = 0;
			for (int i = Integer.parseInt(((MapStage)userMapResponse.data.get(
					userMapResponse.data.size() - 1)).Index) + 1; i < 150; i++) {
				ToolLog.printInfo("开始尝试通关，目前关卡为：" + i);
				ThroughMap throughMap = new ThroughMap(
						loginDestServer.getCookie(), loginMainResponse.GS_IP);

				throughMap.setMapStage(i + "");
				ThroughMapResponse res = throughMap.request();
//				ToolLog.printInfo(throughMap.getResponseText());
				if(res.status.equals("1")){
					errorTry = 0;
				}
				if(res.message!=null && res.message.equals("关卡尚未开启!")){
					errorTry ++;
					if(errorTry >= 3){
						ToolLog.printInfo("尝试通关三次均失败，不再尝试");
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void login(String username, String password) throws Exception {

		loginMainServer = new LoginMainServer(null);
		loginMainServer.setRequestBody(username, password);
		loginMainResponse = loginMainServer.request();
		// 登陆目标服务器
		loginDestServer = new LoginDestServer(loginMainServer.getCookie(),
				loginMainResponse.GS_IP);
		loginDestServer.setRequestBody(loginMainResponse.U_ID,
				loginMainResponse.userName, loginMainResponse.key,
				loginMainResponse.timestamp);
		loginDestServer.request();
		// 获取用户信息
		UserInfo userInfo = new UserInfo(loginDestServer.getCookie(),
				loginMainResponse.GS_IP);
		userResponse = userInfo.request();
		if (isFast) {
			// ToolLog.printInfo(userResponse.NickName + "开始处理军团事务");
		} else {
			ToolLog.printInfo(userResponse.NickName + "_体力值为："
					+ userResponse.Energy);
		}
	}

	private void legionAttack() {
		LegionAttack legionAtt = new LegionAttack(loginMainResponse,
				loginDestServer);
		legionAtt.run();
	}

	private void cleanTower() {
		// 清除塔列表
		String[] cleantowerList = null;
		if (map.get("cleantower") != null) {
			cleantowerList = map.get("cleantower").split(";");
		}
		if (cleantowerList != null && cleantowerList.length > 0) {
			// 获取当前卡组列表
			String thiefgroup = map.get("towergroup");
			CardGroup cardGroup = new CardGroup(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			CardGroupResponse cardGroupResponse = cardGroup.request();
			// 初使化自动切换卡组

			AutoChangeCardGroup chgGroup = new AutoChangeCardGroup(
					loginMainResponse, loginDestServer, userResponse,
					cardGroupResponse);
			if (thiefgroup != null) {
				try {
					ToolLog.printInfo("开始切换刷塔卡组,默认卡组为："
							+ (cardGroupResponse.groupids.indexOf(chgGroup
									.getDefaultGroupId()) + 1));
					chgGroup.changeCardGroup(thiefgroup);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			CleanTower cleanTower = new CleanTower(loginMainResponse,
					loginDestServer);
			if (map.get("onlybox") != null
					&& map.get("onlybox").toString().equals("1")) {
				ToolLog.printInfo("检测程序，模式为只打宝箱！");
			} else {
				ToolLog.printInfo("检测程序，模式为全刷！");
			}
			for (int i = 0; i < cleantowerList.length; i++) {
				if (map.get("onlybox") != null
						&& map.get("onlybox").toString().equals("1")) {
					cleanTower.cleanTowerNew(cleantowerList[i]);
				} else {
					cleanTower.cleanTower(cleantowerList[i]);
				}
			}
			if (thiefgroup != null) {
				chgGroup.returnGroup();
			}
		}
	}

	private void fightThief(UserInfoResponse userResponse) {
		String fightthief = map.get("fightthief");
		if (fightthief == null) {
			try {
				ToolCommonFunction.saveToSetup("fightthief", "1");
			} catch (IOException e) {
			}
		}
		if (fightthief != null && fightthief.equals("0")) {
			return;
		}
		try {
			ToolLog.printInfo("开始自动打贼！");
			String thiefgroup = map.get("thiefgroup");
			GetThieves getThieves = new GetThieves(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			GetThievesResponse getThievesResponse = getThieves.request();
			if (getThievesResponse.getSuitableThief() != null) {
				// 获取当前卡组列表
				CardGroup cardGroup = new CardGroup(
						loginDestServer.getCookie(), loginMainResponse.GS_IP);
				CardGroupResponse cardGroupResponse = cardGroup.request();
				// 初使化自动切换卡组
				AutoChangeCardGroup chgGroup = new AutoChangeCardGroup(
						loginMainResponse, loginDestServer, userResponse,
						cardGroupResponse);
				if (thiefgroup != null) {
					chgGroup.changeCardGroup(thiefgroup);
				}
				FightThieves fightThieves = new FightThieves(
						loginDestServer.getCookie(), loginMainResponse.GS_IP);
				fightThieves
						.setThiefId(getThievesResponse.getSuitableThief().UserThievesId);
				fightThieves.request();
				ToolLog.printInfo("打贼完成！");
				if (thiefgroup != null) {
					chgGroup.returnGroup();
				}
			} else {
				ToolLog.printInfo("打贼失败，可能未找到盗贼！");
			}
		} catch (Exception e) {

		}
	}

	private void searchMap() {
		// 搜索接口
		String search = map.get("search");
		if (search != null && search.equals("1")) {
			SearchMap searMap = new SearchMap(loginMainResponse,
					loginDestServer);
			searMap.searchMap();
		}
	}

	private void rankFight() {
		String rankfight = map.get("rankfight");
		if (rankfight != null && rankfight.equals("1")) {
			ToolLog.printInfo("开始打排位战。。。");
			RankFight rankFight = new RankFight(loginMainResponse,
					loginDestServer);
			rankFight.rankFight();
		}
	}

	private void dungeon() {
		ToolLog.printInfo("开始闯地下城。。。");
		Dungeon dungeon = new Dungeon(loginMainResponse, loginDestServer);
		dungeon.doDungeon();
	}
	private void lls(){
		ToolLog.printInfo("开始打LLS");
		
	}
	private void freeFight() {
		String freefight = map.get("freefight");
		if (freefight == null) {
			try {
				ToolCommonFunction.saveToSetup("freefight", "1");
			} catch (IOException e) {
			}
		}
		if (freefight != null && freefight.equals("0")) {
			return;
		}
		FreeFight freeFight = new FreeFight(loginMainResponse, loginDestServer);
		freeFight.freeFight();
	}

	private void sendEnergy() {
		String energy = map.get("energy");
		if (energy == null) {
			try {
				ToolCommonFunction.saveToSetup("energy", "1");
			} catch (IOException e) {
			}
		}
		if (energy != null && energy.equals("0")) {
			return;
		}
		FriendEnergy friendEnergy = new FriendEnergy(loginMainResponse,
				loginDestServer);
		friendEnergy.getAndsendEnergy();
	}

	private void cleanMonste() {
		String cleanmap = map.get("cleanmap");
		if (cleanmap != null && !cleanmap.equals("") && cleanmap.equals("1")) {
			ToolLog.printInfo("开始自动清除入侵！");
			CleanMap cleanMap = new CleanMap(loginMainResponse, loginDestServer);
			cleanMap.cleanMap();
			cleanMap = null;
			ToolLog.printInfo("清空入侵完成！");
		}
	}

	private void setDefautlCardGroup() {
		// 获取当前卡组列表
		String defaultgroup = map.get("defaultgroup");
		CardGroup cardGroup = new CardGroup(loginDestServer.getCookie(),
				loginMainResponse.GS_IP);
		CardGroupResponse cardGroupResponse = cardGroup.request();
		// 初使化自动切换卡组

		AutoChangeCardGroup chgGroup = new AutoChangeCardGroup(
				loginMainResponse, loginDestServer, userResponse,
				cardGroupResponse);
		if (defaultgroup != null) {
			try {
				ToolLog.printInfo("开始切换为默认卡组：");
				chgGroup.changeCardGroup(defaultgroup);
				ToolLog.printInfo("切换：");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		doThings();
	}
}
