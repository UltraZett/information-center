package cn.com.pzliu.common.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Naive
 * @date 2024-02-23 12:08
 */
public class MailHelper {

    private static Session session;

    private static  String PASSWPRD ;

    private static  String SENDADDRESS;

    private static final Object lock = new Object();

    private static void buildSession() throws IOException {
        InputStream resourceAsStream = MailHelper.class.getResourceAsStream("/mail.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        SENDADDRESS = properties.getProperty("mail.sendaddress");
        PASSWPRD = properties.getProperty("mail.password");
        session = Session.getInstance(properties);
    }


    /**
     *  邮件发送
     * @param address
     * @param info
     */
    public static void sendMail(String address,String info,String subject) throws MessagingException {

        synchronized (lock){
            if (session == null){
                try {
                    buildSession();
                } catch (IOException e) {
                    System.out.println("读取配置文件失败");
                }
            }
        }
        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect(SENDADDRESS,PASSWPRD);
            MimeMessage message = new MimeMessage(session);

            message.setSubject(subject);
            message.setText(info);
            message.setFrom(new InternetAddress(SENDADDRESS));
            transport.sendMessage(message,new Address[]{new InternetAddress(address)});
        }finally {
            if (transport !=null){
                transport.close();
            }
        }
    }

}
