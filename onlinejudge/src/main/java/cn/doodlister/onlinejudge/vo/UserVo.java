package cn.doodlister.onlinejudge.vo;


import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.entity.UserProfile;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class UserVo extends BaseVo<User>{
    private String id;
    private Timestamp lastLogin;
    private String username;
    private String email;
    private Timestamp createTime;
    private String adminType;
    private UserProfile userProfile;
    public UserVo() {
    }

    public UserVo(User user){
        super(user,true);

    }
    public UserVo(User user,UserProfile userProfile) {
        super(user,true);
        this.userProfile=userProfile;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id='" + id + '\'' +
                ", lastLogin=" + lastLogin +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", adminType='" + adminType + '\'' +
                ", userProfile=" + userProfile +
                '}';
    }


}
