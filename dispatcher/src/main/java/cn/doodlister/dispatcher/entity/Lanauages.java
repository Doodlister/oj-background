package cn.doodlister.dispatcher.entity;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Lanauages {
    private Integer id;
    private String languageName;
    private String compileCommand;
    private String fileName;
    private String runCommand;
}
