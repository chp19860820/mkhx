package com.mkhx.tool.base.main;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import java.util.zip.GZIPInputStream;

public class TestInterface {

	

	public static void main(String[] args) throws Exception {
		//String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body><contractCISI xmlns=\"urn:bean:service:ffcs:com\"><REQUEST xmlns=\"\"><BASEINFO><MSGID>GDZFSPXT_HTFZ_20140605021215_w3B96</MSGID><PMSGID>sPMSGID</PMSGID><RETRY>0</RETRY><SENDTIME>20140605021215</SENDTIME><SERVICENAME>sSERVICENAME</SERVICENAME><S_PROVINCE>sS_PROVINCE</S_PROVINCE><S_SYSTEM>sS_SYSTEM</S_SYSTEM><T_PROVINCE>sT_PROVINCE</T_PROVINCE><T_SYSTEM>sT_SYSTEM</T_SYSTEM></BASEINFO><MESSAGE>&lt;CONTRACTSYNC&gt;&lt;PARTATYPE&gt;440&lt;/PARTATYPE&gt;&lt;APPLYDEPTCODE&gt;0900000767&lt;/APPLYDEPTCODE&gt;&lt;APPLYUSERID&gt;44093423@GD&lt;/APPLYUSERID&gt;&lt;OPERATETYPE&gt;0&lt;/OPERATETYPE&gt;&lt;SOURCE_ID&gt;463FF025C07A39D548257CCA000EDCB9&lt;/SOURCE_ID&gt;&lt;FUNDFLAG&gt;2&lt;/FUNDFLAG&gt;&lt;EMERGENCYDEGREE&gt;1&lt;/EMERGENCYDEGREE&gt;&lt;CONTRACTNAME&gt;&#x5173;&#x4E8E;463FF025C07A39D548257CCA000EDCB9&#x7684;&#x7533;&#x8BF7;&lt;/CONTRACTNAME&gt;&lt;PARTBINFO&gt;&lt;MANUNO&gt;44097087@GD&lt;/MANUNO&gt;&lt;/PARTBINFO&gt;&lt;FILEINFO&gt;&lt;TYPE&gt;QT&lt;/TYPE&gt;&lt;NAME&gt;att.doc&lt;/NAME&gt;&lt;URL&gt;&lt;![CDATA[http://www.baidu.com/att.doc]]&gt;&lt;/URL&gt;&lt;FILESIZE&gt;75&lt;/FILESIZE&gt;&lt;/FILEINFO&gt;&lt;/CONTRACTSYNC&gt;</MESSAGE></REQUEST></contractCISI></soapenv:Body></soapenv:Envelope>";
		//Vector v = TestInterface.request("http://10.157.128.3:9001/MSS_MAIN_ENTRY/PS/GDMSSSERV", null, body);
//		Vector v = TestInterface.request("http://127.0.0.1:8080/servlet/pushMsg", null, ("{\"userName\":\"shadmin\",\"title\":\"test_chp\",\"body\":\"aaaa\",\"type\":\"msg\",\"expiretm\":\"2016-01-31 10:38:24\"}"));
//		System.out.println(escape("{'userName':'shadmin','title':'test_chp','body':'aaaa','type':'msg','expiretm':'2016-01-31 10:38:24'}"));
		//System.out.println("v:" + v);
		File file = new File("D:\\myeclipse.zip");
		System.out.println("file:" + file.length());
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mkhx.tool.base.ToolFactory#request(java.lang.String,
	 * java.lang.String, java.lang.String) 模拟请求接口
	 */
	public static Vector request(String urlstr, String cookie, String requestBody) {
		Vector response = new Vector();
		URL url = null;
		HttpURLConnection urlc = null;
		try {
			url = new URL(urlstr);
			urlc = (HttpURLConnection) url.openConnection();
			if (cookie != null) {
				urlc.setRequestProperty("Cookie", cookie);
			}
			urlc.setRequestProperty("Content-Type","text/xml; charset=utf-8");
			urlc.setRequestProperty(
					"Accept",
					"application/soap+xml, application/dime, multipart/related, text/*");
			urlc.setRequestProperty(
					"User-Agent",
					"Axis/1.4");
			urlc.setRequestProperty("Host", "10.157.128.3:9001");
			urlc.setRequestProperty("Connection", "Keep-Alive");
			urlc.setRequestProperty("Cache-Control", "no-cache");
			urlc.setRequestProperty("Pragma","no-cache");
			urlc.setRequestProperty("Pragma","no-cache");
			urlc.setRequestProperty("SOAPAction","\"http://sap.com/xi/WebService/soap1.1\"");
			urlc.setRequestProperty("Authorization","Basic T1NCX0hURkw6d2VsY29tZTE=");
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
			String responsebody = inputStream2String(inStream,
					urlc.getHeaderField("Content-Encoding"));
			// 获得返回的内容，根据是否压缩，调用不同的流转换方法
			if(responsebody == null){
				requestBody = "";
			}
			response.add(responsebody);
//			ToolLog.printInfo("response:" + response[0]);
			// 获得cookie，以便获得下面的请求返回，主要是权限校验这一块
			if( urlc.getHeaderField("Set-Cookie") == null){
				response.add("");
			}else{
				response.add(urlc.getHeaderField("Set-Cookie"));
			}
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