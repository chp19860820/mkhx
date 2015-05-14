package com.mkhx.tool.base.buycard;

import com.mkhx.tool.base.login.LoginDestServer;
import com.mkhx.tool.base.login.LoginMainServerResponse;

public class BuyCards {
	private LoginMainServerResponse loginMainResponse = null;
	private LoginDestServer loginDestServer = null;

	public BuyCards(LoginMainServerResponse loginMainResponse,
			LoginDestServer loginDestServer) {
		this.loginMainResponse = loginMainResponse;
		this.loginDestServer = loginDestServer;
	}
	public void buyCards(String shopType, String cardNum)
			throws Exception {
		buyCards(Integer.parseInt(shopType), Integer.parseInt(cardNum));
	}

	public void buyCards(int shopType, int cardNum) {
		for (int i = 0; i < cardNum; i++) {
			BuyCard buyCard = new BuyCard(loginDestServer.getCookie(),
					loginMainResponse.GS_IP);
			buyCard.setShopId(shopType + "");
			buyCard.request();
		}
	}
}
