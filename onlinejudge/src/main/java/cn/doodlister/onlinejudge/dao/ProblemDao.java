package cn.doodlister.onlinejudge.dao;

import cn.doodlister.onlinejudge.entity.Contest;
import cn.doodlister.onlinejudge.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProblemDao extends JpaRepository<Problem,Integer> {
}
