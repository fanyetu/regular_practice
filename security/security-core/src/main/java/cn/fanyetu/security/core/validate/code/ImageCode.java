package cn.fanyetu.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author zhanghaonan
 * @date 2018/1/22
 */
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expires;

    public ImageCode(BufferedImage image, String code, int expiresIn) {
        this.image = image;
        this.code = code;
        this.expires = LocalDateTime.now().plusSeconds(expiresIn);
    }

    public BufferedImage getImage() {

        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    /**
     * 是否已经过期
     *
     * @return
     */
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(this.expires);
    }
}
