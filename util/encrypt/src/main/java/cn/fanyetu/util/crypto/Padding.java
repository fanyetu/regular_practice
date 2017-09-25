package cn.fanyetu.util.crypto;

/**
 * 分组加密补位方式
 *
 * Created by zhanghaonan on 2017/9/25.
 */
public enum Padding {
	/** 无补码 */
	NoPadding,
	ISO10126Padding,
	OAEPPadding,
	PKCS1Padding,
	PKCS5Padding,
	SSL3Padding
}
