package cn.doodlister.judger.dao;

import cn.doodlister.judger.entity.Language;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface LanguageMapper {
    @Select("SELECT * FROM `language` WHERE language_name = #{name}")
    Language findLanguageByName(String name);
}
