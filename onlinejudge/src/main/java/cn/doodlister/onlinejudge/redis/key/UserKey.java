package cn.doodlister.onlinejudge.redis.key;

import cn.doodlister.onlinejudge.redis.key.base.BasePrefix;

public class UserKey extends BasePrefix {
    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
