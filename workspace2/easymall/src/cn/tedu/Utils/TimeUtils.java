package cn.tedu.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	public static String getTimeStamp(String pattern){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
