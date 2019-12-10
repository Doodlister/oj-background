package cn.doodlister.dispatcher.dao;

import cn.doodlister.dispatcher.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProblemMapper {

    @Select("SELECT * FROM problem where id = #{submitProblemId}")
    Problem findProblemById(int submitProblemId);
}
