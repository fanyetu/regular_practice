package cn.fanyetu.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zhanghaonan
 * @date 2018/1/17
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("登录用户: " + username);

        // 根据用户名查找用户信息
        // 根据查找到的用户信息判断用户是否被冻结
        // 在实际中应该使用自己的类
        String encode = passwordEncoder.encode("123456");
        logger.info("数据库密码是: " + encode);
        return new User(username, encode,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        // 中间4个boolean的意义，对于我们来说还可以赋予它们更多的意义
        // 1.是否启用
        // 2.是否没有过期
        // 3.是否密码没有过期
        // 4.是否没有被冻结

//        return new User(username, "123456",
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
