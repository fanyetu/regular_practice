package cn.fanyetu.util.crypto;

import cn.fanyetu.util.crypto.symmetric.AES;
import cn.fanyetu.util.crypto.symmetric.SymmetricAlgorithm;
import cn.fanyetu.util.crypto.symmetric.SymmetricCrypto;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhanghaonan on 2017/9/22.
 */
public class SymmetricTest {

	@Test
	public void test1() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String content = "测试123";

//		SecretKey key = CryptoUtil.generateKey(SymmetricAlgorithm.AES);

		String k = "abchhhhhjjjjff";

		SecretKey key = CryptoUtil.generateKey(SymmetricAlgorithm.AES, k);

		SymmetricCrypto aes = CryptoUtil.symmetric(SymmetricAlgorithm.AES, key);

		byte[] encrypt = aes.encrypt(content);

		System.out.println(new String(encrypt));

		byte[] decrypt = aes.decrypt(encrypt);

		System.out.println(new String(decrypt));
	}

	@Test
	public void test2() {
		String content = "测试123";

//		AES aes = CryptoUtil.aes();
		AES aes = CryptoUtil.aes("abcd");

		byte[] encrypt = aes.encrypt(content);

		byte[] decrypt = aes.decrypt(encrypt);

		Assert.assertEquals(content, new String(decrypt));

	}
}
