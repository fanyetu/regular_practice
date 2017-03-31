package cn.fanyetu.logback;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback初体验
 * @author Administrator
 *
 */
public class HelloWorld1 {
	
	public static void main(String[] args) {
		
		//如果项目中只有logback那么可以直接使用slf4j，但是这里我们有log4j和logback，所以应该多做一部操作
		Logger logger = LoggerFactory.getLogger("cn.fanyetu.logback");
		logger.debug("hello world");
	}

}
