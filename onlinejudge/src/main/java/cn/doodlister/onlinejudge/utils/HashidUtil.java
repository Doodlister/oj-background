package cn.doodlister.onlinejudge.utils;

import cn.doodlister.onlinejudge.exception.NotFoundException;
import cn.doodlister.onlinejudge.vo.Result;
import org.hashids.Hashids;



public class HashidUtil {
    private static final Hashids hashids = new Hashids("#IEWQHJNGRSWD（U*65as465df45vsadfw317");
    public static String encode(int id){
        return hashids.encode(id);
    }
    public static int decode(String id) throws NotFoundException {
        try {
            return (int)hashids.decode(id)[0];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new NotFoundException(Result.ErrorCode.NOTICE_NOT_FOUND.getMsg()
                    ,Result.ErrorCode.NOTICE_NOT_FOUND.getCode());
        }

    }
}
