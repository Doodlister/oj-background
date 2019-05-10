package cn.doodlister.onlinejudge.controller;

import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.exception.AuthenticationException;
import cn.doodlister.onlinejudge.exception.ParameterErrorException;
import cn.doodlister.onlinejudge.service.SessionService;
import cn.doodlister.onlinejudge.vo.Result;
import cn.doodlister.onlinejudge.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/session")
@Api(tags = "session")
public class SessionController extends BaseController {
    @Autowired
    SessionService sessionService;
    /*
    GET /collection：返回资源对象的列表（数组）
    GET /collection/resource：返回单个资源对象
    POST /collection：返回新生成的资源对象
    PUT /collection/resource：返回完整的资源对象
    PATCH /collection/resource：返回完整的资源对象
    DELETE /collection/resource：返回一个空文档
     */
    @PostMapping
    @ApiOperation(value = "登陆方法",notes="用户登陆")
    @ResponseStatus(HttpStatus.CREATED)
    public Result createSession(@RequestBody User user,
                                HttpServletResponse response) throws AuthenticationException, ParameterErrorException {
        String token = sessionService.login(user);

        response.setHeader("authorization",token);

        return new Result(token,HttpStatus.CREATED.value());
    }
}
