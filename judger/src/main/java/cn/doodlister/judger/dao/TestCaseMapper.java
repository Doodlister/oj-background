package cn.doodlister.judger.dao;

import cn.doodlister.judger.entity.TestCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestCaseMapper {
        @Select("SELECT tc.id,tc.input,tc.output,tc.problem_id FROM test_case as tc " +
            "INNER JOIN submission as sub ON tc.problem_id = sub.submit_problem_id WHERE sub.id =#{submissionId}")
        List<TestCase> findTestCasesBySubmissionId(@Param("submissionId") Integer submissionId);
}
