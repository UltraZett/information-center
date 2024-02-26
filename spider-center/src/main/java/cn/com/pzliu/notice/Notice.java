package cn.com.pzliu.notice;

import cn.com.pzliu.domain.UserInfo;

/**
 * @author Naive
 * @date 2024-02-23 12:10
 */
public interface Notice {
    /**
     * 通知
     * @param userInfo
     */
    void notice(UserInfo userInfo,String notice,String subject);
}
