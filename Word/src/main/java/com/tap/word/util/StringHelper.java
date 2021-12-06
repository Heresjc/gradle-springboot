
/**
 * e-tap
 * 字符串工具类
 * Copyright (c) 2015 Shanghai TAP Software Co. Ltd.
 * 
 */
package com.tap.word.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public final class StringHelper {

	/**
	 * 非空字符串转换，如果输入参数是Null默认转换为""
	 * 
	 * @param strIn
	 *            
	 * @return 不为null的字符串
	 */
	public static String null2String(String strIn) {
		return strIn == null ? "" : strIn;
	}

	public static Map string2Map(String strIn, String strDim) {
		Map map = new HashMap();
		List list = string2ArrayList(strIn, strDim);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			map.put(str.substring(0, str.indexOf("=")).trim(), str.substring(str.indexOf("=") + 1).trim());
		}
		return map;
	}

	/**
	 * 将object对象转换为String，如果object值为null默认转换为""
	 * 
	 * @param strIn （Object）

	 * @return 不为null的字符串
	 */
	public static String null2String(Object strIn) {
		return strIn == null ? "" : "" + strIn;
	}

	public static String null2String(Object strIn, String defstr) {
		return strIn == null ? defstr : "" + strIn;
	}
	/**
	 * 空值或空字符串，转为默认值
	 * @param strIn
	 * @param defstr
	 * @return
	 */
	public static String empt2String(Object strIn, String defstr) {
		boolean isEmpty=isEmpty(null2String(strIn));
		return isEmpty==true ? defstr : "" + strIn;
	}

	/**
	 * 按指定的字节数截取字符串（一个中文字符占3个字节，一个英文字符或数字占1个字节）
	 * @param sourceString 源字符串
	 * @param cutBytes 要截取的字节数
	 * @return
	 */
	public static String cutString(String sourceString, int cutBytes)
	{
		if(sourceString == null || "".equals(sourceString.trim()))
		{
			return "";
		}

		int lastIndex = 0;
		boolean stopFlag = false;

		int totalBytes = 0;
		for(int i=0; i<sourceString.length(); i++)
		{
			String s = Integer.toBinaryString(sourceString.charAt(i));
			if(s.length() > 8)
			{
				totalBytes += 3;
			}
			else
			{
				totalBytes += 1;
			}

			if(!stopFlag)
			{
				if(totalBytes == cutBytes)
				{
					lastIndex = i;
					stopFlag = true;
				}
				else if(totalBytes > cutBytes)
				{
					lastIndex = i - 1;
					stopFlag = true;
				}
			}
		}

		if(!stopFlag)
		{
			return sourceString;
		}
		else
		{
			return sourceString.substring(0, lastIndex + 1);
		}
	}

	public static String GBK2ISO(String s) {
		try {
			return new String(s.getBytes("GBK"), "ISO8859_1");
		} catch (UnsupportedEncodingException ex) {
			return null;
		}
	}

	public static String ISO2GBK(String s) {
		try {
			return new String(s.getBytes("ISO8859_1"), "GBK");
		} catch (UnsupportedEncodingException ex) {
			return null;
		}
	}

	public static ArrayList string2ArrayList2(String strIn, String strDim) {
		strIn = null2String(strIn);
		strDim = null2String(strDim);
		ArrayList strList = new ArrayList();

		String[] sr2 = strIn.split(strDim);
		for (int i = 0; i < sr2.length; i++) {
			strList.add(null2String(sr2[i]));
		}
		if (strIn.endsWith(strDim))
			strList.add("");

		return strList;
	}

	/**

	 * 
	 * @param strIn
	 *            ????????

	 * 
	 * @param strDim
	 *            ??????
	 * @return ?????ArrayList
	 */
	public static ArrayList string2ArrayList(String strIn, String strDim) {
		return string2ArrayList(strIn, strDim, false);
	}

	/**

	 * 
	 * @param strIn
	 *            ????????

	 * 
	 * @param strDim
	 *            ??????
	 * @param bReturndim


	 * @return ?????ArrayList
	 */
	public static ArrayList string2ArrayList(String strIn, String strDim, boolean bReturndim) {
		strIn = null2String(strIn);
		strDim = null2String(strDim);
		ArrayList strList = new ArrayList();
		StringTokenizer strtoken = new StringTokenizer(strIn, strDim, bReturndim);
		while (strtoken.hasMoreTokens()) {
			strList.add(strtoken.nextToken());
		}
		return strList;
	}

	/**

	 * 
	 * @param strIn
	 *            ????????

	 * 
	 * @param strDim
	 *            ??????
	 * @return ????????????
	 */
	public static String[] string2Array(String strIn, String strDim) {
		return string2Array(strIn, strDim, false);
	}

	/**


	 * 
	 * @param strIn
	 *            ????????

	 * 
	 * @param strDim
	 *            ??????
	 * @param bReturndim


	 * @return ????????????
	 */
	public static String[] string2Array(String strIn, String strDim, boolean bReturndim) {
		ArrayList strlist = string2ArrayList(strIn, strDim, bReturndim);
		int strcount = strlist.size();
		String[] strarray = new String[strcount];
		for (int i = 0; i < strcount; i++) {
			strarray[i] = (String) strlist.get(i);
		}
		return strarray;
	}

	/**
	 * ??????????????????????
	 * 
	 * @param a
	 *            ?????????????
	 * @param s
	 *            ??????
	 * @return true ????????а??????? false ????????в?????????
	 */
	public static boolean contains(Object a[], Object s) {
		if (a == null || s == null) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null && a[i].equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ?滻??????Щ???
	 * 
	 * @param s
	 *            ???滻?????
	 * @param c1
	 *            ???滻?????
	 * @param c2
	 *            ??4?滻?????
	 * @return ???滻??????

	 * 
	 */
	public static String replaceChar(String s, char c1, char c2) {
		if (s == null) {
			return s;
		}

		char buf[] = s.toCharArray();
		for (int i = 0; i < buf.length; i++) {
			if (buf[i] == c1) {
				buf[i] = c2;
			}
		}
		return String.valueOf(buf);
	}

	/**
	 * ?滻????е??Щ?????
	 * 
	 * @param sou
	 *            ???滻?????
	 * @param s1
	 *            ???滻???????
	 * @param s2
	 *            ??4?滻?????
	 * @return ???滻??????

	 * 
	 */
	// public static String replaceString(String sou, String s1, String s2) {
	// int idx = sou.indexOf(s1);
	// if (idx < 0) {
	// return sou;
	// }
	// return replaceString(sou.substring(0, idx) + s2 +
	// sou.substring(idx + s1.length()), s1, s2);
	//		
	// }

	/**
	 * 替换字符串
	 * @param strSource 
	 * @param strFrom 
	 */
	public static String replaceString(String strSource, String strFrom, String strTo) {
		if (strSource == null) {
			return strSource;
		}
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0 && strTo != null) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	public static String passwordBuilder(String passrule, int length) //随机生成动态密码
	{
		String str = "";
		int prule = Integer.valueOf(passrule).intValue();
		String[] arr = null;
		switch (prule) {
		case 0:
			String[] tmp_arr = { "A", "B", "D", "E", "F", "G", "H", "J", "L", "M", "N", "Q", "R", "T", "Y", "a", "d",
					"e", "f", "g", "h", "i", "j", "m", "n", "r", "t", "u", "y" }; //大写小写
			arr = tmp_arr;
			break;
		case 1:
			String[] tmp_arr1 = { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "D", "E", "F", "G", "H", "J", "L",
					"M", "N", "Q", "R", "T", "Y" }; //大写数字
			arr = tmp_arr1;
			break;
		case 2:
			String[] tmp_arr2 = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "d", "e", "f", "g", "h", "i", "j", "m",
					"n", "r", "t", "u", "y" }; //小写数字
			arr = tmp_arr2;
			break;
		case 3:
			String[] tmp_arr3 = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "d", "e", "f", "g", "h", "i", "j", "m",
					"n", "r", "t", "u", "y", "A", "B", "D", "E", "F", "G", "H", "J", "L", "M", "N", "Q", "R", "T", "Y" }; //大写小写数字
			arr = tmp_arr3;
			break;
		case 4:
			String[] tmp_arr4 = { "A", "B", "D", "E", "F", "G", "H", "J", "L", "M", "N", "Q", "R", "T", "Y" }; //大写数字
			arr = tmp_arr4;
			break;
		case 5:
			String[] tmp_arr5 = { "a", "d", "e", "f", "g", "h", "i", "j", "m", "n", "r", "t", "u", "y" }; //小写数字
			arr = tmp_arr5;
			break;
		case 6:
			String[] tmp_arr6 = { "2", "3", "4", "5", "6", "7", "8", "9", }; //大写小写数字
			arr = tmp_arr6;
			break;
		default:
			String[] tmp_arrd = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "d", "e", "f", "g", "h", "i", "j", "m",
					"n", "r", "t", "u", "y" }; //大写小写数字
			arr = tmp_arrd;
			break;
		}

		Random rd = new Random();
		while (str.length() < length) {

			String temp = arr[rd.nextInt(arr.length)];
			if (str.indexOf(temp) == -1) {
				str += temp;
			}
		}
		return str;
	}

	/**
	 * ?滻????е?????????,??????????滻?????

	 * 
	 * @param sou
	 *            ???滻?????
	 * @param s1
	 *            ???滻???????
	 * @param s2
	 *            ??4?滻?????
	 * @return ???滻??????

	 * 
	 */
	public static String replaceStringFirst(String sou, String s1, String s2) {
		int idx = sou.indexOf(s1);
		if (idx < 0) {
			return sou;
		}
		return sou.substring(0, idx) + s2 + sou.substring(idx + s1.length());
	}

	/**
	 * ?滻??????}??????м???????

	 * 
	 * ????: StringHelper.replaceRange("this is a test sentense,Look at
	 * this!","test","look ","stone!",false) ???? this is a stone!at this!
	 * StringHelper.replaceRange("this is a test sentense,Look at
	 * this!","test","look ","stone!",true) ???? this is a stone! sentense,Look
	 * at this!
	 * 
	 * @param sentence
	 *            ???滻?????
	 * @param oStart
	 *            ??????
	 * @param oEnd
	 *            ???????
	 * @param rWord
	 *            ??4?滻?????
	 * @param matchCase
	 *            ???????Сд

	 * 
	 * true ????Сд

	 * 
	 * false ??????Сд

	 * 
	 * @return ???滻??????

	 * 
	 */
	public static String replaceRange(String sentence, String oStart, String oEnd, String rWord, boolean matchCase) {
		int sIndex = -1;
		int eIndex = -1;
		if (matchCase) {
			sIndex = sentence.indexOf(oStart);
		} else {
			sIndex = sentence.toLowerCase().indexOf(oStart.toLowerCase());
		}
		if (sIndex == -1 || sentence == null || oStart == null || oEnd == null || rWord == null) {
			return sentence;
		} else {
			if (matchCase) {
				eIndex = sentence.indexOf(oEnd, sIndex);
			} else {
				eIndex = sentence.toLowerCase().indexOf(oEnd.toLowerCase(), sIndex);
			}
			String newStr = null;
			if (eIndex > -1) {
				newStr = sentence.substring(0, sIndex) + rWord + sentence.substring(eIndex + oEnd.length());
			} else {
				newStr = sentence.substring(0, sIndex) + rWord + sentence.substring(sIndex + oStart.length());
			}
			return replaceRange(newStr, oStart, oEnd, rWord, matchCase);
		}
	}

	// Empty checks
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 *  StringUtils.isEmpty(null)      = true
	 *  StringUtils.isEmpty(&quot;&quot;)        = true
	 *  StringUtils.isEmpty(&quot; &quot;)       = false
	 *  StringUtils.isEmpty(&quot;bob&quot;)     = false
	 *  StringUtils.isEmpty(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * String. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equalsIgnoreCase("null") || str.length() == 0;
	}
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }


	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	public static String trimToNull(Object str) {
		if (str == null)
			return null;
		return trimToNull((String) str);
	}


	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	public static boolean parseBoolean(String param) {
		if (isEmpty(param)) {
			return false;
		}
		switch (param.charAt(0)) {
		case '1':
		case 'y':
		case 'Y':
		case 't':
		case 'T':
			return true;
		}
		return false;
	}

	public static String getRandomStr(int length) {
		String psd = "";
		char c;
		int i;
		int isnum = 0;
		for (int j = 0; j < length; j++) {
			if (isnum == 0) {
				isnum = 1;
				c = (char) (Math.random() * 26 + 'a');
				psd += c;
			} else {
				isnum = 0;
				c = (char) (Math.random() * 10 + '0');
				psd += c;
			}
		}
		return psd;
	}

	public static String fromDB(String s) {
		char c[] = s.toCharArray();
		char ch;
		int i = 0;
		StringBuffer buf = new StringBuffer();

		while (i < c.length) {
			ch = c[i++];
			if (ch == '\"') {
				buf.append("\\\"");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	public static String Array2String(Object[] strIn, String strDim) {
		StringBuffer str = new StringBuffer();
		int len = strIn.length;
		for (int i = 0; i < len; i++) {
			str.append((i == 0) ? strIn[i] : strDim + strIn[i]);
		}
		return str.toString();
	}

	public static String Array2String(String[] strIn, String strDim) {
		StringBuffer strOut = new StringBuffer();
		for (String tempStr : strIn) {
			strOut.append(tempStr).append(strDim);
		}
		if (strOut.length() > 0)
			strOut.delete(strOut.lastIndexOf(strDim), strOut.length());
		return strOut.toString();
	}

	public static String ArrayList2String(ArrayList strIn, String strDim) {
		return List2String(strIn, strDim);
	}

	public static String List2String(List strIn, String strDim) {
		StringBuffer strOut = new StringBuffer();
		for (Object o : strIn) {
			strOut.append(o.toString()).append(strDim);
		}
		if (strOut.length() > 0)
			strOut.delete(strOut.lastIndexOf(strDim), strOut.length());
		return strOut.toString();
	}

	public static String fillValuesToString(String str, Hashtable ht) {
		return fillValuesToString(str, ht, '$');
	}

	public static String fillValuesToString(String str, Hashtable ht, char VARIABLE_PREFIX) {

		char TERMINATOR = '\\';

		if (str == null || str.length() == 0 || ht == null) {
			return str;
		}

		char s[] = str.toCharArray();
		char ch;
		int i = 0;
		String vname;
		StringBuffer buf = new StringBuffer();

		ch = s[i];
		while (true) {
			if (ch == VARIABLE_PREFIX) {
				vname = "";
				if (++i < s.length) {
					ch = s[i];
				} else {
					break;
				}
				while (true) {
					if (ch != '_' && ch != '-' && !Character.isLetterOrDigit(ch)) {
						break;
					}
					vname += ch;
					if (++i < s.length) {
						ch = s[i];
					} else {
						break;
					}
				}

				if (vname.length() != 0) {
					String vval = (String) ht.get(vname);
					if (vval != null) {
						buf.append(vval);
					}
				}
				if (vname.length() != 0 && ch == VARIABLE_PREFIX) {
					continue;
				}
				if (ch == TERMINATOR) {
					if (++i < s.length) {
						ch = s[i];
					} else {
						break;
					}
					continue;
				}
				if (i >= s.length) {
					break;
				}
			}

			buf.append(ch);
			if (++i < s.length) {
				ch = s[i];
			} else {
				break;
			}
		}
		return buf.toString();
	}

	public static String formatMutiIDs(String ids) {
		String ret = "";
		ArrayList arrayids = string2ArrayList(ids, ",");
		for (int i = 0; i < arrayids.size(); i++) {
			String _id = null2String(String.valueOf(arrayids.get(i))).trim();
			if (StringHelper.isEmpty(_id))
				continue;
			//if (isID(_id)) {
				ret += ",'" + _id + "'";
			//}
		}
		if (!ret.equals(""))
			return ret.substring(1);
		else
			return "''";
	}

	public static String formatMutiIDs(List<String> idList){
		String ret = "";
		for (int i = 0; i < idList.size(); i++) {
			String _id = null2String(String.valueOf(idList.get(i))).trim();
			if (StringHelper.isEmpty(_id))
				continue;
			ret += ",'" + _id + "'";
		}
		if (!ret.equals(""))
			return ret.substring(1);
		else
			return "''";
	}


	public static String lift(String arg, int length) {
		int sLength = arg.trim().length();
		if (length < 1 || length > sLength) {
			return arg.trim();
		} else {
			return arg.trim().substring(0, length);
		}

	}

	public static String getDecodeStr(String strIn) {
		if (strIn == null)
			return "";
		String strTemp = "";
		for (int i = 0; i < strIn.length(); i++) {
			char charTemp = strIn.charAt(i);
			switch (charTemp) {
			case 124: // '~'
				String strTemp2 = strIn.substring(i + 1, i + 3);
				strTemp = strTemp + (char) Integer.parseInt(strTemp2, 16);
				i += 2;
				break;

			case 94: // '^'
				String strTemp3 = strIn.substring(i + 1, i + 5);
				strTemp = strTemp + (char) Integer.parseInt(strTemp3, 16);
				i += 4;
				break;

			default:
				strTemp = strTemp + charTemp;
				break;
			}
		}

		return strTemp;
	}

	public static String getEncodeStr(String strIn) {
		if (strIn == null)
			return "";
		String strOut = "";
		for (int i = 0; i < strIn.length(); i++) {
			int iTemp = strIn.charAt(i);
			if (iTemp > 255) {
				String strTemp2 = Integer.toString(iTemp, 16);
				for (int iTemp2 = strTemp2.length(); iTemp2 < 4; iTemp2++)
					strTemp2 = "0" + strTemp2;

				strOut = strOut + "^" + strTemp2;
			} else {
				if (iTemp < 48 || iTemp > 57 && iTemp < 65 || iTemp > 90 && iTemp < 97 || iTemp > 122) {
					String strTemp2 = Integer.toString(iTemp, 16);
					for (int iTemp2 = strTemp2.length(); iTemp2 < 2; iTemp2++)
						strTemp2 = "0" + strTemp2;

					strOut = strOut + "|" + strTemp2;
				} else {
					strOut = strOut + strIn.charAt(i);
				}
			}
		}

		return URLEncoder.encode(strOut);
	}


	public static String getfloatToString(String value) {
		int index = value.indexOf("E");
		if (index == -1) {
			return value;
		}

		int num = Integer.parseInt(value.substring(index + 1, value.length()));
		value = value.substring(0, index);
		index = value.indexOf(".");
		value = value.substring(0, index) + value.substring(index + 1, value.length());
		String number = value;
		if (value.length() <= num) {
			for (int i = 0; i < num - value.length(); i++) {
				number += "0";
			}
		} else {
			number = number.substring(0, num + 1) + "." + number.substring(num + 1) + "0";
		}
		return number;
	}

	public static String getfloatToString2(String value) { //保留两位小数
		value = getfloatToString(value);
		int index = value.indexOf(".");
		if (index == -1)
			return value;
		String value1 = value.substring(0, index);
		String value2 = value.substring(index + 1, value.length()) + "00";
		if (Integer.parseInt(value2.substring(0, 2)) == 0)
			return value1;
		else
			return value1 + "." + value2.substring(0, 2);
	}


	public static String numberFormat2(double value) { //保留两位小数
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(value);
	}

	public static String getRequestHost(HttpServletRequest request) {
		if (request == null) {
			return "";
		} else {//获取服务器名（域名或IP地址）和端口号
			String serverHost = request.getServerName()+":"+request.getServerPort();
			return (null2String(serverHost)).trim(); 
		}
	}

	// 以 java 实现 escape  unescape 
	private final static String[] hex = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C",
			"0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E",
			"1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30",
			"31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42",
			"43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54",
			"55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66",
			"67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77", "78",
			"79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A",
			"8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C",
			"9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE",
			"AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
			"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1", "D2",
			"D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4",
			"E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6",
			"F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
	private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00,
			0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B,
			0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
			0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

	public static String escape(String s) {
		if (isEmpty(s))
			return "";
		StringBuffer sbuf = new StringBuffer();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			int ch = s.charAt(i);
			if (ch == ' ') { // space : map to '+' 
				sbuf.append('+');
			} else if ('A' <= ch && ch <= 'Z') { // 'A'..'Z' : as it was
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') { // 'a'..'z' : as it was
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') { // '0'..'9' : as it was
				sbuf.append((char) ch);
			} else if (ch == '-' || ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
				sbuf.append((char) ch);
			} else if (ch <= 0x007F) { // other ASCII : map to %XX
				sbuf.append('%');
				sbuf.append(hex[ch]);
			} else { // unicode : map to %uXXXX
				sbuf.append('%');
				sbuf.append('u');
				sbuf.append(hex[(ch >>> 8)]);
				sbuf.append(hex[(0x00FF & ch)]);
			}
		}
		return sbuf.toString();
	}

	public static String unescape(String s) {
		if (isEmpty(s))
			return "";
		StringBuffer sbuf = new StringBuffer();
		int i = 0;
		int len = s.length();
		while (i < len) {
			int ch = s.charAt(i);
			if (ch == '+') { // + : map to ' ' 
				sbuf.append(' ');
			} else if ('A' <= ch && ch <= 'Z') { // 'A'..'Z' : as it was
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') { // 'a'..'z' : as it was
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') { // '0'..'9' : as it was
				sbuf.append((char) ch);
			} else if (ch == '-' || ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')') {
				sbuf.append((char) ch);
			} else if (ch == '%') {
				int cint = 0;
				if ('u' != s.charAt(i + 1)) { // %XX : map to ascii(XX)
					cint = (cint << 4) | val[s.charAt(i + 1)];
					cint = (cint << 4) | val[s.charAt(i + 2)];
					i += 2;
				} else { // %uXXXX : map to unicode(XXXX)
					cint = (cint << 4) | val[s.charAt(i + 2)];
					cint = (cint << 4) | val[s.charAt(i + 3)];
					cint = (cint << 4) | val[s.charAt(i + 4)];
					cint = (cint << 4) | val[s.charAt(i + 5)];
					i += 5;
				}
				sbuf.append((char) cint);
			}
			i++;
		}
		return sbuf.toString();
	}

	/**
	 * 转换第一个字母为大写
	 * */
	public static String changeFirstLetter(String string) {
		if (string == null) {
			return null;
		} else {
			String c = string.substring(0, 1);
			String d = c.toUpperCase();
			String returnString = string.replaceFirst(c, d);
			return returnString;
		}

	}

	/**
	 * 替换空格、回车、换行
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * Converts some important chars (int) to the corresponding html string
	 */
	public static String conv2Html(int i) {
		if (i == '&')
			return "&amp;";
		else if (i == '<')
			return "&lt;";
		else if (i == '>')
			return "&gt;";
		else if (i == '"')
			return "&quot;";
		else
			return "" + (char) i;
	}

	/**
	 * Converts a normal string to a html conform string
	 */
	public static String conv2Html(String st) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < st.length(); i++) {
			buf.append(conv2Html(st.charAt(i)));
		}
	    //return st;
		return buf.toString();
	}

	/**
	 * 将字符转义
	 * @param i 转义前的字符
	 * @return 转义后的字符串
	 */
	public static String convert2SQL(int i) {
		switch(i) {
			case '&': return "&amp;";
			case '<': return "&lt;";
			case '>': return "&gt;";
			case '\'': return "&#x27;";
			case '"': return "&quot";
			default: return "" + (char)i;
		}
	}

	/**
	 *
	 * @param sql 未转义的SQL字符串，例如：select schoolname || ',' ||
	 * @return 转义后的SQL字符串
	 */
	public static String convert2SQL(String sql) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < sql.length(); i++) {
			sb.append(convert2SQL(sql.charAt(i)));
		}
		return sb.toString();
	}

	/**
	 * 移除开头，结尾，或中间的逗号
	 * @param oldstationids
	 * @return
	 */
	public static String removeComma(String oldstationids) {
		if (!isEmpty(oldstationids)) {
			if (oldstationids.indexOf(",,") != -1) {
				return oldstationids.replace(",,", ",");
			}
			if (oldstationids.lastIndexOf(",") == oldstationids.length()) {
				oldstationids = oldstationids.substring(0, oldstationids.lastIndexOf(","));
			}
			if (oldstationids.indexOf(",") == 1) {
				return oldstationids.substring(1);
			}
		}
		return oldstationids;
	}


	/**
	 * 将string中的回车、换行、空格转化为html代码
	 * @param sStr
	 * @return
	 */
	public static String convertHtmlString(String sStr) {
		if (sStr == null || sStr.equals("")) {
			return sStr;
		}

		StringBuffer sTmp = new StringBuffer();
		int i = 0;
		while (i <= sStr.length() - 1) {
			//			if (sStr.charAt(i) == '\n' || sStr.charAt(i) == '\r') {
			if (sStr.charAt(i) == '\n') {
				sTmp = sTmp.append("<br>");
			} else if (sStr.charAt(i) == ' ') {
				sTmp = sTmp.append("&nbsp;");
			} else {
				sTmp = sTmp.append(sStr.substring(i, i + 1));
			}
			i++;
		}
		return sTmp.toString();
	}

	/**
	 * jstr中的JS关键字符转义，以便直接输出为js脚本内容。
	 * @param jstr as String
	 * @return String //a="a\\bc";转换后 a=\"a\\\\bc\"
	 */
	public static String filterJString(String jstr) {
		jstr = jstr.replaceAll("\\\\", "\\\\");
		jstr = jstr.replaceAll("\"", "\\\\\"");
		return jstr;
	}

	public static String filterJString2(String jstr) {
		if (jstr == null)
			return "";
		jstr = jstr.replaceAll("\\\\", "/");
		jstr = jstr.replaceAll("'", "");
		jstr = jstr.replaceAll("\"", "");
		return jstr;
	}

	/** ${var} */
	public final static String REGEXP_VAR1 = "\\$\\{[\\w: \\.\\*\\(\\)]+\\}";
	/** {var} */
	public final static String REGEXP_VAR2 = "\\{[\\w: \\*\\.\\(\\)]+\\}";
	/** Ognl express variate #var */
	public final static String REGEXP_VAR_OGNL = "#[\\w\\u2E80-\\u9FFF]+";


	public static List<String> parseTemplateVar(String sTemp, String regExp) {
		List<String> fieldList = new ArrayList<String>();
		if (sTemp == null)
			return fieldList;
		if (regExp == null)
			regExp = REGEXP_VAR1;
		int pos = 0;
		if (regExp.equalsIgnoreCase(REGEXP_VAR1))
			pos = 2;
		else if (regExp.equalsIgnoreCase(REGEXP_VAR2))
			pos = 1;

		//求模板内的字段名
		fieldList = new ArrayList<String>();
		Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sTemp);
		String tmp = null;
		if (m.find()) {
			do {
				tmp = sTemp.substring(m.start(), m.end());
				if (pos > 0)
					tmp = tmp.substring(pos, tmp.length() - 1);
				fieldList.add(tmp);
			} while (m.find());
		}//else System.out.println("no found!");//end if.
		return fieldList;
	}

	/**
	 * 过滤字符串中的正则表达式关键词。
	 * @param sText as String
	 * @return String
	 */
	public static String filterRegexpChar(String sText) {
		int size = sText.length();
		StringBuffer s = new StringBuffer();
		char ch = 0;
		for (int i = 0; i < size; i++) {
			ch = sText.charAt(i);
			switch (ch) {
			case '\\':
			case '^':
			case '$':
			case '*':
			case '(':
			case ')':
			case '[':
			case ']':
			case '{':
			case '}':
			case '+':
			case '?':
			case '.':
				s.append("\\" + ch);
				break;
			default:
				s.append(ch);
			}
		}
		return s.toString();
	}

	public static String translateVelocity(String s) {
		s = s.replaceAll("<w:(\\w+)>([^<]*)</w:\\w+>", "#$1$2");
		return s;
	}


	/**
	 *当原字符串位数不足时，用补足字符在字符串前补足指定的位数
	 */
	public static String fillString(String str, int length, char c) {
		while (str.length() < length) {
			str += c;
		}
		return str;
	}
	
	/**
	 *当原字符串位数不足时，用补足字符在字符串左边补足指定的位数
	 */
	public static String fillLeftString(String str, int length, char c) {
		while (str.length() < length) {
			str = c+str;
		}
		return str;
	}
	
	/**
	 *判断字符串是否为数字
	 */
	public static boolean isNumeric(String str){
		  Pattern pattern = Pattern.compile("[0-9]*");
		  return pattern.matcher(str).matches();
	}
	
	
	
	/**
	 * 去掉所有html标签元素
	 * @param input
	 * @return
	 */
    public static String removeHTMLFromString(String input) {   
    	   if (input == null || input.trim().equals("")) {   
	           return "";   
	       }   
	       // 去掉所有html元素,   
	       String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");   
	       str = str.replaceAll("[(/>)<]", "");   
	       return str;   
	}
    
    public static String[] TokenizerString2(String str, String dim) {
        return TokenizerString2(str, dim, false);
    }

    public static String[] TokenizerString2(String str, String dim, boolean returndim) {
        ArrayList strlist = TokenizerString(str, dim, returndim);
        int strcount = strlist.size();
        String[] strarray = new String[strcount];
        for (int i = 0; i < strcount; i++) {
            strarray[i] = (String) strlist.get(i);
        }
        return strarray;
    }
    
    public static ArrayList TokenizerString(String str, String dim) {
        return TokenizerString(str, dim, false);
    }

    public static ArrayList TokenizerString(String str, String dim, boolean returndim) {
        str = null2String(str);
        dim = null2String(dim);
        ArrayList strlist = new ArrayList();
        StringTokenizer strtoken = new StringTokenizer(str, dim, returndim);
        while (strtoken.hasMoreTokens()) {
            strlist.add(strtoken.nextToken());
        }
        return strlist;
    }
    
    public static String toHtml(String s,boolean f) {
        s=StringReplace(s,"<br>",""+'\n');  
        char c[] = s.toCharArray();
        char ch;
        int i = 0;
        StringBuffer buf = new StringBuffer();

        while (i < c.length) {
            ch = c[i++];

            //以下注释 TD2432 关于字段中含有单引号的问题 董平 2005-11-11 (光棍节 呵呵~),重新加入，处理插入数据插入 by ben 2005-12-29
            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            // 刘煜增加针对英文双引号的处理
            else if (ch == '"')
                buf.append("&quot;");
            else if (ch == '\n')
                buf.append("<br>");
            else
            	buf.append(ch);
        }
        return buf.toString();
    }
    
    public static String StringReplace(String sou, String s1, String s2) {
        sou = null2String(sou);
        s1 = null2String(s1);
        s2 = null2String(s2);
        try{
            sou = sou.replace(s1, s2);
        }catch(Exception e){
            e.printStackTrace();//将未知异常打印出来，便于检查错误。
        }
        return sou; 
    }
    
    public static char getSeparator() {
        return 2;
    }

    public static String convertToCommaSeparatedList(Collection<String> items){
    	StringBuilder output = new StringBuilder();
    	for (String item:items){
    		output.append(item + ",");
    	}
    	if (output.length() != 0){
    		output.deleteCharAt(output.length() - 1);
    	}
    	return output.toString();
	}
    
    /**
     * 特殊符号转义 目前包括英文状态下的单引号，双引号，分号，右斜线
     * &#39;也收入此转义方法了，非标准编码，系统里为了保存单引号设置的
     * @param str 
     * @return
     */
    public static String convertToUnicode(String str){
		if(str==null || str.equals("")){
			return str;
		}
		str.replaceAll("'","")
		   .replaceAll("'","");
    	str = StringHelper.StringReplace(str, "\\", "\\u005c");//右斜线
    	str = StringHelper.StringReplace(str, "&quot;", "\\u0022");//双引号
    	str = StringHelper.StringReplace(str, "&#34;", "\\u0022");//双引号
    	str = StringHelper.StringReplace(str, "&#39;", "\\u0027");//单引号，非标准编码，系统里为了保存单引号设置的
    	str = StringHelper.StringReplace(str, ";", "\\u003b");//分号
    	str = StringHelper.StringReplace(str, "'", "\\u0027");//单引号
    	str = StringHelper.StringReplace(str, "\"", "\\u0022");//双引号
    	
    	//附unicode编码表地址http://zh.wikipedia.org/wiki/Unicode%E5%AD%97%E7%AC%A6%E5%88%97%E8%A1%A8
    	
    	return str;
    }
    
    public static String convertToCommonCode(String str){
    	str = StringHelper.StringReplace(str, "&quot;", "\"");//双引号
    	
    	//附unicode编码表地址http://zh.wikipedia.org/wiki/Unicode%E5%AD%97%E7%AC%A6%E5%88%97%E8%A1%A8
    	
    	return str;
    }

	public static String convert2CommonCode(String str){
		str = StringHelper.StringReplace(str, "&quot;", "\"");//双引号
		str = StringHelper.StringReplace(str, "&#39;", "\'");//单引号
		return str;
	}
    
    /**
     * @param  str 指定字符串
     * @return 得到字符串的字节数
     */
    public static int length2(String str){        
        return str.getBytes().length;
    }
    /**
     * @param  str 指定字符串长度(非位数)
    * @param length 指定长度
    * @param strMore more符号
     * @return 带more号的字符串
     */
   public static String getMoreStr(String str,int length,String strMore){
        String moreStr="";
        if (length<=0)   return moreStr;
        int byteLength=length*2;
        if(length2(str)<=byteLength)  return str;

        int num=0;
        for (int i=0;i<str.length();i++){
           int chr=str.charAt(i);
           if (chr>=0x00 && chr<=0xff)    num+=1; //表明不为汉字
           else num+=2;
           moreStr+=(char)chr;
           if(num>=byteLength) break;
        }
       moreStr+=strMore;
        return moreStr;
    }

    /**
     * 校验输入的字符串是否包含SQL注入漏洞
     * @param str
     * @return
     */
    public static boolean sqlValidate(String str) {
        //统一转为小写
        str = str.toLowerCase();
//        String badStr = "'|select|update|and|or|delete|insert|truncate|char|into|iframe|href|script|activex|html|flash"
//                + "|substr|declare|exec|master|drop|execute|"
//                + "union|;|--|+|,|like|%|#|*|<|>|$|\"|<|>|(|)";//过滤掉的sql关键字，可以手动添加
        String badStr = "select|update|delete|insert|truncate|"
                + "union|;|--|+|,|like|%|#|*|<|>|$|\"|'|<|>|(|)";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * 如果字符串是<code>null</code>或者&quot;&quot;，则返回指定默认字符串，否则返回字符串本身。
     *
     * <pre>
     * emptyToDefault(null, &quot;default&quot;)  = &quot;default&quot;
     * emptyToDefault(&quot;&quot;, &quot;default&quot;)    = &quot;default&quot;
     * emptyToDefault(&quot;  &quot;, &quot;default&quot;)  = &quot;  &quot;
     * emptyToDefault(&quot;bat&quot;, &quot;default&quot;) = &quot;bat&quot;
     * </pre>
     *
     * @param str 要转换的字符串
     * @param defaultStr 默认字符串
     *
     * @return 字符串本身或指定的默认字符串
     * @since 4.1.0
     */
    public static String emptyToDefault(CharSequence str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str.toString();
    }

}
