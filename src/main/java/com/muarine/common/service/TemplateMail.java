/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.muarine.common.service;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * TemplateMail.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年8月14日
 * @since 2.0
 */
public class TemplateMail {
	
	private JavaMailSender sender;

	public void setSender(JavaMailSender sender) {
		this.sender = sender;
	}

	/**
	 * 生成html模板字符串
	 * 
	 * @param root
	 *            存储动态数据的map
	 * @return
	 */
	private String getMailText(Map<String, Object> root, String templateName) {
		String htmlText = "";
		try {
			// 通过指定模板名获取FreeMarker模板实例
//			Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			
			Configuration cfg = new Configuration();  
            
            TemplateLoader templateLoader=null;  
              
            templateLoader=new ClassTemplateLoader(this.getClass(),"");  
              
            cfg.setTemplateLoader(templateLoader);  
            Template t=cfg.getTemplate(templateName,"UTF-8");  
			
			
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(t,root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlText;
	}

	/**
	 * 发送邮件
	 * 
	 * @param root
	 *            存储动态数据的map
	 * @param toEmail
	 *            邮件地址
	 * @param subject
	 *            邮件主题
	 * @return
	 */
	public boolean sendTemplateMail(Map<String, Object> root, String[] toEmail,
			String subject, String templateName) {
		try {
			MimeMessage msg = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, false,
					"utf-8");// 由于是html邮件，不是mulitpart类型
			helper.setFrom("maoyun0903@163.com");
			helper.setTo(toEmail);
			helper.setSubject(subject);
			String htmlText = getMailText(root, templateName);// 使用模板生成html邮件内容
			helper.setText(htmlText, true);
			sender.send(msg);
			// System.out.println("成功发送模板邮件");
			return true;
		} catch (MailException e) {
			// System.out.println("失败发送模板邮件");
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			// System.out.println("失败发送模板邮件");
			e.printStackTrace();
			return false;
		}

	}
}
