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
    private Submission submission;
    public JudgeTask(Submission submission){
        this.submission = submission;
    }
    @Override
    public void run() {
        try {
            judgeService.judge(submission);
        } catch (EnvironmentalErrorException e) {
            logger.error("[submission id = "+submission.getId()+"] initialize Env error " + e.getMessage());
            //发送消息
        }catch (CompileException e) {
            logger.error("[submission id = "+submission.getId()+"] compile error " + e.getMessage());
            //发送消息
        }catch (RunException e) {
            logger.error("[submission id = "+submission.getId()+"] run error " + e.getMessage());
        }catch (WrongAnswerException e) {
            logger.error("[submission id = "+submission.getId()+"] wrong answer error " + e.getMessage());
        }catch (Exception e){
            logger.error("[submission id = "+submission.getId()+"] error " + e.getMessage());
        }
    }
}
