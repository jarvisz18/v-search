package com.ixan.boot.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author rickiyang
 * @version 1.0
 * @date Created in 2020-01-07
 * @description 短链地址生成器
 * <p>https://blog.csdn.net/youanyyou/article/details/104305989</p>
 */
public class ShortUrlGenerator {
	public static void main(String[] args) {
		String sLongUrl = "http://www.baidu.com/121244/ddd";
		for (String shortUrl : shortUrl(sLongUrl)) {
			System.out.println(shortUrl);
		}
	}

	/**
	 * 摘要算法
	 * <p>摘要算法又称哈希算法，它表示输入任意长度的数据，输出固定长度的数据。相同的输入数据始终得到相同的输出，不同的输入数据尽量得到不同的输出。
	 * <p>
	 * 算法过程：
	 * 将长网址 md5 生成 32 位签名串,分为 4 段, 每段 8 个字节；
	 * 对这四段循环处理, 取 8 个字节, 将他看成 16 进制串与 0x3fffffff(30 位 1)与操作, 即超过 30 位的忽略处理；
	 * 这 30 位分成 6 段, 每 5 位的数字作为字母表的索引取得特定字符, 依次进行获得 6 位字符串；
	 * 总的 md5 串可以获得 4 个 6 位串；取里面的任意一个就可作为这个长 url 的短 url 地址；
	 * 这种算法,虽然会生成 4 个,但是仍然存在重复几率。
	 * <p>
	 * 虽然几率很小，但是该方法依然存在碰撞的可能性，解决冲突会比较麻烦。不过该方法生成的短码位数是固定的，也不存在连续生成的短码有序的情况。</p>
	 *
	 * @param url
	 * @return
	 */
	public static String[] shortUrl(String url) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "dwz";
		// 要使用生成 URL 的字符
		String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z"
		};
		// 对传入网址进行 MD5 加密
		String sMD5EncryptResult = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update((key + url).getBytes());
			byte[] digest = md.digest();
			sMD5EncryptResult = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String[] resUrl = new String[4];
		//得到 4组短链接字符串
		for (int i = 0; i < 4; i++) {
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = sMD5EncryptResult.substring(i * 8, i * 8 + 8);
			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			//循环获得每组6位的字符串
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引(具体需要看chars数组的长度   以防下标溢出，注意起点为0)
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resUrl[i] = outChars;
		}
		return resUrl;
	}
}
