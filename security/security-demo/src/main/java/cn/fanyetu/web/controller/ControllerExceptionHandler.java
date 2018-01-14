package cn.fanyetu.web.controller;

import cn.fanyetu.exception.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghaonan
 * @date 2018/1/14
 */
// ControllerAdvice处理异常
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 处理userNotExistsException异常
     * @param ex
     * @return
     */
    @ExceptionHandler(UserNotExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleUserNotExistsException(UserNotExistsException ex) {
        Map<String, String> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }
}
