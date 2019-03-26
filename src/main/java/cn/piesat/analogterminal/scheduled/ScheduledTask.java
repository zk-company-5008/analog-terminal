package cn.piesat.analogterminal.scheduled;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author zk
 * @date 2019/2/15 17:12
 */
public class ScheduledTask {
    private static final ScheduledExecutorService scheduled = new ScheduledThreadPoolExecutor(50);

    public static ScheduledExecutorService getInstance() {
        return scheduled;
    }
}
