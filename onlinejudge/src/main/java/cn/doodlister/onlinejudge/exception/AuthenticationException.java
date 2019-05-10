package cn.doodlister.onlinejudge.exception;

public class AuthenticationException extends GlobalException {
    public AuthenticationException(String message, int code) {
        super(message, code);
    }
}
