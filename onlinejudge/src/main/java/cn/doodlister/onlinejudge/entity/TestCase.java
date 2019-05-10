package cn.doodlister.onlinejudge.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
public class TestCase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Integer id;


    @Column(name = "input")
    private String input;


    @Column(name = "output")
    private String output;

    @JSONField(serialize = false)
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "problem_id")
    private Problem problem;
}
