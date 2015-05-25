package com.mcmanuellp.wip;

import com.mcmanuellp.util.FileUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Deprecated //TODO cleanup
public class CryptoUtils
{
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";
	public static final int ENCRYPT = Cipher.ENCRYPT_MODE;
	public static final int DECRYPT = Cipher.DECRYPT_MODE;

	/**
	 * does encryption for the whole file
	 *
	 * @param key        must be 16, 24, 32... bytes long, can be anything
	 * @param inputFile  the file that the method reads from
	 * @param outputFile the file that the method writes to
	 */
	public static void encrypt(String key, File inputFile, File outputFile) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		doCrypto(ENCRYPT, key, inputFile, outputFile);
	}

	/**
	 * does decryption for the whole file
	 *
	 * @param key        must be 16, 24, 32... bytes long, must be the same as used for encryption
	 * @param inputFile  the file that the method reads from
	 * @param outputFile the file that the method writes to
	 */
	public static void decrypt(String key, File inputFile, File outputFile) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		doCrypto(DECRYPT, key, inputFile, outputFile);
	}

	/**
	 * does encryption for the whole inputStream without knowing how long it is
	 *
	 * @param key          must be 16, 24, 32... bytes long, can be anything
	 * @param inputStream  the inputStream that the method reads from
	 * @param outputStream the outputStream that the method writes to
	 */
	public static void encrypt(String key, InputStream inputStream, OutputStream outputStream) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		doCrypto(ENCRYPT, key, inputStream, outputStream);
	}

	/**
	 * does decryption for the whole inputStream without knowing how long it is
	 *
	 * @param key          must be 16, 24, 32... bytes long, must be the same as used for encryption
	 * @param inputStream  the inputStream that the method reads from
	 * @param outputStream the outputStream that the method writes to
	 */
	public static void decrypt(String key, InputStream inputStream, OutputStream outputStream) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		doCrypto(DECRYPT, key, inputStream, outputStream);
	}

	/**
	 * does encryption for the inputStream from current position, for the next {@code inputLength} bytes
	 *
	 * @param key          must be 16, 24, 32... bytes long, can be anything
	 * @param inputStream  the inputStream that the method reads from
	 * @param inputLength  the byte count that shall be read from current position on, if -1 will read all
	 * @param outputStream the outputStream that the method writes to
	 */
	public static void encrypt(String key, InputStream inputStream, int inputLength, OutputStream outputStream) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		doCrypto(ENCRYPT, key, inputStream, inputLength, outputStream);
	}

	/**
	 * does decryption for the inputStream from current position, for the next {@code inputLength} bytes
	 *
	 * @param key          must be 16, 24, 32... bytes long, must be the same as used for encryption
	 * @param inputStream  the inputStream that the method reads from
	 * @param inputLength  the byte count that shall be read from current position on, if -1 will read all
	 * @param outputStream the outputStream that the method writes to
	 */
	public static void decrypt(String key, InputStream inputStream, int inputLength, OutputStream outputStream) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		doCrypto(DECRYPT, key, inputStream, inputLength, outputStream);
	}

	/**
	 * safe resets every known java inputStream
	 *
	 * @param inputStream inputStream that is either {@link FileInputStream} or {@code markSupported == true}
	 */
	public static void resetInputStream(InputStream inputStream) throws IOException, IllegalArgumentException
	{
		if(inputStream instanceof FileInputStream) { ((FileInputStream) inputStream).getChannel().position(0); } else
		{
			if(inputStream.markSupported()) { inputStream.reset(); } else
			{ throw new IllegalArgumentException("inputStream doesn't support marking, if possible use different inputStream or implement marking"); }
		}
	}

	/**
	 * does en-/decryption for the whole file
	 *
	 * @param cipherMode possible arguments are {@link #DECRYPT DECRYPT} and {@link #ENCRYPT ENCRYPT}
	 * @param key        must be 16, 24, 32... bytes long, if encrypting can be anything, if decrypting must be the same as used for encryption
	 * @param inputFile  the file that the method reads from
	 * @param outputFile the file that the method writes to
	 */
	private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		FileUtils.createMissingTextFiles(inputFile, outputFile);
		FileInputStream inputStream = new FileInputStream(inputFile);
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		doCrypto(cipherMode, key, inputStream, (inputFile.length() != 0) ? (int) inputFile.length() : 0, outputStream);
		inputStream.close();
		outputStream.close();
	}

	/**
	 * does en-/decryption for the whole inputStream without knowing how long it is
	 *
	 * @param cipherMode   possible arguments are {@link #DECRYPT DECRYPT} and {@link #ENCRYPT ENCRYPT}
	 * @param key          must be 16, 24, 32... bytes long, if encrypting can be anything, if decrypting must be the same as used for encryption
	 * @param inputStream  the inputStream that the method reads from
	 * @param outputStream the outputStream that the method writes to
	 */
	private static void doCrypto(int cipherMode, String key, InputStream inputStream, OutputStream outputStream) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(cipherMode, secretKey);

		resetInputStream(inputStream);

		int inSize = 0;
		int b = inputStream.read();
		while(b != -1)
		{
			inSize++;
			b = inputStream.read();
		}

		resetInputStream(inputStream);

		byte[] inputBytes = new byte[inSize];
		int bRead = inputStream.read(inputBytes);
		if(bRead != inSize + 1)
		{
			throw new BadPaddingException("read bytes don't equal requested length");
		}

		byte[] outputBytes = cipher.doFinal(inputBytes);
		outputStream.write(outputBytes);
	}

	/**
	 * does en-/decryption for the inputStream from current position, for the next {@code inputLength} bytes
	 *
	 * @param cipherMode   possible arguments are {@link #DECRYPT DECRYPT} and {@link #ENCRYPT ENCRYPT}
	 * @param key          must be 16, 24, 32... bytes long, if encrypting can be anything, if decrypting must be the same as used for encryption
	 * @param inputStream  the inputStream that the method reads from
	 * @param inputLength  the byte count that shall be read from current position on, if {@code this < 0} will read all
	 * @param outputStream the outputStream that the method writes to
	 */
	private static void doCrypto(int cipherMode, String key, InputStream inputStream, int inputLength, OutputStream outputStream) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		if(inputLength > 0)
		{
			Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(cipherMode, secretKey);

			byte[] inputBytes = new byte[inputLength];
			int bRead = inputStream.read(inputBytes);
			if(bRead != inputLength)
			{
				throw new BadPaddingException("read bytes don't equal requested length");
			}

			byte[] outputBytes = cipher.doFinal(inputBytes);
			outputStream.write(outputBytes);
		} else if(inputLength < 0)
		{
			doCrypto(cipherMode, key, inputStream, outputStream);
		}
	}

	public static void main(String[] args) throws Exception
	{
		String test = "one to two";
		System.out.println(test);

		ByteArrayOutputStream enc = new ByteArrayOutputStream();
		sEncrypt(new ByteArrayInputStream(test.getBytes()), enc);
		System.out.println(enc.toString());

		ByteArrayOutputStream dec = new ByteArrayOutputStream();
		sDecrypt(new ByteArrayInputStream(enc.toByteArray()), dec);
		System.out.println(dec.toString());
	}

	public static String sEncrypt(String key, String data) throws Exception
	{
		Key sKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(ENCRYPT, sKey);
		byte[] encValue = c.doFinal(data.getBytes());
		return new BASE64Encoder().encode(encValue);
	}

	public static String sDecrypt(String key, String encryptedData) throws Exception
	{
		Key sKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(DECRYPT, sKey);
		byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decodedValue);
		return new String(decValue);
	}

	public static void sEncrypt(InputStream decInputStream, OutputStream encOutputStream) throws Exception
	{
		new BASE64Encoder().encode(decInputStream, encOutputStream);
	}

	public static void sDecrypt(InputStream encInputStream, OutputStream decOutputStream) throws Exception
	{
		new BASE64Decoder().decodeBuffer(encInputStream, decOutputStream);
	}
}