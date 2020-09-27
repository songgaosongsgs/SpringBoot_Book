package com.bookstore.work.config;

public class Utils {
	
	public static String toChinese(String string) {
		 
        String[] s1 = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"  };
        String[] s2 = { "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };

        String result = "";

        int n = string.length();
        for (int i = 0; i < n; i++) {

            int num = string.charAt(i) - '0';

            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;

    }
	public  static  void main(String[] args)  {
		String chinese = toChinese("1052");
		System.out.println(chinese);
	}


}
