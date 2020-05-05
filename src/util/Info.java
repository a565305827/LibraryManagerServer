package util;


public class Info {

	public static String generalFileName(String srcFileName) {
		try {
			int index = srcFileName.lastIndexOf(".");
			return StrUtil.generalSrid() + srcFileName.substring(index).toLowerCase();
		} catch (Exception e) {
			return StrUtil.generalSrid();
		}
	}
}
