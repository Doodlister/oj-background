package cn.doodlister.onlinejudge.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_profile", schema = "onlinejudge", catalog = "")
@Getter
@Setter
public class UserProfile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Integer id;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "blog")
    private String blog;

    @Column(name = "mood")
    private String mood;

    @Column(name = "acceptedNumber")
    private Integer acceptedNumber;

    @Column(name = "submissionNumber")
    private Integer submissionNumber;

    @Column(name = "github")
    private String github;

    @Column(name = "school")
    private String school;

    @Column(name = "major")
    private String major;

    @Column(name = "totalScore")
    private Long totalScore;

    @Column(name = "realName")
    private String realName;

    @Column(name = "language")
    private String language;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn
    private User user;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return id == that.id &&
                acceptedNumber == that.acceptedNumber &&
                submissionNumber == that.submissionNumber &&
                totalScore == that.totalScore &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(blog, that.blog) &&
                Objects.equals(mood, that.mood) &&
                Objects.equals(github, that.github) &&
                Objects.equals(school, that.school) &&
                Objects.equals(major, that.major) &&
                Objects.equals(realName, that.realName) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, avatar, blog, mood, acceptedNumber, submissionNumber, github, school, major, totalScore, realName, language);
    }
}
