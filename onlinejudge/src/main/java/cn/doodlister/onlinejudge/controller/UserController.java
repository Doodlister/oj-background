package cn.doodlister.onlinejudge.controller;

import cn.doodlister.onlinejudge.annotation.AccessLimit;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.entity.UserProfile;
import cn.doodlister.onlinejudge.exception.AuthenticationException;
import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.exception.ParameterErrorException;
import cn.doodlister.onlinejudge.service.SessionService;
import cn.doodlister.onlinejudge.service.UserProfileService;
import cn.doodlister.onlinejudge.service.UserService;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import cn.doodlister.onlinejudge.vo.Result;
import cn.doodlister.onlinejudge.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    UserProfileService userProfileService;
    /**
     * 新增用户
     * @param user
     * @return
     * @throws AuthenticationException
     */
    @PostMapping
    @ApiOperation(value = "新增用户",notes="新增用户")
    @ResponseStatus(HttpStatus.CREATED)
    public UserVo addUser(@RequestBody User user) throws AuthenticationException, ParameterErrorException, NotFoundException {

        User u =userService.addUser(user);
        UserVo userVo = new UserVo(u);
        return userVo;
    }

    @GetMapping
    @ApiOperation(value="查新指定的用户是否存在",notes = "根据用户名查询指定的用户是否存在，code 40401用户不存在,40001 用户已存在")
    @ResponseStatus(HttpStatus.OK)
    public Result isUserExist(@RequestParam String username){
        User user = userService.findUserByUsername(username);
        if(user != null){
            return new Result(Result.ErrorCode.USER_ALREADY_EXIST.getMsg(),
                    Result.ErrorCode.USER_ALREADY_EXIST.getCode());
        }else{
            return new Result(Result.ErrorCode.USER_NOT_FOUND.getMsg(),
                    Result.ErrorCode.USER_NOT_FOUND.getCode());
        }
    }

    @GetMapping("/profile")
    @ApiOperation(value="根据ID查用户资料")
    @ResponseStatus(HttpStatus.OK)
    @AccessLimit(needLogin = true)
    public UserVo findUserProfile(User user) throws NotFoundException {
        Integer uid = user.getId();
        UserProfile userProfile = null;
        try{
           userProfile = userProfileService.findUserProfileByUserId(uid);
        }catch (Exception e){

        }

        UserVo userVo=new UserVo(user);
        userVo.setUserProfile(userProfile);
        return userVo;
    }

    @GetMapping("/reword")
    @ApiOperation(value="更改用户密码")
    @ResponseStatus(HttpStatus.OK)
    @AccessLimit(needLogin = true)
    public UserVo reword(User user) throws NotFoundException {
        Integer uid = user.getId();
        UserProfile userProfile = null;
        try{
            userProfile = userProfileService.findUserProfileByUserId(uid);
        }catch (Exception e){

        }

        UserVo userVo=new UserVo(user);
        userVo.setUserProfile(userProfile);
        return userVo;
    }


}
