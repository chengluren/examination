package org.dreamer.examination.utils;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字符串帮助类
 * 
 * @author xwang
 * 
 */
public class StringUtil {

	static public boolean isEmpty(Object _value) {
		if (_value == null)
			return true;
		return isEmpty(_value.toString());
	}

	static public boolean exist(String _oriValue ,String _inStr ) {
		if (isEmpty(_oriValue ) || isEmpty( _inStr ) )
			return false;
		return _oriValue.indexOf( _inStr ) >= 0;
	}
	
	static public String toString(Object _val) {
		if (_val == null)
			return "";
		return _val.toString();
	}

	static public boolean isEmpty(String _value) {
		if (_value != null && !_value.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	static public boolean isEmptyIncludeBlank(String _value) {
		if (_value != null && !_value.isEmpty()) {
			return false;
		}
		return true;
	}

	static public boolean isEqual(String _res1, String _res2) {
		if (_res1 == null && _res2 == null)
			return true;
		if (_res1 == null)
			return false;
		if (_res2 == null)
			return false;
		return _res1.equalsIgnoreCase(_res2);
	}

	/*
	 * NULL=NULL ： true； “”=NULL ： true
	 */
	static public boolean isEqualNullOrEmpty(String _res1, String _res2) {
		if (_res1 == null && _res2 == null)
			return true;
		if (_res1 == null && _res2.isEmpty() || _res2 == null
				&& _res1.isEmpty())
			return true;
		if (_res1 == null || _res2 == null)
			return false;
		return _res1.equalsIgnoreCase(_res2);
	}

	static public boolean translateStringToBoolean(String _resFlage,
			boolean _defaultValue) {
		boolean bFlage = _defaultValue;
		if (StringUtil.isEmpty(_resFlage)) {
			return bFlage;
		}
		if (StringUtil.isEqual(_resFlage.toLowerCase(), "true")) {
			bFlage = true;
		} else if (StringUtil.isEqual(_resFlage.toLowerCase(), "false")) {
			bFlage = false;
		}
		return bFlage;
	}

	static public int toNumeric(String _numStr) {
		int value = 0;
		try {
			Integer integer = Integer.valueOf(_numStr);
			value = integer.intValue();
		} catch (Exception e) {

		}
		return value;
	}

	static public int toNumeric(String _numStr, int _default) {
		int value = _default;
		try {
			Integer integer = Integer.valueOf(_numStr);
			value = integer.intValue();
		} catch (Exception e) {

		}
		return value;
	}

	@SuppressWarnings("unchecked")
	static public String getMapValue(Map<String, ? extends Object> values,
			String keyname, String defaultVal) {
		if (values.containsKey(keyname)) {
			Object value = values.get(keyname);
			if (value == null) {
				return defaultVal;
			} else {
				return value.toString();
			}
		}
		return defaultVal;
	}

	static public String getMapsValue(Map<String, String[]> values,
			String keyname, String defaultVal) {
		if (values.containsKey(keyname)) {
			String[] value = values.get(keyname);
			if (value == null || value.length == 0) {
				return defaultVal;
			} else {
				return value[0];
			}
		}
		return defaultVal;
	}


	public static String replaceCharacter(String _value) {
		_value = _value.replaceAll("“", "\"");
		_value = _value.replaceAll("”", "\"");
		_value = _value.replaceAll("，", ",");
		_value = _value.replaceAll("；", ";");
		_value = _value.replaceAll("（", "(");
		_value = _value.replaceAll("）", ")");
		_value = _value.replaceAll(":", ":");
		return _value;
	}

	public static List<String> splitStringToList(String _value, String _spliter) {
		List<String> wordList = new ArrayList<String>();
		if (StringUtil.isEmpty(_value))
			return wordList;
		String words[] = _value.split(_spliter);
		for (int i = 0; i < words.length; i++) {
			if (!StringUtil.isEmpty(words[i]))
				wordList.add(words[i]);
		}
		return wordList;
	}

	public static String getExceptionStackInfo(Exception _e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			_e.printStackTrace(pw);
			return "\r\n" + sw.toString() + "\r\n";
		} catch (Exception e2) {
			return "bad getExceptionStackInfo";
		}
	}
	
}