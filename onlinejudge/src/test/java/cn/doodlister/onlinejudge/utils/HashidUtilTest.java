package cn.doodlister.onlinejudge.utils;

import cn.doodlister.onlinejudge.exception.NotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashidUtilTest {

    @Test
    public void encode() {
        System.out.println(HashidUtil.encode(123));

    }

    @Test
    public void decode() throws NotFoundException {
        System.out.println(HashidUtil.decode("Ald"));
    }
}