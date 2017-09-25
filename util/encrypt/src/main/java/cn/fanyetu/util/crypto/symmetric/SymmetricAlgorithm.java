package cn.fanyetu.util.crypto.symmetric;

/**
 * 对称加密类型枚举类
 * <p>
 * Created by zhanghaonan on 2017/9/21.
 */
public enum SymmetricAlgorithm {

	/** 默认的AES加密方式：AES/CBC/PKCS5Padding */
	AES("AES");

	private String value;

	SymmetricAlgorithm(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
