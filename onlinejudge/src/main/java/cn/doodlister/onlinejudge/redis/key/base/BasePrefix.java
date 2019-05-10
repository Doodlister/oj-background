package cn.doodlister.onlinejudge.redis.key.base;


import lombok.Getter;

public abstract class BasePrefix implements KeyPrefix{
    public static  enum ExpireTime{
        ONE_MINUMTE(60),
        ONE_HOUR(ONE_MINUMTE.getExpire()*60),
        ONE_DAY(ONE_HOUR.getExpire()*24),
        ONE_WAKE(ONE_DAY.getExpire()*7);

        @Getter
        private int expire;
        private ExpireTime(int expire){
            this.expire = expire;
        }

    }
    private int expireSeconds;
    private String prefix;
    public BasePrefix(String prefix){
        this(0,prefix);
    }
    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return this.getClass().getSimpleName() + ":"+prefix;
    }

}
