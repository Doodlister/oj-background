package cn.doodlister.onlinejudge.entity;

import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.aspectj.weaver.ast.Test;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class Problem {

    public Problem() {
    }
    public Problem(String id) throws NotFoundException {
        this.id = HashidUtil.decode(id);
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Integer id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "input_description")
    private String inputDescription;
    @Basic
    @Column(name = "output_description")
    private String outputDescription;
    @Basic
    @Column(name = "samples")
    private String samples;
    @Basic
    @Column(name = "test_case_id")
    private String testCaseId;
    @Basic
    @Column(name = "test_case_score")
    private String testCaseScore;
    @Basic
    @Column(name = "hint")
    private String hint;
    @Basic
    @Column(name = "languages")
    private String languages;
    @Basic
    @Column(name = "template")
    private String template;
    @Basic
    @Column(name = "createTime")
    private Timestamp createTime;
    @Basic
    @Column(name = "last_update_time")
    private Timestamp lastUpdateTime;
    @Basic
    @Column(name = "time_limit")
    private int timeLimit;
    @Basic
    @Column(name = "memory_limit")
    private int memoryLimit;
    @Basic
    @Column(name = "spj")
    private Boolean spj;
    @Basic
    @Column(name = "spj_language")
    private String spjLanguage;
    @Basic
    @Column(name = "spj_code")
    private String spjCode;
    @Basic
    @Column(name = "spj_version")
    private String spjVersion;
    @Basic
    @Column(name = "rule_type")
    private String ruleType;
    @Basic
    @Column(name = "visible")
    private Boolean visible;
    @Basic
    @Column(name = "difficulty")
    private String difficulty;
    @Basic
    @Column(name = "source")
    private String source;
    @Basic
    @Column(name = "submission_number")
    private long submissionNumber;
    @Basic
    @Column(name = "accepted_number")
    private long acceptedNumber;
    @Basic
    @Column(name = "statistic_info")
    private String statisticInfo;
    @Basic
    @Column(name = "total_score")
    private int totalScore;

    @Basic
    @Column(name = "contest_id")
    private Integer contestId;

    @Basic
    @Column(name = "is_public")
    private Boolean isPublic;

    @Basic
    @Column(name = "spj_compile_ok")
    private Boolean spjCompileOk;

    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn
    private User createByUser;

    @JSONField(serialize = false)
    @ManyToMany
    @JoinTable(name = "problem_tags",
            joinColumns = {@JoinColumn(name="problem_id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id")})
    private List<ProblemTag> tags;



    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="problem")
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
