package cn.fanyetu.util.crypto.symmetric;

import cn.fanyetu.util.crypto.CryptoException;
import cn.fanyetu.util.crypto.CryptoUtil;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhanghaonan on 2017/9/21.
 */
public class SymmetricCrypto {

	/**
	 * SecretKey 负责保存对称密钥
	 */
	private SecretKey secretKey;

	/**
	 * Cipher负责完成加密或解密工作
	 */
	private Cipher cipher;

	/**
	 * 加密解密参数
	 */
	private AlgorithmParameterSpec params;

	private Lock lock = new ReentrantLock();

	public SymmetricCrypto(SymmetricAlgorithm algorithm) {
		init(algorithm, CryptoUtil.generateKey(algorithm));
	}

	public SymmetricCrypto(SymmetricAlgorithm algorithm, String key, Charset charset) {
		init(algorithm, CryptoUtil.generateKey(algorithm, key, charset));
	}

	public SymmetricCrypto(SymmetricAlgorithm algorithm, String key) {
		init(algorithm, CryptoUtil.generateKey(algorithm, key));
	}

	public SymmetricCrypto(SymmetricAlgorithm algorithm, byte[] key) {
		init(algorithm, CryptoUtil.generateKey(algorithm, key));
	}

	public SymmetricCrypto(SymmetricAlgorithm algorithm, SecretKey key) {
		init(algorithm, key);
	}

	private SymmetricCrypto init(SymmetricAlgorithm algorithm, SecretKey secretKey) {
		this.secretKey = secretKey;
//		if (SymmetricAlgorithm.DES) {
//
//		}
		try {
			this.cipher = Cipher.getInstance(algorithm.getValue());
		} catch (Exception e) {
			throw new CryptoException(e);
		}
		return this;
	}

	/**
	 * 设置param,通常用于加盐或偏移向量
	 *
	 * @param params
	 * @return 自身
	 */
	public SymmetricCrypto setParams(AlgorithmParameterSpec params) {
		this.params = params;
		return this;
	}

	/**
	 * 加密
	 *
	 * @param data
	 * @return
	 */
	public byte[] encrypt(byte[] data) {
		lock.lock();
		try {
			if (null == params) {
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, params);
			}
			return cipher.doFinal(data);
		} catch (Exception e) {
			throw new CryptoException(e);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 加密
	 *
	 * @param data
	 * @param charset
	 * @return
	 */
	public byte[] encrypt(String data, Charset charset) {
		return encrypt(data.getBytes(charset));
	}

	/**
	 * 加密，使用utf-8编码
	 *
	 * @param data
	 * @return
	 */
	public byte[] encrypt(String data) {
		return encrypt(data, StandardCharsets.UTF_8);
	}

	/**
	 * 解密
	 *
	 * @param data
	 * @return
	 */
	public byte[] decrypt(byte[] data) {
		lock.lock();
		try {
			if (null == params) {
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, params);
			}
			return cipher.doFinal(data);
		} catch (Exception e) {
			throw new CryptoException(e);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 解密
	 * @param data
	 * @return
	 */
	public String decryptStr(byte[] data){
		return decryptStr(data,StandardCharsets.UTF_8);
	}

	/**
	 * 解密
	 * @param data
	 * @param charset
	 * @return
	 */
	public String decryptStr(byte[] data,Charset charset){
		return new String(decrypt(data),charset);
	}

	/**
	 * 解密
	 *
	 * @param data
	 * @param charset
	 * @return
	 */
	public byte[] decrypt(String data, Charset charset) {
		return decrypt(data.getBytes(charset));
	}

	/**
	 * 解密，使用utf-8编码
	 *
	 * @param data
	 * @return
	 */
	public byte[] decrypt(String data) {
		return decrypt(data, StandardCharsets.UTF_8);
	}
}