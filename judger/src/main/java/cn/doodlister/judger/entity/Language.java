package cn.doodlister.judger.entity;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Language {
    private Integer id;
    private String languageName;
    private String compilerPath;
    private String compileCommand;
    private String compileEnv;
    private String fileName;
    private String runCommand;
}
