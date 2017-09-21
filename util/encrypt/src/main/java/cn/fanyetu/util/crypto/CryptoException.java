package cn.fanyetu.util.crypto;

/**
 * Created by zhanghaonan on 2017/9/21.
 */
public class CryptoException extends RuntimeException {

	private static final long serialVersionUID = 2748407445441098313L;

	public CryptoException() {
		super();
	}

	public CryptoException(String message) {
		super(message);
	}

	public CryptoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CryptoException(Throwable cause) {
		super(cause);
	}

	protected CryptoException(String message, Throwable cause, boolean enableSuppression, boolean
			writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
