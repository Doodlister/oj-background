package cn.doodlister.onlinejudge.service;

import cn.doodlister.onlinejudge.dao.UserDao;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.exception.ParameterErrorException;
import cn.doodlister.onlinejudge.utils.HashidUtil;
import cn.doodlister.onlinejudge.vo.Result;
import cn.doodlister.onlinejudge.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;


@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User findUserByUsername(String username){
        return userDao.findByUsername(username);
    }

    public User addUser(User user) throws ParameterErrorException, NotFoundException {
        Date nowDate = new Date();
        if(user.getUsername() == null || user.getPassword() == null || user.getEmail() == null){
            throw new ParameterErrorException(Result.ErrorCode.PARAMETER_ERROR.getMsg(),
                    Result.ErrorCode.PARAMETER_ERROR.getCode());
        }
        if(findUserByUsername(user.getUsername())!=null){
            throw new NotFoundException(Result.ErrorCode.USER_ALREADY_EXIST.getMsg(),
                    Result.ErrorCode.USER_ALREADY_EXIST.getCode());
        }
        user.setIsDisabled(false);
        user.setCreateTime(new Timestamp(nowDate.getTime()));
        user.setSalt("");
        return userDao.save(user);

    }
}
