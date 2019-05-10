package cn.doodlister.onlinejudge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "contest_rank", schema = "onlinejudge", catalog = "")
public class ContestRank {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private int id;

    @Basic
    @Column(name = "submission_number")
    private int submissionNumber;

    @Basic
    @Column(name = "accepted_number")
    private int acceptedNumber;

    @Basic
    @Column(name = "total_time")
    private int totalTime;

    @Basic
    @Column(name = "submission_info")
    private String submissionInfo;

    @Basic
    @Column(name = "contest_id")
    private Integer contest_id;

    @Basic
    @Column(name = "user_id")
    private Integer user_id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContestRank that = (ContestRank) o;
        return id == that.id &&
                submissionNumber == that.submissionNumber &&
                acceptedNumber == that.acceptedNumber &&
                totalTime == that.totalTime &&
                Objects.equals(submissionInfo, that.submissionInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, submissionNumber, acceptedNumber, totalTime, submissionInfo);
    }
}
