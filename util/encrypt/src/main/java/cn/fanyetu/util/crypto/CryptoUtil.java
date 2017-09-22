package cn.fanyetu.util.crypto;

import cn.fanyetu.util.crypto.symmetric.AES;
import cn.fanyetu.util.crypto.symmetric.SymmetricAlgorithm;
import cn.fanyetu.util.crypto.symmetric.SymmetricCrypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密工具类
 * <p>
 * Created by zhanghaonan on 2017/9/21.
 */
public class CryptoUtil {

	/**
	 * 生成SecretKey，仅用于对称加密和摘要算法密钥生成
	 *
	 * @param algorithm
	 * @param key
	 * @return
	 */
	public static SecretKey generateKey(SymmetricAlgorithm algorithm, String key) {
		return generateKey(algorithm, key, StandardCharsets.UTF_8);
	}

	/**
	 * 生成SecretKey，仅用于对称加密和摘要算法密钥生成
	 *
	 * @param algorithm
	 * @param key
	 * @param charset
	 * @return
	 */
	public static SecretKey generateKey(SymmetricAlgorithm algorithm, String key, Charset
			charset) {
		byte[] keyBytes = key.getBytes(charset);
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new CryptoException(e);
		}
		byte[] bytes = md.digest(keyBytes);
		return generateKey(algorithm, bytes);
	}

	/**
	 * 生成SecretKey，仅用于对称加密和摘要算法密钥生成
	 *
	 * @param algorithm 算法
	 * @param key
	 * @return
	 */
	public static SecretKey generateKey(SymmetricAlgorithm algorithm, byte[] key) {
		return (null == key) ? generateKey(algorithm) :
				new SecretKeySpec(key, algorithm.getValue());
	}

	/**
	 * 生成SecretKey，仅用于对称加密和摘要算法密钥生成
	 *
	 * @param algorithm 算法
	 * @return
	 */
	public static SecretKey generateKey(SymmetricAlgorithm algorithm) {
		SecretKey secretKey;
		try {
			secretKey = KeyGenerator.getInstance(algorithm.getValue()).generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new CryptoException(e);
		}
		return secretKey;
	}

	public static SymmetricCrypto symmetric(SymmetricAlgorithm algorithm) {
		return new SymmetricCrypto(algorithm, generateKey(algorithm));
	}

	public static SymmetricCrypto symmetric(SymmetricAlgorithm algorithm, byte[] key) {
		return new SymmetricCrypto(algorithm, key);
	}

	public static SymmetricCrypto symmetric(SymmetricAlgorithm algorithm, SecretKey key) {
		return new SymmetricCrypto(algorithm, key);
	}

	public static SymmetricCrypto symmetric(SymmetricAlgorithm algorithm, String key, Charset
			charset) {
		return new SymmetricCrypto(algorithm, key, charset);
	}

	public static SymmetricCrypto symmetric(SymmetricAlgorithm algorithm, String key) {
		return new SymmetricCrypto(algorithm, key);
	}

	public static AES aes() {
		return new AES();
	}

	public static AES aes(byte[] key) {
		return new AES(key);
	}

	public static AES aes(SecretKey key) {
		return new AES(key);
	}

	public static AES aes(String key) {
		return new AES(key);
	}

	public static AES aes(String key, Charset charset) {
		return new AES(key, charset);
	}
}
