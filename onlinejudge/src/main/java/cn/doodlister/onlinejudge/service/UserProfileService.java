package cn.doodlister.onlinejudge.service;

import cn.doodlister.onlinejudge.dao.UserProfileDao;
import cn.doodlister.onlinejudge.entity.User;
import cn.doodlister.onlinejudge.entity.UserProfile;
import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    UserProfileDao userProfileDao;

    public UserProfile findUserProfileByUserId(Integer userId) throws NotFoundException {
        if(userId == null || userId<0){
            throw new NotFoundException(Result.ErrorCode.USER_NOT_FOUND.getMsg(),
                    Result.ErrorCode.USER_NOT_FOUND.getCode());

        }

       return userProfileDao.findById(userId).get();
    }
}
