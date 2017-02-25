package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.adapator.EmailAdapator;
import com.spring.jms.DSAdminEventSender;
@Controller
public class HomeController implements ApplicationContextAware {
	ApplicationContext ctx;
	DSAdminEventSender ds2AdminEventSender;
	EmailAdapator emailAdapator;
	 
	 @Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			ctx=applicationContext;
			emailAdapator=(EmailAdapator)ctx.getBean("emailAdapator");
			ds2AdminEventSender=(DSAdminEventSender) ctx.getBean("ds2AdminEventSender");
		}
	 @RequestMapping(value="/",method=RequestMethod.GET)
	public String showHome(ModelMap model) throws MessagingException
	{
		 model.addAttribute("message", "Your are in Home Controller ");
//		 emailAdapator.setUpMailer();
//		 System.out.println(emailAdapator.getHOST() +"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Vedprakash>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+emailAdapator.getPORT());
//		 emailAdapator.sendNotification();
		 Map<String,String> theMessage= new HashMap<String, String>();
			theMessage.put("name","Hii Vedprkash You are Genius");
		 ds2AdminEventSender.sendMessage(theMessage);
		 System.out.println("Sent Sucess");

		 return "home";
				
	}
	public void process(String name) {
		// TODO Auto-generated method stub
		System.out.println("Sent Sucess"+name);
		model.addAttribute("msg", "sucess");
	}

	
	 
	 
	 
}