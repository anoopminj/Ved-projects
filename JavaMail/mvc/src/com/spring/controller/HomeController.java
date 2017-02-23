package com.spring.controller;

import javax.mail.MessagingException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.adapator.EmailAdapator;
@Controller
public class HomeController implements ApplicationContextAware {
	ApplicationContext ctx;
	 EmailAdapator emailAdapator;
	 
	 @Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			ctx=applicationContext;
			emailAdapator=(EmailAdapator)ctx.getBean("emailAdapator123");
		}
	 @RequestMapping(value="/",method=RequestMethod.GET)
	public String showHome(ModelMap model) throws MessagingException
	{
		 model.addAttribute("message", "Your are in Home Controller ");
		 emailAdapator.setUpMailer();
		 System.out.println(emailAdapator.getHOST() +"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Vedprakash>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+emailAdapator.getPORT());
		 Boolean status=emailAdapator.sendNotification();
		 if (status==true)
			 System.out.println("Sent Sucess");
		 return "home";
				
	}

	
	 
	 
	 
}