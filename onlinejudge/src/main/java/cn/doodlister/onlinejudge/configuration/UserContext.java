package cn.doodlister.onlinejudge.configuration;

import cn.doodlister.onlinejudge.entity.User;

public class UserContext {
    private static ThreadLocal<User> userHolder = new ThreadLocal<User>();

    public static ThreadLocal<User> getUserHolder() {
        return userHolder;
    }

    public static void setUserHolder(ThreadLocal<User> userHolder) {
        UserContext.userHolder = userHolder;
    }
}
