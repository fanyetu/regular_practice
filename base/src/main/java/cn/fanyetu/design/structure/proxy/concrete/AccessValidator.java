package cn.fanyetu.design.structure.proxy.concrete;

/**
 * 身份信息验证类
 * <p>
 * Created by zhanghaonan on 2017/4/10.
 */
public class AccessValidator {

	/**
	 * 模拟身份认证
	 *
	 * @param username
	 * @return
	 */
	public boolean validate(String username) {
		System.out.println("在数据库中验证用户'" + username + "'是否是合法用户？");
		if ("杨过".equals(username)) {
			System.out.println("登录成功:" + username);
			return true;
		} else {
			System.out.println("登录失败:" + username);
			return false;
		}
	}
}
