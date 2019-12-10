package cn.doodlister.judger.tasker;

import cn.doodlister.judger.entity.Submission;
import cn.doodlister.judger.exception.CompileException;
import cn.doodlister.judger.exception.EnvironmentalErrorException;
import cn.doodlister.judger.exception.RunException;
import cn.doodlister.judger.exception.WrongAnswerException;
import cn.doodlister.judger.service.JudgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class JudgeTask  implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(JudgeTask.class);
    @Autowired
    JudgeService judgeService;

    private String submissionId;
    public JudgeTask(String submissionId){
        this.submissionId = submissionId;
    }
    @Override
    public void run() {
        try {
            judgeService.judge(submissionId);
        } catch (EnvironmentalErrorException e) {
            logger.error("[submission id = "+submissionId+"] initialize Env error " + e.getMessage());
            //发送消息
        }catch (CompileException e) {
            logger.error("[submission id = "+submissionId+"] compile error " + e.getMessage());
            //发送消息
        }catch (RunException e) {
            logger.error("[submission id = "+submissionId+"] run error " + e.getMessage());
        }catch (WrongAnswerException e) {
            logger.error("[submission id = "+submissionId+"] wrong answer error " + e.getMessage());
        }catch (Exception e){
            logger.error("[submission id = "+submissionId+"] error " + e.getMessage());
        }
    }
}
