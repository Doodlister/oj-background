package cn.doodlister.onlinejudge.service;

import cn.doodlister.onlinejudge.dao.ProblemDao;
import cn.doodlister.onlinejudge.dao.SubmissionDao;
import cn.doodlister.onlinejudge.entity.Problem;
import cn.doodlister.onlinejudge.entity.Submission;
import cn.doodlister.onlinejudge.entity.TestCase;
import cn.doodlister.onlinejudge.exception.ParameterErrorException;
import cn.doodlister.onlinejudge.mq.producter.MQProducter;
import cn.doodlister.onlinejudge.utils.SubmissionUtil;
import cn.doodlister.onlinejudge.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionService {
    @Autowired
    SubmissionDao submissionDao;
    @Autowired
    MQProducter mqProducter;
    @Autowired
    ProblemService problemService;
    public Submission subbmit(Submission submission) throws ParameterErrorException {
        //判断语言是否合法
        if(!SubmissionUtil.isContainLanguage(submission.getLanguage())){
            throw new ParameterErrorException(Result.ErrorCode.PARAMETER_ERROR.getMsg(),
                    Result.ErrorCode.PARAMETER_ERROR.getCode());
        }
        //submission 信息存入数据库
        Submission sub = submissionDao.save(submission);
        sub.setSubmitProblem(problemService.findByID(sub.getSubmitProblem().getId()));

        //加入判题队列
        mqProducter.sendSubmision(sub);
        return sub;
    }

    public Submission findById(Integer id) {
        Optional<Submission> result = submissionDao.findById(id);
        return result.get();
    }
    public Submission update(Submission submission) {
        return submissionDao.save(submission);
    }

    public Page<Submission> findSubmissionByPage(int page, int pageSize) {
        Page<Submission> submissionPage = submissionDao.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC,"createTime"));
        return submissionPage;
    }
}
