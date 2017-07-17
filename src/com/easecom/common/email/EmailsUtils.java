/**
 * EmailsUtils.java   2012-5-25 下午1:49:24
 * Copyright:  Copyright (c) 2011
 * Company:山东益信通科贸有限公司
 */

package com.easecom.common.email;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;


/**
 *
 * @Description 邮件发送
 *
 * @author YuPeng
 * @date 2012-5-25 下午1:49:24
 */

public class EmailsUtils {
	public static Logger logger = Logger.getLogger(EmailsUtils.class);
	//Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
	private  Multipart mp = new MimeMultipart();
	private  final String CHAR_SET = "UTF-8";    
	
	/**
	 * 
	 * @Description:  发送邮件
	 * @param EmailParaForm 发件人等发送邮件参数
	 * @return  
	 * @author: YuPeng
	 * @date 2012-5-25 下午5:13:36
	 */
	public  boolean SendEmails(EmailParaForm emailParaForm){
			boolean isSucc = false;
			if (emailParaForm == null){
				return isSucc;
			}
			try{
				//JavaMail需要Properties来创建一个session对象。它将寻找字符串"mail.smtp.host"，属性值就是发送邮件的主机.
				//Properties对象获取诸如邮件服务器、用户名、密码等信息，以及其他可在整个应用程序中 共享的信息。
				Properties props=new Properties();//也可用Properties props = System.getProperties();
				props.put("mail.smtp.host",emailParaForm.getStmpHost());//存储发送邮件服务器的信息
				props.put("mail.smtp.port", "" + emailParaForm.getTport()); // 端口号
				props.put("mail.transport.protocol",emailParaForm.getProtocol());
				props.put("mail.smtp.auth",emailParaForm.getNeedAuth());//同时通过验证
		        if (null != emailParaForm.getAgentIp() && emailParaForm.getAgentIp().length() != 0 && !emailParaForm.getAgentIp().equals("0")) {
		            props.put("mail.agentIp", emailParaForm.getAgentIp());
		        }

		        if (null != emailParaForm.getAgentPort() && emailParaForm.getAgentPort().length() != 0 && !emailParaForm.getAgentPort().equals("0")) {
		            props.put("mail.agentPort", emailParaForm.getAgentPort());
		        }

				//方法二：(如果是在weblogin配置JavaMail：则需指定JNDI名检索
				//Context ctx=new InitialContext();
				//Session s=(Session)ctx.lookup("MailSession");
				//Message msg=new MimeMessage(s);
	
	
				//这个Session类代表JavaMail 中的一个邮件session. 每一个基于 JavaMail的应用程序至少有一个session但是可以有任意多的session。
				//Session类定义全局和每个用户的与邮件相关的属性。这此属性说明了客房机和服务器如何交流信息。
			    //Authenticator也是一个抽象类，你需要自己编写子类已备应用。
			    
		        Session s = Session.getInstance(props,null);//根据属性新建一个邮件会话，null参数是一种Authenticator(验证程序) 对象
				String isDebug = emailParaForm.getIsDebug();
				if (isDebug != null && isDebug.trim().equalsIgnoreCase("true")){
					s.setDebug(true);//设置调试标志,要查看经过邮件服务器邮件命令，可以用该方法
				}
				//  一旦创建了自己的Session对象，就是该去创建要发送的消息的 时候了。这时就要用到消息类型(MimeMessage是其中一种类型)。
				//　Message对象将存储我们实际发送的电子邮件信息，Message对象被作为一个MimeMessage对象来创建并且需要知道应当选择哪一个JavaMail session。
				//  Message类表示单个邮件消息，它的属性包括类型，地址信息和所定义的目录结构。
	
				Message message=new MimeMessage(s);//由邮件会话新建一个消息对象
	
				//message.setContent("hello","test/plain");//设置消息的内容类型,如果发送的格式有HTML格式就必须设置，
				//message.setText("Hello");//发送一般文本格式的消息
	
				//设置邮件,一旦您创建了 Session 和 Message，并将内容填入消息后，就可以用Address确定信件地址了。
				//如果想让一个名字出现在电子邮件地址后，也可以将其传递给构造器：
				//Address from=new InternetAddress("xmqds@21cn.com","qdison");//发件人的邮件地址
	
				Address from=new InternetAddress(emailParaForm.getTfrom());//发件人的邮件地址
				message.setFrom(from);//设置发件人
	
				Address to=new InternetAddress(emailParaForm.getTto());//收件人的邮件地址
				message.setRecipient(Message.RecipientType.TO,to);//设置收件人,并设置其接收类型为TO,还有3种预定义类型如下：
	
				//Message.RecipientType.TO　　收件人
				//Message.RecipientType.CC　　抄送
				//Message.RecipientType.BCC　 暗送
				message.setSubject(emailParaForm.getTtitle());//设置主题
				message.setSentDate(new Date());//设置发信时间
				setBody(emailParaForm.getTcontent());
				
				//String  fileAttachment   =   "C:\\测试\\中国.txt";  
				//添加附件
				String fileAttachments = emailParaForm.getFileAttachments();
				if(null!=fileAttachments){
					String[] fileAttArr = fileAttachments.split(";");
					for (int i = 0; i < fileAttArr.length; i++){
						String fileAtt = fileAttArr[i];
						addFileAffix(fileAtt);
					}
				}
				
				message.setContent(mp);
				message.saveChanges();//存储邮件信息
				// Transport 是用来发送信息的，
				// 用于邮件的收发打操作。
				Transport transport=s.getTransport(emailParaForm.getProtocol());
				transport.connect(emailParaForm.getStmpHost(),emailParaForm.getUserName(),emailParaForm.getPassword());//以smtp方式登录邮箱
				transport.sendMessage(message,message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
				transport.close();
			
				isSucc = true;;
		} catch(MessagingException e){
			logger.error(e);
			e.printStackTrace();
		} 
		return isSucc;
	}
	
	
    /**
     * 邮件添加附件
     *
     * @param filePath 包括路径名的完整本地文件名
     * @return boolean
     */
    public boolean addFileAffix(String filePath) {
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filePath);
            bp.setDataHandler(new DataHandler(fileds));
            String fileNm = fileds.getName();
            bp.setFileName(MimeUtility.encodeText(fileNm));
            mp.addBodyPart(bp);
            return true;
        }
        catch (Exception e) {
			logger.error(e);
            System.err.println("Affix" + filePath + "accour error" + e);
            return false;
        }
    }

    /**
     * 设置邮件内容
     *
     * @param body 邮件内容
     * @return boolean
     */
    public  boolean setBody(String body) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent("" + body, "text/html;charset=" + CHAR_SET);
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
			logger.error(e);
            System.err.println("set mail body is error" + e);
            return false;
        }
    }
    
    /**
     * 发送邮件
     * @param receiveUser 接收人
     * @param title 标题
     * @param content 内容
     * 返回true则发送成功，false发送失败
     * @return
     */
    public  boolean send(String receiveUser,String title,String content){
    	boolean isSucc = false;
    	EmailParaMgr mgr = new EmailParaMgr();
    	EmailParaForm form = mgr.initAdd();
    	form.setTto(receiveUser);
    	form.setTcontent(content);
    	form.setTtitle(title);
    	String formPass = form.getPassword();
    	if (formPass != null ){
    		isSucc=SendEmails(form);
    	}
    	return isSucc;
    }
    
    
    public static void main(String[] args){
    	EmailsUtils e = new EmailsUtils();
    	e.send("1011803084@qq.com", "维基百科", "，自由的百科全书：维基百科是一个内容自由、任何人都能参与、并有多种语言的百科全书协作计划。我们的目标是建立一个完整、准确和中立的百科全书。 所有文字内容在CC-BY-SA-3.0...");
    }
}