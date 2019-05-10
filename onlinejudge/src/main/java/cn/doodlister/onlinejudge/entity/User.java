package cn.doodlister.onlinejudge.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Integer id;


    @Column(name = "password")
    private String password;


    @Column(name = "last_login")
    private Timestamp lastLogin;


    @Column(name = "username")
    private String username;


    @Column(name = "email")
    private String email;


    @Column(name = "create_time")
    private Timestamp createTime;


    @Column(name = "admin_type")
    private String adminType;


    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    @Column(name = "reset_password_token_expire_time")
    private Timestamp resetPasswordTokenExpireTime;


    @Column(name = "salt")
    private String salt;


    @Column(name = "is_disabled")
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
