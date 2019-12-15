package cn.doodlister.judger.exception;

import cn.doodlister.judger.entity.ExecuteResult;
import lombok.Getter;

@Getter
public class RunException extends Exception  {
    private ExecuteResult executeResult;
    public RunException() {
    }

    public RunException(String message,ExecuteResult executeResult) {
        super(message);
        this.executeResult = executeResult;
    }
}
