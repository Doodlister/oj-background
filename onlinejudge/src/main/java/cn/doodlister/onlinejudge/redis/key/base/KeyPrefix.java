package cn.doodlister.onlinejudge.redis.key.base;

public interface KeyPrefix {
    int expireSeconds();
    String getPrefix();
}
