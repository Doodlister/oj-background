package cn.doodlister.onlinejudge.vo;

import lombok.Getter;
import lombok.Setter;

public class Result {
    public enum ErrorCode{
        //404XX 资源未找到
        USER_NOT_FOUND(40401,"用户不存在"),
        NOTICE_NOT_FOUND(40402,"该资源不存在"),
        USER_ALREADY_EXIST(40001,"用户已存在"),
        //401 AUTHENATION
        USER_NAME_ERROR_OR_PASSWORD_ERROR(40100,"用户名或密码有误"),
        TOKEN_EXPIRED(40101,"Token已失效"),
        //406 NOT_ACCEPTABLE
        PARAMETER_ERROR(40600,"参数校验失败");
        ;

        @Getter
        private int code;
        @Getter
        private String msg;

        ErrorCode(int code,String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private Integer code;

    public Result(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }


}
