package cn.doodlister.dispatcher.exception;

import cn.doodlister.dispatcher.entity.Result;

public class WrongAnswerException extends Exception {
    public WrongAnswerException() {
    }

    public WrongAnswerException(String message) {
        super(message);
    }
}
