package cn.doodlister.dispatcher.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter
@Setter
@ToString
public class ExecuteResult {

    @JSONField(name="cpu_time")
    private Integer cpuTime;
    @JSONField(name="real_time")
    private Integer realTime;
    private Integer memory;
    private Integer signal;
    @JSONField(name="exit_code")
    private Integer exitCode;
    private Integer result;
    private Integer error;
    public ResultEnum getResult(){
        return ResultEnum.findByCode(result);
    }
    public ErrorEnum getError(){
        return ErrorEnum.findByCode(result);
    }

    @Getter
    public  enum ResultEnum{
        SUCCESS(0,"success"),CPU_TIME_LIMIT_EXCEEDED(1,"exceed cpu time limit"),
        REAL_TIME_LIMIT_EXCEEDED(2,"exceed real time limit"),MEMORY_LIMIT_EXCEEDED(3,"exceed memory limit"),
        RUNTIME_ERROR(4,"run time error"),SYSTEM_ERROR(5,"SYSTEM_ERROR");
        private  static HashMap<Integer,ResultEnum> CODE_MAP = new HashMap<>();
        static {
            for (ResultEnum typeEnum : ResultEnum.values()) {
                CODE_MAP.put(typeEnum.getCode(), typeEnum);
            }
        }
        public static ResultEnum findByCode(Integer code){
            return CODE_MAP.get(code);
        }
        ResultEnum(int code, String meaning) {
            this.code = code;
            this.meaning = meaning;
        }
        private final int code;
        private final String meaning;
    }
    @Getter
    public enum ErrorEnum{
        SUCCESS(0,"SUCCESS"),
        INVALID_CONFIG(-1,"INVALID_CONFIG"),
        FORK_FAILED(-2,"FORK_FAILED"),
        PTHREAD_FAILED(-3,"PTHREAD_FAILED"),
        WAIT_FAILED(-4,"WAIT_FAILED"),
        ROOT_REQUIRED(-5,"ROOT_REQUIRED"),
        LOAD_SECCOMP_FAILED(-6,"LOAD_SECCOMP_FAILED"),
        SETRLIMIT_FAILED(-7,"SETRLIMIT_FAILED"),
        DUP2_FAILED(-8,"DUP2_FAILED"),
        SETUID_FAILED(-9,"SETUID_FAILED"),
        EXECVE_FAILED(-10,"EXECVE_FAILED ");
        private  static HashMap<Integer,ErrorEnum> CODE_MAP = new HashMap<>();
        static {
            for (ErrorEnum typeEnum : ErrorEnum.values()) {
                CODE_MAP.put(typeEnum.getCode(), typeEnum);
            }
        }
        public static ErrorEnum findByCode(Integer code){
            return CODE_MAP.get(code);
        }
        ErrorEnum(int code,String meaning) {
            this.code = code;
            this.meaning = meaning;
        }
        private final int code;
        private final String meaning;

    }


}
