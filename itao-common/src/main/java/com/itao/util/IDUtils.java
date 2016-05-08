package com.itao.util;

import java.util.Random;

/**
 * 各种id生成策略
 */
public class IDUtils {

	/**
	 * 图片名生成
	 * @return 返回一个基于当前时间的文件名（不包含扩展名）
     */
	public static String generateImageName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		
		return str;
	}

	/**
	 * 生成图片全名
	 * @param oldName 原图片名称
	 * @return 返回与原图相同格式的文件名称
     */
	public static String generateImageName(String oldName){
		if(CommonUtils.notExist(oldName) || !oldName.contains(".")) return generateImageName();
		return generateImageName() + oldName.substring(oldName.lastIndexOf("."));
	}
	
	/**
	 * 商品id生成
	 */
	public static long generateItemId() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		//如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		return Long.parseLong(str);
	}
}
