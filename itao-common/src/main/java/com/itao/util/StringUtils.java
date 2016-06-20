package com.itao.util;


import com.itao.planet.Constants;

/**
 * 字符串工具类。
 * 
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public abstract class StringUtils {

	private StringUtils() {}

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value 待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if(length < 1)
			return false;
		
		int i = 0;
		if(length > 1 && chars[0] == '-')
			i = 1;
		
		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检查对象是否为正数。(不包含0)
	 */
	public static boolean isDigit(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if(length < 1)
			return false;
		
		for (int i=0; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检查字符串是否包含子字符串
	 * @param own
	 * @param targets
	 * @return
	 */
	public static boolean contains(String own,String... targets){
		for(String target : targets){
			if(own.indexOf(target)>-1){
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	/**
	 * 驼峰命名风格改下划线命名风格
     */
	public static String toUnderlineStyle(String name) {
		StringBuilder newName = new StringBuilder();
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i > 0) {
					newName.append("_");
				}
				newName.append(Character.toLowerCase(c));
			} else {
				newName.append(c);
			}
		}
		return newName.toString();
	}

	public static String convertString(byte[] data, int offset, int length) {
		if (data == null) {
			return null;
		} else {
			try {
				return new String(data, offset, length, Constants.CHARSET_UTF8);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	

	public static byte[] convertBytes(String data) {
		if (data == null) {
			return null;
		} else {
			try {
				return data.getBytes(Constants.CHARSET_UTF8);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 将字符串首字母大写
	 * @param str
	 * @return
	 */
	public static String toUpperStringFirstChar(String str){
		if(str==null|| str.length()<1) return str;
		
		StringBuffer sb = new StringBuffer(str);
	    sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString(); 
	}

	/**
	 * 从字符串提取数值并以{mark}间隔
	 * @param item
	 * @param mark
	 * @return
	 */
	public static String extramDecimalAndSplitByMark(String item, String mark) {
		item = item.replaceAll("\\D+", mark);
		if (item.startsWith(mark)) item = item.substring(mark.length());
		if (item.endsWith(mark)) item = item.substring(0, item.length() - mark.length());
		return item;
	}

}
