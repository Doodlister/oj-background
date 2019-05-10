package cn.doodlister.dispatcher.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Result {
    private int time;
    private int memory;
    private String info;
    private String outputPath;
    private ResultType result;
    public enum ResultType{
        /**
         *  0 Wait
         * 1 ：AC	Accepted	通过
         * 2 :WA	Wrong Answer	答案错误
         * 3 :TLE	Time Limit Exceed	超时
         *
         * 4 :OLE	Output Limit Exceed	超过输出限制
         *
         * 5 :MLE	Memory Limit Exceed	超内存
         *
         * 6 :RE	Runtime Error	运行时错误
         * 7 :PE	Presentation Error	格式错误
         * 8 :CE	Compile Error	无法编译
         */
        ACCEEPTED("通过",1),
        WRONG_ANSWER("答案错误",2),
        TIME_LIMIT_EXCEED("超时",3),
        OUTPUT_LIMIT_EXECCD("超过输出限制",4),
        MEMORY_LIMIT_EXCEED("超过内存限制",5),
        RUNTIME_ERROR("运行时错误",6),
        PRESENTATION_ERROR("格式错误",7),
        COMPILE_ERROR("编译出错",8);


        @Getter
        private String info;
        @Getter
        private int code;

        private ResultType(String info, int code) {
            this.info =info;
            this.code = code;
        }
    }
}
