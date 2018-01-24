package cn.fanyetu.security.core.validate.code.image;

import cn.fanyetu.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码，继承验证码类
 *
 * @author zhanghaonan
 * @date 2018/1/22
 */
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(String code, LocalDateTime expires, BufferedImage image) {
        super(code, expires);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int expiresIn) {
        super(code, expiresIn);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
