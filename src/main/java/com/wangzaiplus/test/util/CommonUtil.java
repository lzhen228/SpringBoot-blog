package com.wangzaiplus.test.util;

import com.wangzaiplus.test.pojo.Talk;
import com.wangzaiplus.test.pojo.TalkJson;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	/**
	 * String转Date
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		Date date = null;
		if (null != str && !"".equals(str)) {
			// yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sdf = new SimpleDateFormat(BaseConstantUtil.TRACK_TIME_FOEMATE_2);
			try {
				// String转Date
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * Date转String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (null != date) {
			// yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sdf = new SimpleDateFormat(BaseConstantUtil.TRACK_TIME_FOEMATE_2);
			return sdf.format(date);
		}
		return null;
	}

	/**
	 * String转Integer
	 * 
	 * @param str
	 * @return
	 */
	private static Integer stringToInteger(String str) {
		Integer integer = null;
		if (null != str && !"".equals(str)) {
			try {
				// String转Integer
				integer = Integer.parseInt(str.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return integer;
	}

	/**
	 * String转Long转Date
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToLongToDate(String str) {
		Date date = null;
		if (null != str && !"".equals(str)) {
			Long longTiem = null;
			try {
				// String转Long
				longTiem = Long.parseLong(str.trim());
				date = new Date(longTiem);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * undefined检查
	 * 
	 * @param str
	 * @return
	 */
	private static String undefinedCheck(String str) {
		if (null != str && !"".equals(str) && !"undefined".equals(str)) {
			return str.trim();
		}
		return "";
	}
	
	public static Talk formatTotTalk(String formItem) {
		Talk talk= null;
		// 如果json不为空
		if (null != formItem && !"".equals(formItem)) {
			TalkJson talkJson = null;
			JSONObject talkJsonObject = JSONObject
					.fromObject(formItem);
			// 先转换为MaillogJson类型
			talkJson = (TalkJson) JSONObject.toBean(talkJsonObject,
					TalkJson.class);
			if (null != talkJson) {
				talk = new Talk();
				talk.setTalk(undefinedCheck(talkJson.getTalk()));
				talk.setHref(undefinedCheck(talkJson.getHead())+undefinedCheck(talkJson.getHref()));
				talk.setToHref(undefinedCheck(talkJson.getToHref()));
				talk.setName(undefinedCheck(talkJson.getName()));
				talk.setToName(undefinedCheck(talkJson.getToName()));
				talk.setTime(dateToString(new Date()));
				talk.setTid(stringToInteger(undefinedCheck(talkJson.getTid()+"")));
				talk.setAid(stringToInteger(undefinedCheck(talkJson.getId()+"")));																
			}
		}
		return talk;
	}

}
