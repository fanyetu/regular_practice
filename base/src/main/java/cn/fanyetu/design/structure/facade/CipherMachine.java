package cn.fanyetu.design.structure.facade;

/**
 * 文件加密类，充当子系统
 * <p>
 * Created by zhanghaonan on 2017/4/7.
 */
public class CipherMachine {

	public String encrypt(String source) {
		System.out.println("内容加密中......");
		char[] chars = source.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char ch : chars) {
			String c = (ch % 7) + "";
			sb.append(c);
		}
		return sb.toString();
	}
}
