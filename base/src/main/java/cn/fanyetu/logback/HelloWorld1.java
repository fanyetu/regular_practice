package cn.fanyetu.logback;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback������
 * @author Administrator
 *
 */
public class HelloWorld1 {
	
	public static void main(String[] args) {
		
		//�����Ŀ��ֻ��logback��ô����ֱ��ʹ��slf4j����������������log4j��logback������Ӧ�ö���һ������
		Logger logger = LoggerFactory.getLogger("cn.fanyetu.logback");
		logger.debug("hello world");
	}

}
