package com.mkhx.tool.base.tower;

import com.mkhx.tool.base.ToolLog;
import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;

public class CleanTower {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public CleanTower(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}

	public void cleanTower(String towerId) {
		cleanTower(towerId, false);
	}

	/*
	 * 只打箱子方法，不打塔怪，节约体力
	 */
	public void cleanTowerNew(String towerId) {
		boolean isResetTower = false;
		// 获取选定塔的信息
		Tower tower = new Tower(loginDestServer.getCookie(),
				loginMainResponse.GS_IP);
		tower.setMapStageId(towerId);
		TowerResponse towerResponse = tower.request();
		// 已经清空的塔，按旧有的逻辑处理。。。
		if (towerResponse.Clear.equals("1")) {
			// 如果塔允许免费重置
			if (towerResponse.FreeReset.equals("1")) {
				isResetTower = true;
			} else {
				ToolLog.printInfo(towerId + "塔已经清空,请重置！");
				return;
			}
		} else {
			// 只有允许免费重置的情况下，才重置
			if (towerResponse.FreeReset.equals("1")) {
				// 先假设塔已经清清，需要重置
				isResetTower = true;
				for (int i = 1; i <= 5; i++) {
					TowerLayerInfo towerLayerInfo = new TowerLayerInfo(
							loginDestServer.getCookie(),
							loginMainResponse.GS_IP);
					towerLayerInfo.setTowerLayer(towerId, i + "");
					TowerLayerInfoResponse layerInfoResponse = towerLayerInfo
							.request();
					// 只要任意一层的箱子未清空，则不需要重置，否则，重置
					if (layerInfoResponse.RemainBoxNum > 0) {
						isResetTower = false;
						break;
					}
				}
			}
		}
		// 重置塔
		if (isResetTower) {
			ResetTower resetTower = new ResetTower(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			resetTower.setTowerId(towerId);
			resetTower.request();
		}
		// 遍历清空塔
		for (int i = 1; i <= 5; i++) {
			TowerLayerInfo towerLayerInfo = new TowerLayerInfo(
					loginDestServer.getCookie(), loginMainResponse.GS_IP);
			towerLayerInfo.setTowerLayer(towerId, i + "");
			TowerLayerInfoResponse layerInfoResponse = towerLayerInfo.request();
			// 如果该层还没清空，则先清空
			/*
			 * 遍历每层中的所有点 迷宫为8*4二维数组
			 */
			for (int j = 0; j < layerInfoResponse.ItemIndex.length; j++) {
				// 清空箱子、下一层怪物，普通怪不清空
				if (layerInfoResponse.ItemIndex[j] == layerInfoResponse.BOX
						|| layerInfoResponse.ItemIndex[j] == layerInfoResponse.NEXTSTEP) {
					CleanStageTower cleanStageTower = new CleanStageTower(
							loginDestServer.getCookie(),
							loginMainResponse.GS_IP);
					cleanStageTower.setStageLayerItem(towerId, i + "", j + "");
					CleanStageTowerResponse res = cleanStageTower.request();
					if (res.cardName != null && !(res.cardName.equals(""))) {
						ToolLog.printInfo("普通箱子掉落：" + "("
								+ res.card.get("Color").toString() + "星卡)"
								+ res.cardName);
					}
					if (res.extraCardName != null
							&& !(res.extraCardName.equals(""))) {
						ToolLog.printInfo("通关奖励：" + "("
								+ res.extraCard.get("Color").toString() + "星卡)"
								+ res.extraCardName);
					}
				}
			}
		}
	}

	/*
	 * 默认清空塔才重置，刷怪包含塔怪
	 */
	public void cleanTower(String towerId, boolean resetByStone) {
		try {
			// 获取选定塔的信息
			Tower tower = new Tower(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			tower.setMapStageId(towerId);
			TowerResponse towerResponse = tower.request();
			// 如果塔已经清空
			if (towerResponse.Clear.equals("1")) {
				// 如果塔允许免费重置
				if (towerResponse.FreeReset.equals("1") || resetByStone) {
					ResetTower resetTower = new ResetTower(
							loginDestServer.getCookie(),
							loginMainResponse.GS_IP);
					resetTower.setTowerId(towerId);
					resetTower.request();
				} else {
					ToolLog.printInfo(towerId + "塔已经清空,请重置！");
					return;
				}
			}
			// 遍历清空塔
			for (int i = 1; i <= 5; i++) {
				TowerLayerInfo towerLayerInfo = new TowerLayerInfo(
						loginDestServer.getCookie(), loginMainResponse.GS_IP);
				towerLayerInfo.setTowerLayer(towerId, i + "");
				TowerLayerInfoResponse layerInfoResponse = towerLayerInfo
						.request();
				// 如果该层还没清空，则先清空
				/*
				 * 遍历每层中的所有点 迷宫为8*4二维数组
				 */
				for (int j = 0; j < layerInfoResponse.ItemIndex.length; j++) {
					// 清空箱子、怪物，下一层怪物
					if (layerInfoResponse.ItemIndex[j] == layerInfoResponse.BOX
							|| layerInfoResponse.ItemIndex[j] == layerInfoResponse.MONSTER
							|| layerInfoResponse.ItemIndex[j] == layerInfoResponse.NEXTSTEP) {
						CleanStageTower cleanStageTower = new CleanStageTower(
								loginDestServer.getCookie(),
								loginMainResponse.GS_IP);
						cleanStageTower.setStageLayerItem(towerId, i + "", j
								+ "");
						CleanStageTowerResponse res = cleanStageTower.request();
						if (res!=null && res.cardName != null && !(res.cardName.equals(""))) {
							ToolLog.printInfo("普通箱子掉落：" + "("
									+ res.card.get("Color").toString() + "星卡)"
									+ res.cardName);
						}
						if (res.extraCardName != null
								&& !(res.extraCardName.equals(""))) {
							ToolLog.printInfo("通关奖励：" + "("
									+ res.extraCard.get("Color").toString() + "星卡)"
									+ res.extraCardName);
						}
					}
				}
			}

		} catch (Exception e) {
		}
	}
}
