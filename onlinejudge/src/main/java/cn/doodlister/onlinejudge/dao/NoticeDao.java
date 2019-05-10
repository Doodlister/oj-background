package cn.doodlister.onlinejudge.dao;

import cn.doodlister.onlinejudge.entity.Contest;
import cn.doodlister.onlinejudge.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeDao extends JpaRepository<Notice,Integer> {
}
