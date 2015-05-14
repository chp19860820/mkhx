package com.mkhx.tool.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ToolCommonFunction {
	// 获取卡组信息
	public static JSONArray getCardArray() {
		Reader bufferReader = null;
		try {
			File file = new File("allcard");
			bufferReader = new BufferedReader(new FileReader(file));
			JSONParser jsonP = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonP.parse(bufferReader);
			String status = jsonObject.get("status").toString();
			if (status != null && status.equals("1")) {
				JSONObject jData = (JSONObject) jsonObject.get("data");
				JSONArray jCards = (JSONArray) jData.get("Cards");
				return jCards;
			}
			return null;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (bufferReader != null) {
					bufferReader.close();
					bufferReader = null;
				}
			} catch (Exception e) {
			}
		}
	}

	public static String getCardNameById(String id) {
		return getCardNameById((long) Integer.parseInt(id));
	}

	public static JSONObject getCardById(String id) {
		return getCardById((long) Integer.parseInt(id));
	}

	public static JSONObject getCardById(long id) {
		JSONArray jArr = getCardArray();
		int key = (int) id;
		if (key == 0 || jArr == null) {
			return null;
		}
		int start = 0;// 开始
		int end = jArr.size() - 1;// 结束
		while (start <= end) {
			int mid = (start + end) / 2;
			JSONObject jCard = (JSONObject) jArr.get(mid);
			long cardId = Integer.parseInt(jCard.get("CardId").toString());
			if (cardId == key) {
				return jCard;
			}
			if (cardId < key) {
				start = mid + 1;
			}
			if (cardId > key) {
				end = mid - 1;
			}
		}
		return null;
	}

	// 折半查找CARD的名称
	public static String getCardNameById(long id) {
		JSONArray jArr = getCardArray();
		int key = (int) id;
		if (key == 0 || jArr == null) {
			return "";
		}
		int start = 0;// 开始
		int end = jArr.size() - 1;// 结束
		while (start <= end) {
			int mid = (start + end) / 2;
			JSONObject jCard = (JSONObject) jArr.get(mid);
			long cardId = Integer.parseInt(jCard.get("CardId").toString());
			if (cardId == key) {
				return jCard.get("CardName").toString();
			}
			if (cardId < key) {
				start = mid + 1;
			}
			if (cardId > key) {
				end = mid - 1;
			}
		}
		return "";
	}

	private static void setup(File file) throws Exception {
		System.out.println("********************************************************");
		file.createNewFile();
		System.out.println("*****程序开始初使化中***********************************");
		enterAccount(file);
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
		System.out.println("*****程序开始设置自动清除入侵***************************");
		saveToFile(file, "cleanmap", "1");
		System.out.println("*****程序开始设置自动刷塔顺序，默认为6、8、7************");
		saveToFile(file, "cleantower", "6;8;7");
		System.out.println("*****程序开始设置自动搜索地图功能，默认开启*************");
		saveToFile(file, "search", "1");
		System.out.println("*****程序开始设置自动打贼功能，默认开启*****************");
		saveToFile(file, "fightthief", "1");
		System.out.println("*****程序开始设置自动赠送、获取好友体力功能，默认开启***");
		saveToFile(file, "energy", "1");
		System.out.println("*****程序开始设置默认清除塔规则，默认全刷模式***********");
		saveToFile(file, "onlybox", "0");
		System.out.println("*****程序开始设置请求间隔时间，默认设置为2秒************");
		saveToFile(file, "sleep", "2");
		System.out.println("*****程序开始设置主程序请求间隔时间，默认设置为30分钟***");
		saveToFile(file, "mainsleep", "30");
		System.out.println("*****程序开始设置自动切磋碎片功能，默认开启*************");
		saveToFile(file, "freefight", "1");
		System.out.println("*****程序开始设置自动竞技场排名，默认关闭***************");
		saveToFile(file, "rankfight", "0");
		Thread.sleep(3 * 1000);
		if (file.length() == 0) {
			file.delete();
		}
	}

	// private static void setupMap(File file) throws Exception{
	// BufferedReader strin = new BufferedReader(new InputStreamReader(
	// System.in));
	// System.out.println("是否自动清除入侵？y表示确认，n表示重新输入");
	// }
	private static void enterAccount(File file) throws Exception {
		String accounts = "";
		String passwords = "";
		boolean flag = true;
		while (flag) {
			BufferedReader strin = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("请输入帐号！");
			String account = strin.readLine();
			System.out.println("请输入该帐号密码！");
			String password = strin.readLine();
			if (account.equals("") || password.equals("")) {
				System.out.println("输入帐号及密码有误，帐号及密码均不能为空，请重新输入");
				flag = true;
				continue;
			}
			System.out.println("输入帐号及密码是否正确？y表示保存，n表示重新输入");
			String strSave = strin.readLine();
			if (strSave.equalsIgnoreCase("y")) {
				if (accounts.equals("")) {
					accounts = account;
					passwords = password;
				} else {
					accounts += ";" + account;
					passwords += ";" + password;
				}
			} else {
				flag = true;
				continue;
			}
			System.out.println("继续输入帐号？y表示继续，n表示结束");
			String strFlag = strin.readLine();
			if (strFlag.equalsIgnoreCase("y")) {
				flag = true;
			} else {
				flag = false;
			}
		}
		saveToFile(file, "account", accounts);
		saveToFile(file, "password", passwords);
	}

	public static void saveToSetup(String key, String value) throws IOException {
		File file = new File("setup.ini");
		if (!file.exists()) {
			file.createNewFile();
		}
		saveToFile(file, key, value);
	}

	public static void saveToFile(File file, String key, String value) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file, true);
			os.write(("\r\n" + key + "=" + value).getBytes("utf-8"));
		} catch (Exception e) {
		} finally {
			try {
				os.close();
				os = null;
			} catch (Exception e) {
			}
		}
	}

	public static HashMap<String, String> getInitMap() {
		BufferedReader bufferReader = null;
		try {
			File file = new File("setup.ini");
			if (!file.exists()) {
				setup(file);
			}
			// ToolLog.printInfo("filePath:" + file.getAbsolutePath());
			bufferReader = new BufferedReader(new FileReader(file));
			String line = null;
			HashMap<String, String> map = new HashMap<String, String>();
			while ((line = bufferReader.readLine()) != null) {
				try {
					// 清除注释
					if (line.indexOf("#") == 0 || line.trim().equals("")) {
						continue;
					}
					String[] param = line.split("=");
					map.put(param[0], param[1]);
				} catch (Exception e) {
					// 一般出错了不需要处理
				}
			}
			return map;
		} catch (Exception e) {
			return null;
		} finally {
			if (bufferReader != null) {
				try {
					bufferReader.close();
					bufferReader = null;
				} catch (IOException e) {
				}
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
	 /**
     * 将字符串按特定字符拆分为数组.
     * <p>将字符串按特定字符拆分为数组<br>
     * 应用于所有模块
     * @param str 原字符串
     * @param x 拆分字符
     * @return 字符串数组
     * @author 张涛
     * @version Version 1.00
     */
    public static String[] com_Split(String str,char x) throws Exception
    {
        String s = new String();
        String[] arr;
        int len=1, k=0;

        for (k=0; k<str.length(); k++)
            if (str.charAt(k) == x) len++;

        arr = new String[len];
        k = 0;
        for(int i=0; i<str.length(); i++)
        {
            if(str.charAt(i) == x)
            {
                arr[k] = s;
                k++;
                s = new String();
            }
            else
            {
                s += str.charAt(i);
            }
        }
        arr[k] = s;

        return arr;
    }

    /**
     * 将字符串数组按特定字符串组合为字符串.
     * <p>将字符串数组按特定字符串组合为字符串<br>
     * 应用于所有模块
     * @param arr 原字符串数组
     * @param x 间隔字符串
     * @return 字符串
     * @author 张涛
     * @version Version 1.00
     */
    public static String com_Join(String[] arr, String x) throws Exception
    {
        String s = new String();

        for (int i=0; i<(arr.length - 1); i++)
			s += (arr[i] + x);

		s += arr[arr.length-1];

        return s;
    }
    
    /**
     * 将字符串中某个字符替换为指定字符串.
     * <p>将字符串中某个字符替换为指定字符串<br>
     * 应用于所有模块
     * @param src 原字符串
     * @param rep 所要替换的字符
	 * @param tar 所要替换成的字符串
     * @return 字符串
     * @author 张涛
     * @version Version 1.00
     */
    public static String com_ReplaceSubstring(String src, char rep, String tar) throws Exception
    {
        String[] s = com_Split(src, rep);
        return com_Join(s, tar);
    }
    public static String com_GetSubStrBetween(String srcStr, String startStr,String endStr, boolean case_insens) throws Exception
    {
    /**
     * 获取两个字符串之间的值.
     * <p>获取两个字符串之间的值<br>
     * 应用于所有模块
     * @param srcStr 原字符串数组
     * @param startStr 开始字符串
     * @return endStr 开始字符串
     * @return endStr 是否区分大小写:ture 区分;false,不区分;
     * @author 吴良喜
     * @version Version 1.00
     */ 
		int stPos=0;
		int enPos=0;
		String srcStr_L;
		
		srcStr_L = srcStr;
		if(!case_insens){
			srcStr_L = srcStr.toLowerCase();
			startStr = startStr.toLowerCase();
			endStr = endStr.toLowerCase();
		}
		stPos = srcStr_L.indexOf(startStr);
		if("".equals(endStr)){
			enPos = srcStr.length();
		}else{
			enPos = stPos+startStr.length()+srcStr_L.substring(stPos+startStr.length()).indexOf(endStr);
		}
		if(stPos<1){
			return "";
		}
		stPos = stPos + startStr.length();
		if(enPos < stPos){
			return srcStr.substring(stPos);
		}else{
			return srcStr.substring(stPos,enPos);
		}
    }
    public static int randomNum(int min,int max){
		if(ToolCache.COUNTER > min && ToolCache.COUNTER < max){
			ToolCache.incresCounter();
		}
		if(ToolCache.COUNTER < min){
			ToolCache.setCounter(min+1);
		}
		if(ToolCache.COUNTER > max){
			ToolCache.setCounter(min+1);
		}
		return ToolCache.COUNTER;
    }
}
