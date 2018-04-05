package cn.fanyetu.security.core.social.qq.connect;

import cn.fanyetu.security.core.social.qq.api.QQ;
import cn.fanyetu.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author zhanghaonan
 * @date 2018/4/5
 */
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 验证服务提供商是否可用
     *
     * @param qq
     * @return
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    /**
     * 将从服务提供商处获取的信息转换为spring social的标准数据结构
     *
     * @param qq
     * @param connectionValues
     */
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInfo = qq.getUserInfo();

        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    /**
     * 更新消息，微博类似的才会用到
     *
     * @param qq
     * @param s
     */
    @Override
    public void updateStatus(QQ qq, String s) {
        // do nothing
    }
}
