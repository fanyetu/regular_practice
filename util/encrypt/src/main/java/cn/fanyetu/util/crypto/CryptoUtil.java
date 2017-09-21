package cn.fanyetu.util.crypto;

import cn.fanyetu.util.crypto.symmetric.SymmetricAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * 加解密工具类
 *
 * Created by zhanghaonan on 2017/9/21.
 */
public class CryptoUtil {

	/**
	 * 生成SecretKey，仅用户对称加密和摘要算法密钥生成
	 * @param algorithm 算法
	 * @return
	 */
	public static SecretKey generateKey(SymmetricAlgorithm algorithm){
		SecretKey secretKey;
		try {
			secretKey = KeyGenerator.getInstance(algorithm.getValue()).generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new CryptoException(e);
		}
		return secretKey;
	}
}
