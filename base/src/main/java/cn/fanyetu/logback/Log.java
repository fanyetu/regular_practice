package cn.fanyetu.logback;

/**
 * 日志接口
 * 
 * @author Administrator
 * 
 */
public interface Log {

	boolean isDebugEnable();

	boolean isWarnEnable();

	boolean isErrorEnable();

	boolean isInfoEnable();

	boolean isTraceEnabled();

	void trace(Throwable throwable);

	void trace(Object obj, Throwable throwable);

	void debug(Object obj);

	void debug(Throwable throwable);

	void debug(Object obj, Throwable throwable);

	void debug(Object obj, StackTraceElement stackTraceElement);

	void debug(Object obj, Throwable throwable, StackTraceElement stackTraceElement);

	void info(Object obj);

	void info(Throwable throwable);

	void info(Object obj, Throwable throwable);

	void warn(Object obj);

	void warn(Throwable throwable);

	void warn(Object obj, Throwable throwable);

	void error(Object obj);

	void error(Throwable throwable);

	void error(Object obj, Throwable throwable);

}
