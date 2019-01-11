package com.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import net.sf.json.JSONArray;

import org.apache.commons.codec.binary.Hex;
/**
 * 
     * Title: StringUtils.java    
     * Description:  字符串工具类
     * @author xucs    
     * Company: 江苏增宇信息科技开发有限公司  
     * Copyright: Copyright (c) 2016
     * @created 2016-4-11 下午6:56:33 
     * @update 2016-4-11 下午6:56:33 
     * @version 1.0
 */
@SuppressWarnings({"static-access","rawtypes","unchecked","deprecation"})
public class StringUtils {
	enum CharacterFlag{
		Lower, Upper;
	}
	
	
	private static Object initLock = new Object();

	private static int[] m_Seed = new int[256];
	/**
	 * 
	     * @discription 根据密码（种子）字符串初始化加密种子数组 ' 数组有 256 个元素，各元素取值从 0 到 255，无重复值
	     * @author xucs       
	     * @created 2016-4-12 上午8:39:24  
	     * @update 2016-4-12 上午8:39:24   
	     * @param strPassword 为输入的密码（种子）
	 */
	private static void InitRC4Encryption(String strPassword) {
		/***********************************************************************
		 * ' 根据密码（种子）字符串初始化加密种子数组 ' 数组有 256 个元素，各元素取值从 0 到 255，无重复值 '
		 * strPassword 为输入的密码（种子）
		 **********************************************************************/
		int i = 0, j = 0, lngA = 0;
		int[] aSeed = new int[256];
		if (strPassword == null || strPassword.length() == 0) {
			strPassword = "*";
		}
		@SuppressWarnings("unused")
		String tem_pas = "";
		byte[] bytes = new byte[0];

		// 将 Unicode 字符串处理成 ANSI 字符串，使密码可以接受汉字
		try {
			// strPassword = new String(strPassword.getBytes(), "UTF-16LE");
			// System.out.println(strPassword);
			bytes = strPassword.getBytes();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// strPassword = StrConv(strPassword, vbFromUnicode);
		// 生成密码数组序列。例如密码为 "12345"，数组序列为 "1234512345..."

		lngA = bytes.length;
		j = -1;
		for (i = 0; i <= 255; i++) {
			j = j + 1;
			if (j >= lngA) {
				j = 0;
			}
			if (bytes[j] < 0) {
				aSeed[i] = 256 + bytes[j];
			} else {
				aSeed[i] = bytes[j];
			}
		}

		// 种子数组序列各元素的初始值为 0 到 255 依次递增
		for (i = 0; i <= 255; i++) {
			m_Seed[i] = i;
		}

		// 根据密码数组序列重新排列种子数组序列的元素
		j = 0;
		for (i = 0; i <= 255; i++) {
			j = (j + m_Seed[i] + aSeed[i]) % 256;
			// 交换 m_Seed(i) 和 m_Seed(j) 的值
			lngA = m_Seed[i];
			m_Seed[i] = m_Seed[j];
			m_Seed[j] = lngA;
		}
	}
	/**
	 * 
	     * @discription RC4 加密/解密函数 ' 输入 Byte 数组，输出 Unicode 字符串
	     * @author xucs       
	     * @created 2016-4-12 上午8:40:03  
	     * @update 2016-4-12 上午8:40:03   
	     * @param strPassword 加密的串
	     * @param bytIn 加密数组 0-255之间
	     * @return 加密结果
	 */
	public static String RC4EncryptionBS(String strPassword, byte[] bytIn) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Byte 数组，输出 Unicode 字符串 '
		 **********************************************************************/
		// return StrConv(RC4EncryptionByte(strPassword, bytIn), vbUnicode);
		return String.valueOf(RC4EncryptionByte(strPassword, bytIn));
	}

