package cn.doodlister.judger.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Objects;


@Getter
@Setter
@ToString
public class User {


    private Integer id;



    private String password;



    private Timestamp lastLogin;



    private String username;



    private String email;



    private Timestamp createTime;



    private String adminType;



    private String resetPasswordToken;



    private Timestamp resetPasswordTokenExpireTime;



    private String salt;



    private Boolean isDisabled;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isDisabled == user.isDisabled &&
                Objects.equals(password, user.password) &&
                Objects.equals(lastLogin, user.lastLogin) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(createTime, user.createTime) &&
                Objects.equals(adminType, user.adminType) &&
                Objects.equals(resetPasswordToken, user.resetPasswordToken) &&
                Objects.equals(resetPasswordTokenExpireTime, user.resetPasswordTokenExpireTime) &&
                Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, password, lastLogin, username, email, createTime, adminType, resetPasswordToken, resetPasswordTokenExpireTime, salt, isDisabled);
    }
}
