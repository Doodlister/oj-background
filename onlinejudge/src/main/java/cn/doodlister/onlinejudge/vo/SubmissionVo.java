package cn.doodlister.onlinejudge.vo;

import cn.doodlister.onlinejudge.entity.Notice;
import cn.doodlister.onlinejudge.entity.Problem;
import cn.doodlister.onlinejudge.entity.Submission;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;


@ToString
public class SubmissionVo extends BaseVo<Submission>{

    public SubmissionVo(Submission clazz) {
        super(clazz,true);
        this.id = HashidUtil.encode(clazz.getId());
    }
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Integer contestId;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private int result;
    @Getter
    @Setter
    private String info;
    @Getter
    @Setter
    private String language;
    @Getter
    @Setter
    private String statisticInfo;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private Timestamp createTime;
    @Getter
    @Setter
    private String pid;


}
