package com.spring.jms;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.spring.controller.HomeController;

public class DSAdminEventReceiver implements MessageListener,ApplicationContextAware {
	ApplicationContext ctx;
	HomeController homeController;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		ctx = applicationContext;
		homeController=(HomeController) ctx.getBean("homeController");
	}
	public void onMessage(Message msg) {
		try{
			MapMessage mapMsg = (MapMessage) msg;
			String name=(String) mapMsg.getObject("name");
			homeController.process(name);
		}catch(Exception E)
		{
			E.printStackTrace();
		}
	}
		
}
