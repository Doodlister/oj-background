package cn.doodlister.onlinejudge.vo;


import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.entity.UserProfile;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserProfileVo extends BaseVo<UserProfile>{
    public UserProfileVo() {
    }
    public UserProfileVo(UserProfile userProfile) {
        super(userProfile);
    }


    private String avatar;
    private String blog;
    private String mood;
    private int acceptedNumber;
    private int submissionNumber;
    private String github;
    private String school;
    private String major;
    private long totalScore;
    private String realName;
    private String language;

    @Override
    public String toString() {
        return "UserProfileVo{" +
                "avatar='" + avatar + '\'' +
                ", blog='" + blog + '\'' +
                ", mood='" + mood + '\'' +
                ", acceptedNumber=" + acceptedNumber +
                ", submissionNumber=" + submissionNumber +
                ", github='" + github + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", totalScore=" + totalScore +
                ", realName='" + realName + '\'' +
                ", language='" + language + '\'' +
                '}';
    }


}
