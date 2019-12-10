package cn.doodlister.judger.dao;

import cn.doodlister.judger.entity.Submission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SubmissionMapper {
    @Select("SELECT * FROM submission where id  = #{submissionId}")
    Submission findSubmissionById(@Param("submissionId") Integer submissionId);
}
