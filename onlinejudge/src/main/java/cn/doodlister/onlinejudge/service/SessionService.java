package cn.doodlister.onlinejudge.service;

import cn.doodlister.onlinejudge.configuration.UserContext;
import cn.doodlister.onlinejudge.dao.UserDao;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.exception.AuthenticationException;
import cn.doodlister.onlinejudge.exception.GlobalException;
import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.exception.ParameterErrorException;
import cn.doodlister.onlinejudge.redis.RedisService;
import cn.doodlister.onlinejudge.redis.key.UserKey;
import cn.doodlister.onlinejudge.redis.key.base.BasePrefix;
import cn.doodlister.onlinejudge.redis.key.base.KeyPrefix;
import cn.doodlister.onlinejudge.utils.Util;
import cn.doodlister.onlinejudge.vo.Result;
import cn.doodlister.onlinejudge.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {
   @Autowired
   UserService userService;
   @Autowired
   RedisService redisService;

    /**
     *
     * @param user
     * @return UserToken
     * @throws AuthenticationException
     * @throws ParameterErrorException
     */
    public String login( User user) throws AuthenticationException, ParameterErrorException {
        if(StringUtils.isEmpty(user.getUsername())
                || StringUtils.isEmpty(user.getPassword())){
            throw new ParameterErrorException(Result.ErrorCode.PARAMETER_ERROR.getMsg(),
                    Result.ErrorCode.PARAMETER_ERROR.getCode());
        }
        User dbUser = userService.findUserByUsername(user.getUsername());
        if(dbUser != null && dbUser.getPassword().equals(user.getPassword())){
            String token = Util.generateUUID();
            KeyPrefix userKey = new UserKey(UserKey.ExpireTime.ONE_DAY.getExpire(),"token");
            redisService.set(userKey,token,dbUser);
            return token;

        }

        else
            throw new AuthenticationException(Result.ErrorCode.USER_NAME_ERROR_OR_PASSWORD_ERROR.getMsg()
                    ,Result.ErrorCode.USER_NAME_ERROR_OR_PASSWORD_ERROR.getCode());
    }
}
