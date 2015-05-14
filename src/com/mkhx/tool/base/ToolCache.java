package com.mkhx.tool.base;

import java.util.HashMap;
import java.util.Map;

public class ToolCache {
	public static Map desSerCache = new HashMap();
	public static Map mainSerCache = new HashMap();
	public static Map mainResCache = new HashMap();
	public static int COUNTER;
	public static synchronized void incresCounter(){
		COUNTER = COUNTER + 1;
	}
	public static synchronized void setCounter(int COUNTER){
		ToolCache.COUNTER = COUNTER;
	}
}
