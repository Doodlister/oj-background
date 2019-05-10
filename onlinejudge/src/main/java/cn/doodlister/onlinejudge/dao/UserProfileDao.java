package cn.doodlister.onlinejudge.dao;


import cn.doodlister.onlinejudge.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileDao extends JpaRepository<UserProfile,Integer> {
}
