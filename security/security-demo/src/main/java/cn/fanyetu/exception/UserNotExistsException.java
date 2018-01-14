package cn.fanyetu.exception;

/**
 * @author zhanghaonan
 * @date 2018/1/14
 */
public class UserNotExistsException extends RuntimeException {

    private String id;

    public UserNotExistsException() {
        super("user not exists");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
