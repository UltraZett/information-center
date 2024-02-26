package cn.com.pzliu.spider;

import cn.com.pzliu.common.httpclient.HttpHelper;
import cn.com.pzliu.domain.UserInfo;
import cn.com.pzliu.notice.MailNotice;
import cn.com.pzliu.notice.Notice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Naive
 * @date 2024-02-21 9:58
 */

public class ScoreSpider implements Spider {

    private final Log logger = LogFactory.getLog(ScoreSpider.class);

    private static final String url = "https://yz.chsi.com.cn/apply/cjcx/cjcx.do";

    private static final Notice mailNotice = new MailNotice();

    private final Properties properties;

    public ScoreSpider() throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/user.properties");
        properties = new Properties();
        properties.load(resourceAsStream);
    }

    @Override
    public void run() {
        logger.info("开始查询");
        Map<String,String> map = new HashMap<>();
        String reponse = null;
        map.put("xm","刘沛泽");
        map.put("zjhm",this.properties.getProperty("zjhm"));
        map.put("ksbh",this.properties.getProperty("ksbh"));
        map.put("bkdwdm",this.properties.getProperty("bkdwdm"));
        map.put("checkcode","");
        try {
            reponse = HttpHelper.post(url,map);
        } catch (IOException e) {
            logger.error("http请求失败"+e.getMessage());
        }

        if (!StringUtils.isEmpty(reponse)){
           if (!reponse.contains("无查询结果")){
               UserInfo userInfo = new UserInfo("981104419@qq.com","pzliu");
               logger.info("查询成功"+reponse);
               mailNotice.notice(userInfo,reponse,"成绩查询");
           }else {
               // 查询完成 无结果
               logger.info("查询完成，无结果");
           }

        }
    }
}
