package cn.com.pzliu;

import cn.com.pzliu.spider.SpiderStart;

import java.io.IOException;

/**
 * @author Naive
 * @date 2024-02-21 10:35
 */
public class Start {


    private static SpiderStart spiderStart;

    static {
        try {
            spiderStart = new SpiderStart(1);
        } catch (IOException ignored) {

        }
    }


    public static void main(String[] args) {
        spiderStart.start();
    }

}
