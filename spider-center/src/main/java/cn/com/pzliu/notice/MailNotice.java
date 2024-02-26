package cn.com.pzliu.notice;

import cn.com.pzliu.common.mail.MailHelper;
import cn.com.pzliu.domain.UserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.mail.MessagingException;

/**
 * @author Naive
 * @date 2024-02-23 12:10
 */
public class MailNotice implements Notice{
    private final Log logger = LogFactory.getLog(MailNotice.class);
    @Override
    public void notice(UserInfo userInfo,String notice,String subject) {
        try {
            logger.info("开始发送邮件");
            MailHelper.sendMail(userInfo.getEmail(),notice,subject);
        } catch (MessagingException e) {
            logger.error("发送邮件失败"+e.getMessage());
        }
    }
}
