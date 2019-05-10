package cn.doodlister.onlinejudge.service;

import cn.doodlister.onlinejudge.dao.ProblemDao;
import cn.doodlister.onlinejudge.entity.Problem;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.exception.AuthenticationException;
import cn.doodlister.onlinejudge.vo.ProblemVo;
import cn.doodlister.onlinejudge.vo.Result;
import cn.doodlister.onlinejudge.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProblemService {
   @Autowired
   ProblemDao problemDao;

   public ProblemVo saveProblem(Problem problem){
      Problem p = problemDao.save(problem);
      return new ProblemVo(p);
   }

   public Page<Problem> findProblemByPage(int page, int pageSize) {
      Page<Problem> problemPage = problemDao.findAll(PageRequest.of(page, pageSize));
       return problemPage;
   }

   public void delete(Problem problem) {
      problemDao.delete(problem);
   }

    public Problem findByID(Integer id) {
        Optional<Problem> problem = problemDao.findById(id);
        if(problem == null){
            return null;
        }else{
            return problem.get();
        }
   }
}
