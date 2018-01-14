package cn.fanyetu.util.core.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhanghaonan on 2017/10/9.
 */
public class HexUtilTest {

	@Test
	public void isHexNumberTest(){
		String hex = "0x1f";
		boolean hexNumber = HexUtil.isHexNumber(hex);

		Assert.assertTrue(hexNumber);

		Integer value = Integer.decode(hex);
		System.out.println(value);
	}
}
