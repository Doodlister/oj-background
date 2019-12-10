package cn.doodlister.judger.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class TestCase {

    private Integer id;



    private String input;



    private String output;


    private Problem problem;
}
