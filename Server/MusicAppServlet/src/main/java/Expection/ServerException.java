package Expection;

/**
 * 自定义异常类
 */
public class ServerException extends RuntimeException{

    static final long serialVersionUID = -703489719066939L;

    public ServerException(String message) {
        super(message);
    }
}
