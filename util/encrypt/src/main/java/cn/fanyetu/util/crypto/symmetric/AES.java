package cn.fanyetu.util.crypto.symmetric;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.Charset;

/**
 * Created by zhanghaonan on 2017/9/22.
 */
public class AES extends SymmetricCrypto {

	public AES() {
		super(SymmetricAlgorithm.AES);
	}

	/**
	 * 构造
	 *
	 * @param key 密钥，支持三种密钥长度：128、192、256位
	 */
	public AES(byte[] key) {
		super(SymmetricAlgorithm.AES, key);
	}

	public AES(SecretKey key) {
		super(SymmetricAlgorithm.AES, key);
	}

	public AES(String key, Charset charset) {
		super(SymmetricAlgorithm.AES, key, charset);
	}

	public AES(String key) {
		super(SymmetricAlgorithm.AES, key);
	}

	public AES setIv(IvParameterSpec iv) {
		super.setParams(iv);
		return this;
	}
}
