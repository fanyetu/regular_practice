package cn.fanyetu.design.structure.facade;

/**
 * 新的内容加密类
 *
 * Created by zhanghaonan on 2017/4/7.
 */
public class NewCiphreMachine {

	public String encrypt(String source) {
		System.out.println("使用新算法对内容加密中......");
		char[] chars = source.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char ch : chars) {
			String c = (ch % 8) + "";
			sb.append(c);
		}
		return sb.toString();
	}

}
