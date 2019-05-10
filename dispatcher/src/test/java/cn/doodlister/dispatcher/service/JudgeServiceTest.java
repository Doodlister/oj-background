package cn.doodlister.dispatcher.service;

import lombok.ToString;
import org.junit.Test;

import static org.junit.Assert.*;

public class JudgeServiceTest {
    @Test
    public void test(){
        String a="   \n d";
        System.out.println(a.length());
        System.out.println(a.trim().length());
        System.out.println(a);
    }

}