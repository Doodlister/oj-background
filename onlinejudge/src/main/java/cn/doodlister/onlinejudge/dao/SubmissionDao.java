package cn.doodlister.onlinejudge.dao;

import cn.doodlister.onlinejudge.entity.Problem;
import cn.doodlister.onlinejudge.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionDao extends JpaRepository<Submission,Integer> {
}
