package cn.fanyetu.util.crypto.symmetric;

import cn.fanyetu.util.crypto.Mode;
import cn.fanyetu.util.crypto.Padding;

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

	public AES(String mode, String padding, byte[] key) {
		super("AES/" + mode + "/" + padding, key);
	}

	public AES(String mode, String padding) {
		this(mode, padding, null);
	}

	public AES(Mode mode, Padding padding, byte[] key) {
		this(mode.name(), padding.name(), key);
	}

	public AES(Mode mode, Padding padding) {
		this(mode, padding, null);
	}

	public AES setIv(IvParameterSpec iv) {
		super.setParams(iv);
		return this;
	}
}
