package com.mkhx.tool.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolLog {
	private static File logFile = null;
	private static boolean isOutPutToConsle = true;
	private static boolean isOutPutToFile = true;
	
	public static void printInfo(String info) {
		String  date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		OutputStream os = null;
		try {
			logFile = new File("log.txt");
//			System.out.println("logFile:" + logFile.getAbsolutePath());
			if (isOutPutToConsle) {
				System.out.println(date + ":" + info);
			}
			if (isOutPutToFile) {
				os = new FileOutputStream(logFile, true);
				os.write((date + ":" + info + "\r\n").getBytes());
			}
		} catch (Exception e) {
		} finally {
			try {
				os.close();
				os = null;
			} catch (Exception e) {
			}
		}
	}
	public static void printDebugMsg(String info) {
		String  date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		OutputStream os = null;
		try {
			logFile = new File("debuglog.txt");
			if (isOutPutToFile) {
				os = new FileOutputStream(logFile, true);
				os.write((date + ":" + info + "\r\n").getBytes());
			}
		} catch (Exception e) {
		} finally {
			try {
				os.close();
				os = null;
			} catch (Exception e) {
			}
		}
	}
}
