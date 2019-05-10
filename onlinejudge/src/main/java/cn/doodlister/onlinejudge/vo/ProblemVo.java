package cn.doodlister.onlinejudge.vo;

import cn.doodlister.onlinejudge.entity.Notice;
import cn.doodlister.onlinejudge.entity.Problem;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ProblemVo extends BaseVo<Problem>{

    public ProblemVo(Problem clazz) {
        super(clazz,true);
        this.id = HashidUtil.encode(clazz.getId());

    }
    private String id;


    private String title;


    private String description;

    private String inputDescription;

    private String outputDescription;

    private String samples;

    private String testCaseId;

    private String testCaseScore;

    private String hint;

    private String languages;

    private String template;

    private Timestamp createTime;

    private Timestamp lastUpdateTime;

    private int timeLimit;

    private int memoryLimit;

    private Boolean spj;

    private String spjLanguage;

    private String spjCode;

    private String spjVersion;

    private String ruleType;

    private Boolean visible;

    private String difficulty;

    private String source;

    private long submissionNumber;

    private long acceptedNumber;

    private String statisticInfo;

    private int totalScore;


    private Integer contestId;


    private Boolean isPublic;


    private Boolean spjCompileOk;


    private User createByUser;
}
