package cn.com.pzliu.spider;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Naive
 * @date 2024-02-21 10:35
 */
public class SpiderStart {

    private Spider scoreSpider = new ScoreSpider();

    private ScheduledExecutorService threadPool;
    public SpiderStart(int threadCount) throws IOException {
        threadPool = Executors.newScheduledThreadPool(threadCount);
    }



    public void start(){

        threadPool.scheduleAtFixedRate(scoreSpider,0,5, TimeUnit.MINUTES);

    }

}
