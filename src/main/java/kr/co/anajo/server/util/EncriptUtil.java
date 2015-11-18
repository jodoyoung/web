package kr.co.anajo.server.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 암호화 도구
 * 
 * @author jodoyoung
 * 
 */
public class EncriptUtil {

	public static synchronized String encriptMD5(String input, boolean isBase64) {
		MessageDigest digest = null;

		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		try {
			digest.update(input.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] rawData = digest.digest();

		if (isBase64) {
			rawData = Base64.getEncoder().encode(rawData);
		}

		return new String(rawData);
	}

	public static void main(String[] args) {
		System.out.println(EncriptUtil.encriptMD5("gidtnr2", true));
	}
}
