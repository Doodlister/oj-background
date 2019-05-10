package cn.doodlister.dispatcher.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;


@Getter
@Setter
public class Submission {

    private Integer id;


    private Integer contestId;


    private Timestamp createTime;

    private String code;

    private int result;

    private String info;

    private String language;

    private Boolean shared;

    private String statisticInfo;

    private String username;

    private String ip;


    private User submitByUser;


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
