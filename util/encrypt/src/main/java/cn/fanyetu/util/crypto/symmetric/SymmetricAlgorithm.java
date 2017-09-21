package cn.fanyetu.util.crypto.symmetric;

/**
 * 对称加密类型枚举类
 *
 * Created by zhanghaonan on 2017/9/21.
 */
public enum SymmetricAlgorithm {

	AES("AES");

	private String value;

	SymmetricAlgorithm(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
