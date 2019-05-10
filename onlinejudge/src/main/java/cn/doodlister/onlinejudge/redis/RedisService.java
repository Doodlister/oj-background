package cn.doodlister.onlinejudge.redis;

import cn.doodlister.onlinejudge.redis.key.base.KeyPrefix;
import cn.doodlister.onlinejudge.utils.Util;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;
    public <T> T get(KeyPrefix keyPrefix,String key,Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String beanString = jedis.get(keyPrefix.getPrefix()+":"+key);
            T t = Util.stringToBean(beanString,clazz);
            return t;
        }
        finally {
            returnToPool(jedis);
        }

    }
    public <T> Boolean set(KeyPrefix keyPrefix,String key,T value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = Util.beanToString(value);
            if(str == null || str.length()<=0){
                return false;
            }
            int expireSeconds = keyPrefix.expireSeconds();
            String realKey = keyPrefix.getPrefix()+":"+key;
            if(expireSeconds<=0){
                jedis.set(realKey,str);
            }else {
                jedis.setex(realKey,expireSeconds,str);

            }

            return true;
        }finally {
            returnToPool(jedis);
        }
    }
    public Boolean exists(KeyPrefix keyPrefix,String key){
        Jedis jedis = jedisPool.getResource();
        try{
            String realKey = keyPrefix.getPrefix()+key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
    public Long incr(KeyPrefix keyPrefix, String key){
        Jedis jedis = jedisPool.getResource();
        try{
            String realKey = keyPrefix.getPrefix()+key;

            return  jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
    public Long decr(KeyPrefix keyPrefix,String key){
        Jedis jedis = jedisPool.getResource();
        try{
            String realKey = keyPrefix.getPrefix()+key;

            return  jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }




    private void returnToPool(Jedis jedis) {
        if(jedis !=null)
            jedis.close();
    }


}
