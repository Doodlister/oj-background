package cn.doodlister.onlinejudge.interceptor;

import cn.doodlister.onlinejudge.annotation.AccessLimit;
import cn.doodlister.onlinejudge.configuration.UserContext;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.exception.AuthenticationException;
import cn.doodlister.onlinejudge.redis.RedisService;
import cn.doodlister.onlinejudge.redis.key.UserKey;
import cn.doodlister.onlinejudge.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessInterceptor implements HandlerInterceptor {
    @Autowired
    RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            User user = getUser(request, response);
            UserContext.getUserHolder().set(user);
            HandlerMethod hm = (HandlerMethod)handler;
            AccessLimit annotation = hm.getMethodAnnotation(AccessLimit.class);
            if(annotation !=null){
                if(annotation.needLogin()) {
                    if (user == null) {
                        throw new AuthenticationException(Result.ErrorCode.TOKEN_EXPIRED.getMsg(),
                                Result.ErrorCode.TOKEN_EXPIRED.getCode());
                    }
                }
            }

            return true;
        }
        return true;
    }
    private User getUser(HttpServletRequest request, HttpServletResponse response){

        String token = request.getHeader("authorization");
        if(!StringUtils.isEmpty(token)){
            UserKey userKey = new UserKey("token");
            User user = redisService.get(userKey,token,User.class);
            return user;

        }
        return null;
    }
    private void render(HttpServletResponse response, Result result){
        response.setContentType("application/json;charset=UTF-8");
    }
}
