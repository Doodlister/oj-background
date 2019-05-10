package cn.doodlister.onlinejudge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Submission {
    @Id
    @Basic
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Integer id;

    @Basic
    @Column(name = "contest_id")
    private Integer contestId;

    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "result")
    private int result;
    @Basic
    @Column(name = "info")
    private String info;
    @Basic
    @Column(name = "language")
    private String language;
    @Basic
    @Column(name = "shared")
    private Boolean shared;
    @Basic
    @Column(name = "statistic_info")
    private String statisticInfo;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "ip")
    private String ip;

    @OneToOne
    @JoinColumn
    private User submitByUser;

    @OneToOne
    @JoinColumn
    private Problem submitProblem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submission that = (Submission) o;
        return result == that.result &&
                shared == that.shared &&
                Objects.equals(id, that.id) &&
                Objects.equals(contestId, that.contestId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(code, that.code) &&
                Objects.equals(info, that.info) &&
                Objects.equals(language, that.language) &&
                Objects.equals(statisticInfo, that.statisticInfo) &&
                Objects.equals(username, that.username) &&
                Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, contestId, createTime, code, result, info, language, shared, statisticInfo, username, ip);
    }
}
