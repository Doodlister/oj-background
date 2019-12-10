package cn.doodlister.judger.service;

import org.junit.Test;

public class BaseServiceTest {
    @Test
    public void test(){
        String a="   \n d";
        System.out.println(a.length());
        System.out.println(a.trim().length());
        System.out.println(a);
    }

}