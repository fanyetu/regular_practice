package cn.fanyetu.util.poi.exception;

/**
 * Created by zhanghaonan on 2017/9/26.
 */
public class POIException extends RuntimeException {
	private static final long serialVersionUID = 4859473350430981444L;

	public POIException() {
	}

	public POIException(String message) {
		super(message);
	}

	public POIException(String message, Throwable cause) {
		super(message, cause);
	}

	public POIException(Throwable cause) {
		super(cause);
	}

	public POIException(String message, Throwable cause, boolean enableSuppression, boolean
			writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
