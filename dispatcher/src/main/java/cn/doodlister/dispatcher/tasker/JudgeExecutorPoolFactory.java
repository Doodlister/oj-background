package cn.doodlister.dispatcher.tasker;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
@Service
public class JudgeExecutorPoolFactory {
    private static int coreNum = Runtime.getRuntime().availableProcessors();
    private static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(coreNum);
    private static ExecutorService executorService = new ThreadPoolExecutor(coreNum, coreNum,
            0, TimeUnit.SECONDS, linkedBlockingQueue,
            new JudgeThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    /**
     * 自定义ThreadFactory实现保证ThreadName有序...
     */
    static class JudgeThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(0);
        private final String namePrefix;

        JudgeThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "Judge-Thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
    public static ExecutorService getJudgerExecutorPool(){
        return executorService;
    }

    public static void main(String[] args) {
        ExecutorService judgerExecutorPool = JudgeExecutorPoolFactory.getJudgerExecutorPool();
        for(int i=0;i<17;++i){
            judgerExecutorPool.execute(new Runnable() {

                @SneakyThrows
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" : "+Thread.currentThread().getId());
                }
            });
        }

        //judgerExecutorPool.shutdownNow();
    }
}
