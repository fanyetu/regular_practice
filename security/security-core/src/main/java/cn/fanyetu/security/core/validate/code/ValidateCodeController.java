package cn.fanyetu.security.core.validate.code;

import cn.fanyetu.security.core.properties.SecurityProperties;
import cn.fanyetu.security.core.validate.code.image.ImageCode;
import cn.fanyetu.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhanghaonan
 * @date 2018/1/22
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private SecurityProperties securityProperties;

//    @Autowired
//    private ValidateCodeGenerator validateCodeGenerator;

    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGeneratorMap;

    @Autowired
    private SmsCodeSender smsCodeSender;

    // spring 提供的操作session的工具
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        // 1.生成验证码
        ImageCode imageCode = (ImageCode) validateCodeGeneratorMap.get("imageCodeGenerator").generateCode(servletWebRequest);

        // 2.放入session
        sessionStrategy.setAttribute(servletWebRequest, SESSION_KEY, imageCode);

        // 3.返回
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void createSms(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletWebRequest servletWebRequest = new ServletWebRequest(request);

        ValidateCode smsCode = validateCodeGeneratorMap.get("smsCodeGenerator").generateCode(servletWebRequest);

        sessionStrategy.setAttribute(servletWebRequest, SESSION_KEY, smsCode);

        String mobile = request.getParameter("mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }


}