	/**
	 * 
	     * @discription RC4 加密/解密函数 ' 输入 Byte 数组，输出 Unicode 字符串
	     * @author xucs       
	     * @created 2016-4-12 上午8:41:04  
	     * @update 2016-4-12 上午8:41:04   
	     * @param strPassword 加密的串
	     * @param bytIn 加密数组 0-255之间
	     * @return 加密结果
	 */
	public static String EncryptionBS(String strPassword, byte[] bytIn) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Byte 数组，输出 Unicode 字符串 '
		 **********************************************************************/
		// return StrConv(RC4EncryptionByte(strPassword, bytIn), vbUnicode);
		int[] intEncryResult = new int[0];
		String strEncryResult = "";
		try {
			intEncryResult = StringUtils.RC4EncryptionByte(strPassword, bytIn);
			strEncryResult = "";
			for (int j = 0; j < intEncryResult.length; j++) {
				strEncryResult = strEncryResult + (char) intEncryResult[j];
			}
			return StringUtils.encodeBase64(
					strEncryResult.getBytes("ISO-8859-1")).trim();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	     * @discription RC4 加密/解密函数 ' 输入 Byte 数组，输出 Byte 数组
	     * @author xucs       
	     * @created 2016-4-12 上午8:41:32  
	     * @update 2016-4-12 上午8:41:32   
	     * @param strPassword 加密的串
	     * @param bytIn 加密数组 0-255之间
	     * @return  原文与加密种子数组的元素异或，生成密文
	 */
	public static int[] RC4EncryptionByte(String strPassword, byte bytIn[]) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Byte 数组，输出 Byte 数组 '
		 **********************************************************************/

		int lngLength = 0;
		int lngA = 0, i, j, k;
		int[] aOut = new int[0];
		lngLength = bytIn.length;
		int[] intofByte = new int[lngLength];

		for (i = 0; i < bytIn.length; i++) {
			if (bytIn[i] < 0) {
				intofByte[i] = (256 + bytIn[i]);
			} else {
				intofByte[i] = bytIn[i];
			}
		}

		if (lngLength > 0) {
			aOut = new int[lngLength];
			InitRC4Encryption(strPassword);
			j = 0;
			k = 0;
			for (i = 1; i <= lngLength; i++) {
				j = (j + 1) % 256;
				k = (k + m_Seed[j]) % 256;
				// 交换 m_Seed(j) 和 m_Seed(k) 的值
				lngA = m_Seed[j];
				m_Seed[j] = m_Seed[k];
				m_Seed[k] = lngA;
				// 原文与加密种子数组的元素异或，生成密文
				// System.out.println(bytIn[i - 1] ^ m_Seed[(m_Seed[j] +
				// m_Seed[k]) % 256]);
				aOut[i - 1] = intofByte[i - 1]
						^ m_Seed[(m_Seed[j] + m_Seed[k]) % 256];
			}
		}
		return aOut;
	}

	

