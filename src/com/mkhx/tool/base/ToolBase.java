package com.mkhx.tool.base;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

public class ToolBase {
	public final String loginMainServerURL = "http://pp.fantasytoyou.com/pp/httpService.do";
	public final String defaultDestHost = "http://s3.mysticalcard.com/";
	public final String loginDestServerPath = "login.php?do=PassportLogin&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String buyCardPath = "shop.php?do=Buy&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String towerPath = "maze.php?do=Show&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String resetTowerPath = "maze.php?do=Reset&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String towerLayerInfoPath = "maze.php?do=Info&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String cleanStagePath = "maze.php?do=Battle&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String userMapPath = "mapstage.php?do=GetUserMapStages&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String cleanMapPath = "mapstage.php?do=EditUserMapStages&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String userInfoPath = "user.php?do=GetUserinfo&OpenCardChip=1&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String meditePath = "meditation.php?do=Npc&v=?&phpp=ANDROID&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String dealMeditePath = "meditation.php?do=Deal&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String getThievesPath = "arena.php?do=GetThieves&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String searchMapPath = "mapstage.php?do=Explore&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String fightThievesPath = "arena.php?do=ThievesFight&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String getFriendsPath = "friend.php?do=GetFriends&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String sendFriendEnergyPath = "fenergy.php?do=SendFEnergy&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String getFriendEnergyPath = "fenergy.php?do=GetFEnergy&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String getCardGroupPath = "card.php?do=GetCardGroup&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String setCardGroupPath = "card.php?do=SetDefalutGroup&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String getCompetitorsPath = "arena.php?do=GetCompetitors&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String freefightCompetitorsPath = "arena.php?do=FreeFight&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String getRankCompetitorsPath = "arena.php?do=GetRankCompetitors&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String rankfightPath = "arena.php?do=RankFight&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String getUserDungeonPath = "dungeon.php?do=GetUserDungeon&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String fightDungeonPath = "dungeon.php?do=Fight&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String sweepDungeonPath = "dungeon.php?do=Sweep&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String awardSalaryPath = "user.php?do=AwardSalary&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String getBossPath = "boss.php?do=GetBoss&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String fightBossPath = "boss.php?do=Fight&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String legionInfoPath = "legionattack.php?do=info&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String joinLegionPath = "legionattack.php?do=join&&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public final String throughMapPath = "mapstage.php?do=EditUserMapStages&v=?&phpp=ANDROID&phpl=ZH_CN&pvc=1.4.0&pvb=2013-11-22%2010%3A30%3A18";
	public String cookie = null;
	public String responseText = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mkhx.tool.base.ToolFactory#request(java.lang.String,
	 * java.lang.String, java.lang.String) 模拟请求接口
	 */
	public String[] request(String urlstr, String cookie, String requestBody) {
		try {
			HashMap<String, String> map = ToolCommonFunction.getInitMap();
			Integer sleepInt = 2;
			try {
				String sleep = map.get("sleep");
				sleepInt = Integer.parseInt(sleep);
				Thread.sleep(sleepInt*1000);
			} catch (Exception e) {
			}
		} catch (Exception e) {
			ToolLog.printInfo("为什么休息会出问题呢？真心奇怪");
		}
		String response[] = new String[2];
		URL url = null;
		HttpURLConnection urlc = null;
		try {
			urlstr = urlstr.replaceFirst("&v=?",
					"&v=" + ToolCommonFunction.randomNum(1000, 9999));
//			ToolLog.printInfo("userstr:" + urlstr);
			url = new URL(urlstr);
			urlc = (HttpURLConnection) url.openConnection();
			if (cookie != null) {
				if (cookie.indexOf("; expires") > -1) {
					cookie = cookie.substring(0, cookie.indexOf("; expires"));
				}
				urlc.setRequestProperty("Cookie", cookie);
			}
			if (true) {

				urlc.setRequestProperty("Accept-Encoding", "deflate, gzip");
				urlc.setRequestProperty(
						"Accept",
						"text/xml, application/xml, application/xhtml+xml, text/html;q=0.9, text/plain;q=0.8, text/css, image/png, image/jpeg, image/gif;q=0.8, application/x-shockwave-flash, video/mp4;q=0.9, flv-application/octet-stream;q=0.8, video/x-flv;q=0.7, audio/mp4, application/futuresplash, */*;q=0.5");
				urlc.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Android; U; zh-CN) AppleWebKit/533.19.4 (KHTML, like Gecko) AdobeAIR/3.9");
				urlc.setRequestProperty("x-flash-version", "11,9,900,117");
				urlc.setRequestProperty("Connection", "Keep-Alive");
				urlc.setRequestProperty("Cache-Control", "no-cache");
				urlc.setRequestProperty("Referer", "app:/assets/CardMain.swf");
				urlc.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
			}
			// 设置HTTP类型
			urlc.setRequestMethod("POST");
			// 允许POST数据
			urlc.setDoOutput(true);
			urlc.setDoInput(true);
//			urlc.setReadTimeout(5000);
			// 添加POST数据
			if (requestBody != null && !requestBody.equals("")) {
				byte[] bytes = requestBody.getBytes("UTF-8");
				urlc.getOutputStream().write(bytes);
			} else {
				urlc.setRequestProperty("Content-Length", "0");
			}
			InputStream inStream = urlc.getInputStream();
			// 获得返回的内容，根据是否压缩，调用不同的流转换方法
			response[0] = inputStream2String(inStream,
					urlc.getHeaderField("Content-Encoding"));
//			ToolLog.printInfo("response:" + response[0]);
			// 获得cookie，以便获得下面的请求返回，主要是权限校验这一块
			response[1] = urlc.getHeaderField("Set-Cookie");
		} catch (Exception e) {
		} finally {
			try {
				if (urlc != null) {
					urlc.disconnect();
					urlc = null;
				}
				if (url != null) {
					url = null;
				}
			} catch (Exception e) {
			}
		}
		return response;
	}

	public static String inputStream2String(InputStream is,
			String contentEncoding) throws Exception {
		if (contentEncoding == null
				|| !contentEncoding.equalsIgnoreCase("gzip")) {
			return inputStream2String(is);
		} else {
			return inputStream2String(is, true);
		}
	}

	/*
	 * @param InputStream 输出流
	 * 
	 * @param flag 压缩标志
	 */
	public static String inputStream2String(InputStream is, boolean flag)
			throws Exception {
		java.util.zip.GZIPInputStream gzstream = null;
		try {
			if (!flag) {
				return inputStream2String(is);
			}
			StringBuffer out = new StringBuffer();
			gzstream = new GZIPInputStream(is);
			byte[] b = new byte[2048];
			for (int n; (n = gzstream.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			return out.toString();
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (Exception e) {
			}
			try {
				if (gzstream != null) {
					gzstream.close();
					gzstream = null;
				}
			} catch (Exception e) {

			}
		}
	}

	/*
	 * 非分块压缩转换成String对象
	 */
	public static String inputStream2String(InputStream is) throws Exception {
		try {
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[1024];
			for (int n; (n = is.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			return out.toString();
		} catch (Exception e) {
			return null;
		} finally {
			try {
				is.close();
				is = null;
			} catch (Exception e) {
			}
		}
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
}
