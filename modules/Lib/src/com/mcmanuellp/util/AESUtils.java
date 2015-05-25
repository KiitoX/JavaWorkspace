package com.mcmanuellp.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Field;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

public class AESUtils
{
	public static final String tempSalt = "ThisSalt";
	public static final String tempIv   = "ThisIvIsMiracle!";

	public static void applyJCEChanges()
	{
		try
		{
			Field field = Class.forName("javax.crypto.JceSecurity").
					getDeclaredField("isRestricted");
			field.setAccessible(true);
			field.set(null, java.lang.Boolean.FALSE);
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void encrypt(String key, File decInputFile, File encOutputFile, File salt, File iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidParameterSpecException
	{
		FileUtils.createMissingTextFiles(decInputFile, encOutputFile);
		FileInputStream decInputStream = new FileInputStream(decInputFile);
		FileOutputStream encOutputStream = new FileOutputStream(encOutputFile);
		FileOutputStream saltOutputStream = new FileOutputStream(salt);
		FileOutputStream ivOutputStream = new FileOutputStream(iv);
		encrypt(key, decInputStream, encOutputStream, saltOutputStream, ivOutputStream);
	}

	public static void decrypt(String key, File encInputFile, File decOutputFile, File salt, File iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException
	{
		FileUtils.createMissingTextFiles(encInputFile, decOutputFile);
		FileInputStream encInputStream = new FileInputStream(encInputFile);
		FileOutputStream decOutputStream = new FileOutputStream(decOutputFile);
		FileInputStream saltOut = new FileInputStream(salt);
		FileInputStream ivOut = new FileInputStream(iv);
		decrypt(key, encInputStream, decOutputStream, saltOut, ivOut);
	}

	public static void encrypt(String key, InputStream decInput, OutputStream encOutput, OutputStream saltOut, OutputStream ivOut) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidParameterSpecException
	{
		byte[] salt = new byte[8];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);
		saltOut.write(salt);
		saltOut.close();

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
		SecretKey secretKey = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();

		byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
		ivOut.write(iv);
		ivOut.close();

		byte[] input = new byte[64];

		int bytesRead;

		while((bytesRead = decInput.read(input)) != -1)
		{
			byte[] output = cipher.update(input, 0, bytesRead);
			if(output != null) encOutput.write(output);
		}

		byte[] output = cipher.doFinal();

		if(output != null) encOutput.write(output);

		decInput.close();
		encOutput.flush();
		encOutput.close();

		System.out.println("Encryption done.");
	}

	public static void decrypt(String key, InputStream encInput, OutputStream decOutput, InputStream saltIn, InputStream ivIn) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException
	{
		byte[] salt = new byte[8];
		saltIn.read(salt);
		saltIn.close();

		byte[] iv = new byte[16];
		ivIn.read(iv);
		ivIn.close();

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
		SecretKey tmp = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
		byte[] in = new byte[64];
		int read;
		while((read = encInput.read(in)) != -1)
		{
			byte[] output = cipher.update(in, 0, read);
			if(output != null) decOutput.write(output);
		}

		byte[] output = cipher.doFinal();
		if(output != null) decOutput.write(output);
		encInput.close();
		decOutput.flush();
		decOutput.close();

		System.out.println("Decryption done.");
	}
}
