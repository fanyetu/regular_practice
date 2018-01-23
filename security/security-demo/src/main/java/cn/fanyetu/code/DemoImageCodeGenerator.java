package cn.fanyetu.code;

import cn.fanyetu.security.core.validate.code.ImageCode;
import cn.fanyetu.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 通过向容器中注入imageCodeGenerator bean覆盖security-core中的默认的imageCodeGenerator
 *
 * @author zhanghaonan
 * @date 2018/1/23
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {


    @Override
    public ImageCode generateImageCode(ServletWebRequest request) {
        System.out.println("更高级的图形验证码实现");
        return null;
    }
}
