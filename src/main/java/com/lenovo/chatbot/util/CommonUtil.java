package com.lenovo.chatbot.util;

public class CommonUtil {
	
	public static String languageHandle(String str) {
		StringBuilder sb = new StringBuilder();
		char[] charArray = str.toCharArray();
		for(int i=0;i<charArray.length;i++) {
			char c = charArray[i];
			if( c >= 0x0391 && c <= 0xFFE5) {
				//中文后面自动加空格
				sb.append(c).append(" ");
			}else {
				sb.append(c);
				while(i++<charArray.length-1) {
					System.out.println(i);
					char c1 = charArray[i];
					if(c1 >= 0x0391 && c1 <= 0xFFE5) {
						sb.append(" ").append(c1);
						break;
					}else {
						sb.append(c1);
					}
				}
			}
			
		}
		return sb.toString();
	}
}
