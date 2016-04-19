package com.mkhx.tool.base.main;


/**  
 *   CryptionData.java  
 *   @version   1.0  
 *   @author   2005/4/20   Yao   (WICT)  
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.*;
import sun.misc.*;

/**
 * DES encryption algorithm, providing the encryption and decryption algorithm
 * for byte array and string
 * 
 * @author : Yao (WICT)
 * @version 1.0
 */
public class CryptionData {
	// The length of Encryptionstring should be 8 bytes and not be
	// a weak key
	private String EncryptionString;

	// The initialization vector should be 8 bytes
	private byte[] EncryptionIV = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88,
			0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
			0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
			(byte) 0xE2 };
	private final static String DES = "DES/CBC/PKCS5Padding";

	/**
	 * Saving key for encryption and decryption
	 * 
	 * @param EncryptionString
	 *            String
	 */
	public CryptionData(String EncryptionString) {
		this.EncryptionString = EncryptionString;
	}

	/**
	 * Encrypt a byte array
	 * 
	 * @param SourceData
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public byte[] EncryptionByteData(byte[] SourceData) throws Exception {
		byte[] retByte = null;

		// Create SecretKey object

		byte[] EncryptionByte = EncryptionString.getBytes();
		DESKeySpec dks = new DESKeySpec(EncryptionByte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		EncryptionIV = "gzbank!@".getBytes();
		// Create IvParameterSpec object with initialization vector
		IvParameterSpec spec = new IvParameterSpec(EncryptionIV);

		// Create Cipter object
		Cipher cipher = Cipher.getInstance(DES);

		// Initialize Cipher object
		cipher.init(Cipher.ENCRYPT_MODE, securekey, spec);

		// Encrypting data
		retByte = cipher.doFinal(SourceData);
		return retByte;
	}

	/**
	 * Decrypt a byte array
	 * 
	 * @param SourceData
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public byte[] DecryptionByteData(byte[] SourceData) throws Exception {
		byte[] retByte = null;

		// Create SecretKey object
		byte[] EncryptionByte = EncryptionString.getBytes();
		DESKeySpec dks = new DESKeySpec(EncryptionByte);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Create IvParameterSpec object with initialization vector
		IvParameterSpec spec = new IvParameterSpec(EncryptionIV);

		// Create Cipter object
		Cipher cipher = Cipher.getInstance(DES);

		// Initialize Cipher object
		cipher.init(Cipher.DECRYPT_MODE, securekey, spec);

		// Decrypting data
		retByte = cipher.doFinal(SourceData);

		return retByte;
	}

	/**
	 * Encrypt a string
	 * 
	 * @param SourceData
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public String EncryptionStringData(String SourceData) throws Exception {
		String retStr = null;
		byte[] retByte = null;

		// Transform SourceData to byte array
		byte[] sorData = SourceData.getBytes();

		// Encrypte data
		retByte = EncryptionByteData(sorData);

		// Encode encryption data
		BASE64Encoder be = new BASE64Encoder();
		retStr = be.encode(retByte);

		return retStr;
	}

	/**
	 * Decrypt a string
	 * 
	 * @param SourceData
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public String DecryptionStringData(String SourceData) throws Exception {
		String retStr = null;
		byte[] retByte = null;

		// Decode encryption data
		BASE64Decoder bd = new BASE64Decoder();
		byte[] sorData = bd.decodeBuffer(SourceData);

		// Decrypting data
		retByte = DecryptionByteData(sorData);
		retStr = new String(retByte);

		return retStr;
	}

	public static void main(String[] args) throws Exception {
		CryptionData c = new CryptionData("12345678");
		System.out.println(c.EncryptionStringData("13764528738"));
	}
}