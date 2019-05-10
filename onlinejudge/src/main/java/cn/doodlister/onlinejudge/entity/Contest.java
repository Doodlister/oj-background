package cn.doodlister.onlinejudge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Contest {
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
    @Column(name = "real_time_rank")
    private Boolean realTimeRank;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "rule_type")
    private String ruleType;

    @Basic
    @Column(name = "start_time")
    private Timestamp startTime;

    @Basic
    @Column(name = "end_time")
    private Timestamp endTime;

    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;

    @Basic
    @Column(name = "last_update_time")
    private Timestamp lastUpdateTime;

    @Basic
    @Column(name = "visible")
    private Boolean visible;

    @Basic
    @Column(name = "allowed_ip_ranges")
    private String allowedIpRanges;

    @ManyToOne
    @JoinColumn
    private User createByUser;

    @ManyToMany
    @JoinTable(name = "contest_rank",
            joinColumns = {@JoinColumn(name="contest_id")}
    ,inverseJoinColumns = {@JoinColumn(name="user_id")})
    private List<User> userList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest contest = (Contest) o;
        return id == contest.id &&
                realTimeRank == contest.realTimeRank &&
                visible == contest.visible &&
                Objects.equals(title, contest.title) &&
                Objects.equals(description, contest.description) &&
                Objects.equals(password, contest.password) &&
                Objects.equals(ruleType, contest.ruleType) &&
                Objects.equals(startTime, contest.startTime) &&
                Objects.equals(endTime, contest.endTime) &&
                Objects.equals(createTime, contest.createTime) &&
                Objects.equals(lastUpdateTime, contest.lastUpdateTime) &&
                Objects.equals(allowedIpRanges, contest.allowedIpRanges);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, realTimeRank, password, ruleType, startTime, endTime, createTime, lastUpdateTime, visible, allowedIpRanges);
    }
}
