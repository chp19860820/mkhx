package com.mkhx.tool.base.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mkhx.tool.base.ToolCommonFunction;

public class test {

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String in;
		System.out.print("请输入cardID：");
		BufferedReader strin = new BufferedReader(new InputStreamReader(
				System.in));
		in = strin.readLine();
		System.out.println("对应的卡牌为：" + ToolCommonFunction.getCardNameById(in));
		main(null);
	}

}
