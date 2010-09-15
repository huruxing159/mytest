package com.goodhope.goldselling.service;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

import com.goodhope.goldselling.exception.MD5EncryptionException;

public class MD5Service {
	private final static Logger LOG = Logger.getLogger(MD5Service.class);

	public String md5(String source) throws MD5EncryptionException {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			char[] charArray = source.toCharArray();
			byte[] byteArray = new byte[charArray.length];
			for (int i = 0; i < charArray.length; i++) {
				byteArray[i] = (byte) charArray[i];
			}
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();

		} catch (Exception e) {
			LOG.error(e, e);
			throw new MD5EncryptionException(e);
		}
	}

}
