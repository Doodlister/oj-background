package cn.doodlister.onlinejudge.controller;

import cn.doodlister.onlinejudge.entity.Notice;
import cn.doodlister.onlinejudge.entity.Problem;
import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.service.NoticeService;
import cn.doodlister.onlinejudge.service.ProblemService;
import cn.doodlister.onlinejudge.vo.NoticeVo;
import cn.doodlister.onlinejudge.vo.PageVo.PageResult;
import cn.doodlister.onlinejudge.vo.ProblemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;


@RestController
@RequestMapping("/problem")
@Api(tags = "problem")
public class ProblemContoller {

    @Autowired
    ProblemService problemService;

    /**
     * 添加新的习题
     * @param problem
     */
    @PostMapping
    @ApiOperation(value = "添加问题")
    @ResponseStatus(HttpStatus.CREATED)
    public ProblemVo addProblem(@RequestBody Problem problem){
        problem.setCreateTime(new Timestamp(new Date().getTime()));

        ProblemVo problemVo = problemService.saveProblem(problem);
        return problemVo;
    }

    /**
     * 分页查询问题
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "分页查询问题",notes="分页查询问题")
    @ResponseStatus(HttpStatus.OK)
    public PageResult<Problem> problemList(@RequestParam int page,
                                   @RequestParam int pageSize){
        Page<Problem> problem = problemService.findProblemByPage(page,pageSize);
        PageResult<Problem> pageResult = new PageResult<>(problem,Problem.class);
        return pageResult;
    }

    /**
     * 根据Id删除问题
     *
     */

    @DeleteMapping
    @ApiOperation(value="根据ID删除问题")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@RequestParam String id) throws NotFoundException {
        Problem problem = new Problem(id);
        problemService.delete(problem);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取指定ID Problem")
    @ResponseStatus(HttpStatus.OK)
    public ProblemVo getProblemById(@PathVariable("id") String id) throws NotFoundException {
        Problem problem = new Problem(id);
        problem = problemService.findByID(problem.getId());
        return new ProblemVo(problem);
    }


}
