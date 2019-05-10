package cn.doodlister.onlinejudge.controller;


import cn.doodlister.onlinejudge.annotation.AccessLimit;
import cn.doodlister.onlinejudge.entity.Problem;
import cn.doodlister.onlinejudge.entity.Submission;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.exception.ParameterErrorException;
import cn.doodlister.onlinejudge.service.SubmissionService;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import cn.doodlister.onlinejudge.utils.SubmissionUtil;
import cn.doodlister.onlinejudge.utils.Util;
import cn.doodlister.onlinejudge.vo.PageVo.PageResult;
import cn.doodlister.onlinejudge.vo.Result;
import cn.doodlister.onlinejudge.vo.SubmissionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.callback.LanguageCallback;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/submission")
@Api(tags = "submission")
public class SubmissionController {

    @Autowired
    SubmissionService submissionService;

    @AccessLimit(needLogin = true)
    @PostMapping
    @ApiOperation(value = "提交题目",notes="提交题目")
    @ResponseStatus(HttpStatus.CREATED)
    public SubmissionVo subbmit( @RequestBody Submission submission,
                                 @RequestParam String problemId,
                                  User user,
                                 HttpServletRequest request) throws NotFoundException, ParameterErrorException {
        submission.setCreateTime(new Timestamp(new Date().getTime()));
        submission.setSubmitByUser(user);
        submission.setUsername(user.getUsername());
        submission.setIp(Util.getIPAddress(request));
        submission.setSubmitProblem(new Problem(problemId));
        Submission subbmit = submissionService.subbmit(submission);
        return new SubmissionVo(subbmit);
    }

    @AccessLimit(needLogin = true)
    @GetMapping("/{id}")
    @ApiOperation(value = "获取判题结果")
    @ResponseStatus(HttpStatus.OK)
    public SubmissionVo result( @PathVariable("id") String id) throws NotFoundException {


        Integer intId = HashidUtil.decode(id);
        Submission sub = submissionService.findById(intId);
        return new SubmissionVo(sub);
    }

    /**
     * 分页Submission问题
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "分页查询问题",notes="分页查询问题")
    @ResponseStatus(HttpStatus.OK)
    public PageResult<Submission> submissionList(@RequestParam int page,
                                           @RequestParam int pageSize){
        Page<Submission> submissions = submissionService.findSubmissionByPage(page,pageSize);

        PageResult<Submission> pageResult = new PageResult<>(submissions,Submission.class);


        List<Submission> data =submissions.getContent();
        List<SubmissionVo> voData = pageResult.getData();
        for(int i=0;i<voData.size();++i){
            String pid = HashidUtil.encode(data.get(i).getSubmitProblem().getId());
            voData.get(i).setPid(pid);
        }
        return pageResult;
    }

}