	/**
	 * 
	     * @discription  RC4 加密/解密函数 ' 输入 Unicode 字符串，输出 Byte 数组 
	     * @author xucs       
	     * @created 2016-4-12 上午8:43:39  
	     * @update 2016-4-12 上午8:43:39   
	     * @param strPassword 加密的串
	     * @param strIn 原文与加密种子数组的元素异或，生成密文
	     * @return 加密数组 0-255之间
	 */
	public static byte[] RC4EncryptionString(String strPassword, String strIn) {
		/***********************************************************************
		 * ' RC4 加密/解密函数 ' 输入 Unicode 字符串，输出 Byte 数组 '
		 **********************************************************************/
		int lngLength, lngA, i, j, k;
		byte[] aOut = new byte[0];
		strIn = "";
		try {
			// strIn = StrConv(strIn, vbFromUnicode);
			try {
				strIn = new String(strIn.getBytes(), "UTF-16LE");
			} catch (Exception e) {
				e.printStackTrace();
			}
			lngLength = strIn.length();
			if (lngLength > 0) {
				aOut = new byte[lngLength];
				InitRC4Encryption(strPassword);
				j = 0;
				k = 0;
				for (i = 1; i < lngLength; i++) {
					j = (j + 1) % 256;
					k = (k + m_Seed[j]) % 256;
					// 交换 m_Seed(j) 和 m_Seed(k) 的值
					lngA = m_Seed[j];
					m_Seed[j] = m_Seed[k];
					m_Seed[k] = lngA;
					// 原文与加密种子数组的元素异或，生成密文
					aOut[i - 1] = (byte) (strIn.charAt(i) ^ m_Seed[(m_Seed[j] + m_Seed[k]) % 256]);
					// System.out.print(aOut[i - 1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return aOut;
	}

	
	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;

	/**
	 * Hashes a String using the Md5 algorithm and returns the result as a
	 * String of hexadecimal numbers. This method is synchronized to avoid
	 * excessive MessageDigest object creation. If calling this method becomes a
	 * bottleneck in your code, you may wish to maintain a pool of MessageDigest
	 * objects instead of using this method. <p/> A hash is a one-way function --
	 * that is, given an input, an output is easily computed. However, given the
	 * output, the input is almost impossible to compute. This is useful for
	 * passwords since we can store the hash and a hacker will then have a very
	 * hard time determining the original password. <p/> take their plain text
	 * password, compute the hash, and compare the generated hash to the stored
	 * hash. Since it is almost impossible that two passwords will generate the
	 * same hash, we know if the user gave us the correct password or not. The
	 * only negative to this system is that password recovery is basically
	 * impossible. Therefore, a reset password method is used instead.
	 * 
	 * @param data
	 *            the String to compute the hash of.
	 * @return a hashed version of the passed-in String
	 */
	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. "
						+ "system will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return toHex(digest.digest());
	}
	
	/**
	 * 
	     * @discription 将字符串做 SHA1摘要
	     * @author xucs       
	     * @created 2016-4-12 上午8:44:48  
	     * @update 2016-4-12 上午8:44:48   
	     * @param message 信息
	     * @return 摘要值十六进制转换后的字符串 
	 */
	public static String doSHA1(String message) {
		if( null == message )
		{
			return null;
		}
		java.security.MessageDigest alga = null ;
		try {
			alga = java.security.MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		//使用指定的字节数组对摘要进行最后更新，然后完成摘要计算。
		alga.update(message.getBytes());
		//计算SHA-1值
		byte[] digesta = alga.digest();
		
		//将字节数组转换成十六进制数	Hex 类在 commons-codec-1.3.jar中
		Hex hex = new Hex();
		return new String(hex.encodeHex(digesta));
	}

	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number. <p/> Method by Santeri Paavolainen, Helsinki Finland
	 * 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 * 
	 * @param bytes
	 *            an array of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Turns a hex encoded string into a byte array. It is specifically meant to
	 * "reverse" the toHex(byte[]) method.
	 * 
	 * @param hex
	 *            a hex encoded String to transform into a byte array.
	 * @return a byte array representing the hex String[
	 */
	public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * Returns the the byte value of a hexadecmical char (0-f). It's assumed
	 * that the hexidecimal chars are lower case as appropriate.
	 * 
	 * @param ch
	 *            a hexedicmal character (0-f)
	 * @return the byte value of the character (0x00-0x0F)
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

	// *********************************************************************
	// * Base64 - a simple base64 encoder and decoder.
	// *
	// * Copyright (c) 1999, Bob Withers - bwit@pobox.com
	// *
	// * This code may be freely used for any purpose, either personal
	// * or commercial, provided the authors copyright notice remains
	// * intact.
	// *********************************************************************

	/**
	 * Encodes a String as a base64 String.
	 * 
	 * @param data
	 *            a String to encode.
	 * @return a base64 encoded String.
	 */
	public static String encodeBase64(String data) {
		return encodeBase64(data.getBytes());
	}

	/**
	 * Encodes a byte array into a base64 String.
	 * 
	 * @param data
	 *            a byte array to encode.
	 * @return a base64 encode String.
	 */
	public static String encodeBase64(byte[] data) {
		int c;
		int len = data.length;
		StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
		for (int i = 0; i < len; ++i) {
			c = (data[i] >> 2) & 0x3f;
			ret.append(cvt.charAt(c));
			c = (data[i] << 4) & 0x3f;
			if (++i < len)
				c |= (data[i] >> 4) & 0x0f;

			ret.append(cvt.charAt(c));
			if (i < len) {
				c = (data[i] << 2) & 0x3f;
				if (++i < len)
					c |= (data[i] >> 6) & 0x03;

				ret.append(cvt.charAt(c));
			} else {
				++i;
				ret.append((char) fillchar);
			}

			if (i < len) {
				c = data[i] & 0x3f;
				ret.append(cvt.charAt(c));
			} else {
				ret.append((char) fillchar);
			}
		}
		return ret.toString();
	}

	/**
	 * Decodes a base64 String.
	 * 
	 * @param data
	 *            a base64 encoded String to decode.
	 * @return the decoded String.
	 */
	public static String decodeBase64(String data) {
		return decodeBase64(data.getBytes());
	}

	/**
	 * Decodes a base64 aray of bytes.
	 * 
	 * @param data
	 *            a base64 encode byte array to decode.
	 * @return the decoded String.
	 */
	public static String decodeBase64(byte[] data) {
		int c, c1;
		int len = data.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; ++i) {
			c = cvt.indexOf(data[i]);
			++i;
			c1 = cvt.indexOf(data[i]);
			c = ((c << 2) | ((c1 >> 4) & 0x3));
			ret.append((char) c);
			if (++i < len) {
				c = data[i];
				if (fillchar == c)
					break;

				c = cvt.indexOf((char) c);
				c1 = ((c1 << 4) & 0xf0) | ((c >> 2) & 0xf);
				ret.append((char) c1);
			}

			if (++i < len) {
				c1 = data[i];
				if (fillchar == c1)
					break;

				c1 = cvt.indexOf((char) c1);
				c = ((c << 6) & 0xc0) | c1;
				ret.append((char) c);
			}
		}
		return ret.toString();
	}

	private static final int fillchar = '=';

	private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";

	/**
	 * Turns an array of bytes into a String representing each byte as an
	 * unsigned hex number. <p/> Method by Santeri Paavolainen, Helsinki Finland
	 * 1996<br>
	 * (c) Santeri Paavolainen, Helsinki Finland 1996<br>
	 * Distributed under LGPL.
	 * 
	 * @param hash
	 *            an rray of bytes to convert to a hex-string
	 * @return generated hex string
	 */
	public static final String toHex(byte hash[]) {
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if (((int) hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) hash[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Converts a line of text into an array of lower case words. Words are
	 * delimited by the following characters: , .\r\n:/\+ <p/> In the future,
	 * this method should be changed to use a BreakIterator.wordInstance(). That
	 * class offers much more fexibility.
	 * 
	 * @param text
	 *            a String of text to convert into an array of words
	 * @return text broken up into an array of words.
	 */
	public static final String[] toLowerCaseWordArray(String text) {
		if (text == null || text.length() == 0) {
			return new String[0];
		}
		StringTokenizer tokens = new StringTokenizer(text, " ,\r\n.:/\\+");
		String[] words = new String[tokens.countTokens()];
		for (int i = 0; i < words.length; i++) {
			words[i] = tokens.nextToken().toLowerCase();
		}
		return words;
	}

	/**
	 * A list of some of the most common words. For searching and indexing, we
	 * often want to filter out these words since they just confuse searches.
	 * The list was not created scientifically so may be incomplete :)
	 */
	private static final String[] commonWords = new String[] { "a", "and",
			"as", "at", "be", "do", "i", "if", "in", "is", "it", "so", "the",
			"to" };

	
	private static Map commonWordsMap = null;

	/**
	 * Returns a new String array with some of the most common English words
	 * removed. The specific words removed are: a, and, as, at, be, do, i, if,
	 * in, is, it, so, the, to
	 */
	public static final String[] removeCommonWords(String[] words) {
		// See if common words map has been initialized. We don't statically
		// initialize it to save some memory. Even though this a small savings,
		// it adds up with hundreds of classes being loaded.
		if (commonWordsMap == null) {
			synchronized (initLock) {
				if (commonWordsMap == null) {
					commonWordsMap = new HashMap();
					for (int i = 0; i < commonWords.length; i++) {
						commonWordsMap.put(commonWords[i], commonWords[i]);
					}
				}
			}
		}
		// Now, add all words that aren't in the common map to results
		ArrayList results = new ArrayList(words.length);
		for (int i = 0; i < words.length; i++) {
			if (!commonWordsMap.containsKey(words[i])) {
				results.add(words[i]);
			}
		}
		return (String[]) results.toArray(new String[results.size()]);
	}

	/**
	 * Pseudo-random number generator object for use with randomString(). The
	 * Random class is not considered to be cryptographically secure, so only
	 * use these random Strings for low to medium security applications.
	 */
	private static Random randGen = null;

	/**
	 * Array of numbers and letters of mixed case. Numbers appear in the list
	 * twice so that there is a more equal chance that a number will be picked.
	 * We can use the array to get a random number or letter by picking a random
	 * array index.
	 */
	private static char[] numbersAndLetters = null;

	/**
	 * Returns a random String of numbers and letters of the specified length.
	 * The method uses the Random class that is built-in to Java which is
	 * suitable for low to medium grade security uses. This means that the
	 * output is only pseudo random, i.e., each number is mathematically
	 * generated so is not truly random.
	 * <p>
	 * <p/> For every character in the returned String, there is an equal chance
	 * that it will be a letter or number. If a letter, there is an equal chance
	 * that it will be lower or upper case.
	 * <p>
	 * <p/> The specified length must be at least one. If not, the method will
	 * return null.
	 * 
	 * @param length
	 *            the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Init of pseudo random number generator.
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					// Also initialize the numbersAndLetters array
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
							.toCharArray();
				}
			}
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String randomStringOfStr(int length) {
		if (length < 1) {
			return null;
		}
		// Init of pseudo random number generator.
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					// Also initialize the numbersAndLetters array
					numbersAndLetters = ("abcdefghijklmnopqrstuvwxyz")
							.toCharArray();
				}
			}
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(26)];
		}
		return new String(randBuffer);
	}

	/**
	 * Intelligently chops a String at a word boundary (whitespace) that occurs
	 * at the specified index in the argument or before. However, if there is a
	 * newline character before <code>length</code>, the String will be
	 * chopped there. If no newline or whitespace is found in
	 * <code>string</code> up to the index <code>length</code>, the String
	 * will chopped at <code>length</code>. <p/> For example,
	 * chopAtWord("This is a nice String", 10) will return "This is a" which is
	 * the first word boundary less than or equal to 10 characters into the
	 * original String.
	 * 
	 * @param string
	 *            the String to chop.
	 * @param length
	 *            the index in <code>string</code> to start looking for a
	 *            whitespace boundary at.
	 * @return a substring of <code>string</code> whose length is less than or
	 *         equal to <code>length</code>, and that is chopped at
	 *         whitespace.
	 */
	public static final String chopAtWord(String string, int length) {
		if (string == null) {
			return string;
		}

		int sLength = string.length();
		char[] charArray = string.toCharArray();
		if (length < sLength) {
			sLength = length;
		}

		// First check if there is a newline character before length; if so,
		// chop word there.
		for (int i = 0; i < sLength - 1; i++) {
			// Windows
			if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
				return string.substring(0, i);
			}
			// Unix
			else if (charArray[i] == '\n') {
				return string.substring(0, i);
			}
		}
		// Also check boundary case of Unix newline
		if (sLength - 1 >= 0) {
			if (charArray[sLength - 1] == '\n')
				return string.substring(0, sLength - 1);
		}

		// Done checking for newline, now see if the total string is less than
		// the specified chop point.
		if (string.length() < length) {
			return string;
		}
		/*
		 * //No newline, so chop at the first whitespace. for (int i = length -
		 * 1; i > 0; i--) { if (charArray[i] == ' ') { return string.substring
		 * (0, i).trim (); } }
		 */
		// Did not find word boundary so return original String chopped at
		// specified length.
		return string.substring(0, length);
	}
	
	/**
	 * 
	     * @discription 去除掉字符串中第一次以给定字符串开头和结尾的部分
	     * @author xucs       
	     * @created 2016-4-15 上午11:00:46  
	     * @update 2016-4-15 上午11:00:46   
	     * @param aimStr 目标字符串
	     * @param beginStr 起始字符串
	     * @param endStr 结束字符串
	     * @return
	 */
	public static String trimStr(String aimStr, String beginStr, String endStr) {
		String newStr = aimStr;
		int iStart = aimStr.indexOf(beginStr);
		int iEnd = aimStr.indexOf(endStr) + endStr.length();
		if (iStart != -1 && iEnd != -1) {
			newStr = aimStr.substring(0, iStart)
					+ aimStr.substring(iEnd, aimStr.length());
		}
		return newStr;
	}

	/**
	 * 
	     * @discription 在此输入一句话描述作用
	     * @author xucs       
	     * @created 2016-4-15 上午11:01:28  
	     * @update 2016-4-15 上午11:01:28   
	     * @param date 根据系统配置中的假期设置判断是否是节假日
	     * @param holidays 天数
	     * @return
	 */
	public static boolean isHoliday(Date date, String holidays) {
		/*
		 * 节假日的定义: 周六,周日, 1.1--1.3; 5.1--5.7;
		 */
		if (date.getDay() == 0 || date.getDay() == 6)
			return true;
//		String holidays = PropertyManager.getProperty("holidays");
		holidays = holidays != null ? holidays : "";
		String[] holidayarr = holidays.split(",");
		for (int i = 0; i < holidayarr.length; i++) {
			// String holidaystr = todayYear + "-" + holidayarr[i];
			try {
				String[] yd = holidayarr[i].split("-");
				if (yd[0].trim().equals(date.getMonth() + 1 + "")
						&& yd[1].trim().equals(date.getDate() + ""))
					return true;
				// if(holiday.compareTo(date) == 0)
				// return true;
			} catch (Exception e) {
				System.out.println("节假日" + holidayarr[i] + "有误");
			}

		}
		return false;
	}

	/**
	 * 
	     * @discription  把类似于 |fsdjl|fjskadlfj|fjskafj| 这样的id串拼成类似于 'fsdjl','fjskadlfj','fjskafj'
	     * 这样的串返回(删除中间""空字符串) cutStringToArray(str, "|", "'", ",")
	     * @author xucs       
	     * @created 2016-4-15 上午11:01:55  
	     * @update 2016-4-15 上午11:01:55   
	     * @param str 字符串
	     * @param split 截取串
	     * @param replacement 替换串
	     * @param replacementSplit 替换表达式
	     * @return
	 */
	public static String regroupString(String str, String split,
			String replacement, String replacementSplit) {
		if (str == null) {
			return "";
		}
		String[] strArr = str.split(split);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < strArr.length; i++) {
			if (!"".equals(strArr[i])) {
				sb.append(replacement + strArr[i] + replacement
						+ replacementSplit);
			}
		}
		// 去掉最后一个replacementSplit
		if (sb.length() > replacementSplit.length()) {
			sb.deleteCharAt(sb.length() - replacementSplit.length());
		}
		return sb.toString();
	}

	/**
	 * 
	     * @discription 把String[] 拼成 一个String。
	     * @author xucs       
	     * @created 2016-4-15 上午11:02:39  
	     * @update 2016-4-15 上午11:02:39   
	     * @param strArr 数组
	     * @param prefix 前缀
	     * @param subfix 后缀
	     * @param split 截取串
	     * @return
	 */
	public static String regroupStringArr(String[] strArr, String prefix,
			String subfix, String split) {
		if (strArr == null || strArr.length == 0) {
			return "";
		}
		if (prefix == null) {
			prefix = "";
		}
		if (subfix == null) {
			subfix = "";
		}
		if (split == null) {
			split = "";
		}
		StringBuffer returnValue = new StringBuffer("");
		for (int i = 0; i < strArr.length; i++) {
			returnValue.append(prefix).append(strArr[i]).append(subfix).append(
					split);
		}
		// 去掉最后一个split
		if (split.length() > 0)
			returnValue.deleteCharAt(returnValue.length() - split.length());
		return returnValue.toString();
	}
	
	/**
	 * 
	     * @discription 把String 拼成 一个String[]。
	     * @author xucs       
	     * @created 2016-4-15 上午11:04:16  
	     * @update 2016-4-15 上午11:04:16   
	     * @param str 数组数据
	     * @param split 默认是","
	     * @return
	 */
	public static String[] regroupStringArr(String str, String split) {
		if (str == null) {
			return null;
		}
		if (split == null) {
			split = ",";
		}
		String[] returnStrArr;
		String[] strArr = str.split(split);
		boolean[] booArr = new boolean[strArr.length];//判断每一项是否符合要求，符合记为true。
		int count = 0;//符合要求的总数
		for (int i = 0; i < strArr.length; i++) {
			if(!strArr[i].trim().equals("")){
				count++;
				booArr[i] = true;
			}else{
				booArr[i] = false;
			}
		}
		returnStrArr = new String[count];
		count = 0;
		for (int i = 0; i < booArr.length; i++) {
			if(booArr[i]){
				returnStrArr[count++] = strArr[i];
			}
		}
		return returnStrArr;
	}
	

	/**
	 * 
	     * @discription 第一个字符大写
	     * @author xucs       
	     * @created 2016-4-15 上午11:04:37  
	     * @update 2016-4-15 上午11:04:37   
	     * @param str
	     * @return
	 */
	public static String upperCaseFirstLetter(String str){
		return str.replaceFirst(String.valueOf(str.charAt(0)), String.valueOf(str.charAt(0)).toUpperCase());
	}

	/**
	 * 
	     * @discription 返回obj在objArr中的位置
	     * @author xucs       
	     * @created 2016-4-15 上午11:04:53  
	     * @update 2016-4-15 上午11:04:53   
	     * @param objArr 数组数据
	     * @param obj 数据对象
	     * @return
	 */
	public static int getIndexInArr(Object[] objArr, Object obj){
		if(objArr == null){
			return -1;
		}
		for (int i = 0; i < objArr.length; i++) {
			Object object = objArr[i];
			if(object.equals(obj)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 
	     * @discription 将字符串转成布尔型
	     * @author xucs       
	     * @created 2016-4-15 上午11:05:23  
	     * @update 2016-4-15 上午11:05:23   
	     * @param str 转化的串 如 1 yes y true 都为true 其他都是false
	     * @return
	 */
	public static boolean convertStringToBoolean(String str){
		if("1".equals(str)){
			return true;
		}else if("yes".equals(str)){
			return true;
		}else if("y".equals(str)){
			return true;
		}else if("true".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	     * @discription 提供32位全球无重复的字符串 UUID
	     * @author xucs       
	     * @created 2016-4-11 下午6:56:49  
	     * @update 2016-4-11 下午6:56:49   
	     * @return uuid
	 */
	public static String UUID32(){
		return UUID36().replaceAll("-", "");
	}
	
	
	/**
	 * 
	     * @discription 提供36位全球无重复的字符串 UUID
	     * @author xucs       
	     * @created 2016-4-11 下午6:57:12  
	     * @update 2016-4-11 下午6:57:12   
	     * @return uuid
	 */
	public static String UUID36(){
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	     * @discription 将字符串s第index个字母小写（index从0开始），并返回。
	     * @author xucs       
	     * @created 2016-4-15 上午11:06:20  
	     * @update 2016-4-15 上午11:06:20   
	     * @param s 字符串
	     * @param index 第几个字符
	     * @return
	 */
	public static String lowerCharInString(String s, int index){
		return lowerAndUpperCharInStringUtil(s, index, CharacterFlag.Lower);
	}
	
	/**
	 * 
	     * @discription 将字符串s第index个字母大写（index从0开始），并返回。
	     * @author xucs       
	     * @created 2016-4-15 上午11:06:53  
	     * @update 2016-4-15 上午11:06:53   
	     * @param s 字符串
	     * @param index 第几个字符
	     * @return
	 */
	public static String upperCharInString(String s, int index){
		return lowerAndUpperCharInStringUtil(s, index, CharacterFlag.Upper);
	}
	
	/**
	 * 
	     * @discription lowerCharInString方法和upperCharInString的公用类
	     * @author xucs       
	     * @created 2016-4-15 上午11:07:13  
	     * @update 2016-4-15 上午11:07:13   
	     * @param s 字符串 
	     * @param index 第几个
	     * @param flag  1代表upper，大写 -1代表lower小写
	     * @return
	 */
	private static String lowerAndUpperCharInStringUtil(String s, int index, CharacterFlag flag) {
		if (s == null || s.length() < index + 1 || index < 0) {
			System.err.println("字符串参数与序列参数不正确");
			return s;
		}
//		if()
		StringBuffer sb = new StringBuffer(s);
		Character c;
		//转换大小写
		if (flag.equals(CharacterFlag.Lower)) {
			c = Character.toLowerCase(sb.charAt(index));
		} else if (flag.equals(CharacterFlag.Upper)) {
			c = Character.toUpperCase(sb.charAt(index));
		}else{
			c = null;
		}
		//删除第index个字符
		sb.deleteCharAt(index);
		//重新拼接
		if (index == 0) {
			return c + sb.toString();
		} else if (index == s.length() - 1) {
			return sb.substring(0, index) + c;
		} else {
			return sb.substring(0, index) + c + sb.substring(index);
		}
	}
	
	/**
	 * 
	     * @discription 对于String类型的非空判断
	     * @author xucs       
	     * @created 2016-4-15 上午11:07:56  
	     * @update 2016-4-15 上午11:07:56   
	     * @param s String类型的数据
	     * @return boolean true-null或者”“，false-有值
	 */
	public static boolean isNullOrEmpty(String s){
		if(s == null || "".equals(s)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	     * @discription ：把 字符串数组转化成json串数组
	     * @author xucs       
	     * @created 2016-4-11 下午6:57:46  
	     * @update 2016-4-11 下午6:57:46   
	     * @param arg 字符串数组
	     * @return
	 */
	public static String arrayToJson(String [] arg){
		JSONArray  array =new JSONArray();
		for(String str : arg){
			array.add(str);	
		}
		return array.toString();
	}
	
	
	
	

	
	/**
	 * 
	     * @discription 判断字符串为null或者为""
	     * @author xucs       
	     * @created 2016-4-11 下午6:58:39  
	     * @update 2016-4-11 下午6:58:39   
	     * @param value 要判断的字符按串
	     * @return 如果字符串是null或者为""，返回true，否则返回false
	 */
	public static boolean isNullorBlank(String value) {
		return null == value || "".equals(value);
	}
	
	
	
	/**
	 * 
	     * @discription 去掉左右空格后字符串是否为空.
	     * @author xucs       
	     * @created 2016-4-11 下午6:59:11  
	     * @update 2016-4-11 下午6:59:11   
	     * @param astr 要处理的字符串
	     * @return 如果去掉左右空格后为空，返回true，否则返回false
	 */
	public static boolean isTrimEmpty(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		}
		if (isBlank(trim(astr))) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * 
	     * @discription 去掉指定字符串两端的空格
	     * @author xucs       
	     * @created 2016-4-11 下午6:59:32  
	     * @update 2016-4-11 下午6:59:32   
	     * @param value 指定的字符串
	     * @return 去掉两端空格后的字符串。如果传入的指定字符串是null，返回""。
	 */
	public static String trim(String value) {
		if (value == null) {
			return "";
		}
		else {
			return value.trim();
		}
	}
	
	
	/**
	 * 
	     * @discription 字符串是否为空:null或者长度为0.
	     * @author xucs       
	     * @created 2016-4-11 下午6:59:48  
	     * @update 2016-4-11 下午6:59:48   
	     * @param astr 要判断的字符串
	     * @return null或者长度为0.
	 */
	public static boolean isBlank(String astr) {
		return ((null == astr) || (astr.length() == 0));
	}
	
	/**
	 * 方便测试本类中的方法
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println(lowerCharInString("FJDKF", 0));
		System.out.println(lowerCharInString("FJDKF", 4));
		System.out.println(lowerCharInString("FJDKF", 2));
		System.out.println(lowerCharInString("FJDKF", 5));
		System.out.println(lowerCharInString("FJDKF", -1));
	}
}
