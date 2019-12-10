package cn.doodlister.judger.entity;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Problem {

    private Integer id;

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

    private List<TestCase> testCases;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return id == problem.id &&
                timeLimit == problem.timeLimit &&
                memoryLimit == problem.memoryLimit &&
                spj == problem.spj &&
                visible == problem.visible &&
                submissionNumber == problem.submissionNumber &&
                acceptedNumber == problem.acceptedNumber &&
                totalScore == problem.totalScore &&
                isPublic == problem.isPublic &&
                spjCompileOk == problem.spjCompileOk &&
                Objects.equals(title, problem.title) &&
                Objects.equals(description, problem.description) &&
                Objects.equals(inputDescription, problem.inputDescription) &&
                Objects.equals(outputDescription, problem.outputDescription) &&
                Objects.equals(samples, problem.samples) &&
                Objects.equals(testCaseId, problem.testCaseId) &&
                Objects.equals(testCaseScore, problem.testCaseScore) &&
                Objects.equals(hint, problem.hint) &&
                Objects.equals(languages, problem.languages) &&
                Objects.equals(template, problem.template) &&
                Objects.equals(createTime, problem.createTime) &&
                Objects.equals(lastUpdateTime, problem.lastUpdateTime) &&
                Objects.equals(spjLanguage, problem.spjLanguage) &&
                Objects.equals(spjCode, problem.spjCode) &&
                Objects.equals(spjVersion, problem.spjVersion) &&
                Objects.equals(ruleType, problem.ruleType) &&
                Objects.equals(difficulty, problem.difficulty) &&
                Objects.equals(source, problem.source) &&
                Objects.equals(statisticInfo, problem.statisticInfo) &&
                Objects.equals(contestId, problem.contestId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, inputDescription, outputDescription, samples, testCaseId, testCaseScore, hint, languages, template, createTime, lastUpdateTime, timeLimit, memoryLimit, spj, spjLanguage, spjCode, spjVersion, ruleType, visible, difficulty, source, submissionNumber, acceptedNumber, statisticInfo, totalScore, contestId, isPublic, spjCompileOk);
    }
}
