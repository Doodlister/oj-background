package cn.doodlister.onlinejudge.exception;

public class ParameterErrorException extends GlobalException {
    public ParameterErrorException(String message, int code) {
        super(message, code);
    }
}
