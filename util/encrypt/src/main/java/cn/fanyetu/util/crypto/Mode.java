package cn.fanyetu.util.crypto;

/**
 * 分组加密的工作模式
 *
 * Created by zhanghaonan on 2017/9/25.
 */
public enum Mode {

	/** 无模式 */
	NONE,
	/** Cipher Block Chaining */
	CBC,
	/** Cipher Feedback */
	CFB,
	/** A simplification of OFB */
	CTR,
	/** Cipher Text Stealing */
	CTS,
	/** Electronic Codebook */
	ECB,
	/** Output Feedback */
	OFB,
	/** Propagating Cipher Block */
	PCBC;
}
