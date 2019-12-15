package cn.doodlister.judger.tasker;

import cn.doodlister.judger.entity.ExecuteResult;
import cn.doodlister.judger.exception.CompileException;
import cn.doodlister.judger.exception.EnvironmentalErrorException;
import cn.doodlister.judger.exception.RunException;
import cn.doodlister.judger.exception.WrongAnswerException;
import cn.doodlister.judger.mq.MQSender;
import cn.doodlister.judger.service.ExecuteService;
import cn.doodlister.judger.service.JudgeService;
import cn.doodlister.judger.util.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;


public class JudgeTask  implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(JudgeTask.class);
    @Autowired
    JudgeService judgeService;
    @Autowired
    MQSender mqSender;
    private String submissionId;
    public JudgeTask(String submissionId){
        this.submissionId = submissionId;
        this.judgeService =  SpringContext.getApplicationContext().getBean(JudgeService.class);
        this.mqSender =  SpringContext.getApplicationContext().getBean(MQSender.class);
    }
    @Override
    public void run() {
        try {
            ExecuteResult result = judgeService.judge(submissionId);
            HashMap<String,String> map = new HashMap<>();
            map.put("event","success");
            map.put("realTime",result.getRealTime()+"");
            map.put("time",result.getCpuTime()+"");
            map.put("memory",result.getMemory()+"");
            mqSender.sendMessage(map);
        } catch (EnvironmentalErrorException e) {
            logger.info("[submission id = "+submissionId+"] initialize Env error " + e.getMessage());
            HashMap<String,String> map = new HashMap<>();
            map.put("event","EnvironmentalErrorException");
            map.put("msg",e.getMessage());
            mqSender.sendMessage(map);
        }catch (CompileException e) {
            logger.info("[submission id = "+submissionId+"] compile error " + e.getMessage());
            HashMap<String,String> map = new HashMap<>();
            map.put("event","CompileException");
            map.put("msg",e.getMessage());
            mqSender.sendMessage(map);
        }catch (RunException e) {
            ExecuteResult executeResult = e.getExecuteResult();
            logger.info("[submission id = "+submissionId+"] run error " + e.getMessage() +
                    " time used:" +executeResult.getCpuTime() + " memory used:" + executeResult.getMemory()/ ExecuteService.STANDARD_MB);
            HashMap<String,String> map = new HashMap<>();
            map.put("event","RunException");
            map.put("msg",e.getMessage());
            mqSender.sendMessage(map);
        }catch (WrongAnswerException e) {
            logger.info("[submission id = "+submissionId+"] wrong answer " + e.getMessage());
            HashMap<String,String> map = new HashMap<>();
            map.put("event","WrongAnswerException");
            map.put("msg",e.getMessage());
            mqSender.sendMessage(map);
        }catch (Exception e){
            logger.error("[submission id = "+submissionId+"] error " + e.getMessage());
            HashMap<String,String> map = new HashMap<>();
            map.put("event","Exception");
            map.put("msg",e.getMessage());
            mqSender.sendMessage(map);
        }
    }
}
