package cn.doodlister.judger.dao;

import cn.doodlister.judger.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProblemMapper {

    @Select("SELECT * FROM problem where id = #{submitProblemId}")
    Problem findProblemById(int submitProblemId);
}
