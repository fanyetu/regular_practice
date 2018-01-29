package cn.fanyetu.security.core.validate.code.image;

import cn.fanyetu.security.core.validate.code.AbstractValidateCodeProcesser;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * 图片验证码处理器
 *
 * @author zhanghaonan
 * @date 2018/1/29
 */
@Component("imageValidateCodeProcesser")
public class ImageCodeProcesser extends AbstractValidateCodeProcesser<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
