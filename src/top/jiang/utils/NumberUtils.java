package top.jiang.utils;

import java.util.Random;

public class NumberUtils {
	/* 
	 * ���س���Ϊ��strLength�������������ǰ�油0 
	 */  
	public static String getFixLenthString(int strLength) {  

		Random rm = new Random();  

		// ��������  
		double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  

		// ����õĻ�������ת��Ϊ�ַ���  
		String fixLenthString = String.valueOf(pross);  

		// ���ع̶��ĳ��ȵ������  
		return fixLenthString.substring(1, strLength + 1);  
	}  

}
