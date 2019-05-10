package cn.doodlister.onlinejudge.dao;

import cn.doodlister.onlinejudge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);


}
