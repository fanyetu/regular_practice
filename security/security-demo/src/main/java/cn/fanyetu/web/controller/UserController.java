package cn.fanyetu.web.controller;

import cn.fanyetu.dto.User;
import cn.fanyetu.dto.UserQueryCondition;
import cn.fanyetu.exception.UserNotExistsException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.US_ASCII;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghaonan
 * @date 2018/1/14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable("id") String id) {
        System.out.println(id);
    }

    @PutMapping("/{id:\\d+}")
    public User update(@PathVariable("id") String id, @Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + " " + fieldError.getDefaultMessage();
//                System.out.println(message);
//            });
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        user.setId("1");
        return user;
    }

    /**
     * // 使用正则限制pathVariable只能是数字
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户id") @PathVariable("id") String id) {

//        UserNotExistsException userNotExistsException = new UserNotExistsException();
//        userNotExistsException.setId(id);
//        throw userNotExistsException;

        System.out.println("进入getInfo服务");

        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(size = 15, page = 1, sort = {"username"}, direction = Sort.Direction
                                    .DESC) Pageable pageable) {

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}
