package com.spring.adapator;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailAdapator {
	private static String HOST = "smtp.gmail.com";
	private static String USERNAME = "xxxxxx@gmail.com";
	private static String PASSWORD = "xxxxxxxxx";
	private static int PORT = 587;
	private static String to = "pyadu8535@gmail.com";
	private static String from = "vedprakash.sahu143@gmail.com";
	private static String subject = "Testing javaMail";
	protected JavaMailSenderImpl mailSender;

	public void setUpMailer() {
		System.out.println("<<<<<<<<<<<TLSEmail Start>>>>>>>>>>>>>>>>>>>>");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

		mailSender = new JavaMailSenderImpl();
		// mailSender.setHost(HOST);
		mailSender.setUsername(USERNAME);
		mailSender.setPassword(PASSWORD);
		// mailSender.setPort(PORT);
		mailSender.setJavaMailProperties(props);

	}

	public static String getHOST() {
		return HOST;
	}

	public static void setHOST(String hOST) {
		HOST = hOST;
	}

	public static int getPORT() {
		return PORT;
	}

	public static void setPORT(int pORT) {
		PORT = pORT;
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public boolean sendNotification() throws MessagingException {
		// String attachmentName = "Interview Call Letter";
		// String attachmentFilename = "C:/Users/VED/Desktop/call
		// letter/marlabs.pdf";
		String body = "";

		try {

			MimeMessage message = mailSender.createMimeMessage();

			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setFrom(from == null ? "no-reply" : from);
			helper.setSubject(subject == null ? "no-subject" : subject);
			// use the true flag to indicate the text included is HTML
			if (body != null)
				helper.setText(body, false);

			// add attachment; not inline
			/*
			 * if ((attachmentName != null) && (attachmentFilename != null)) {
			 * helper.addAttachment(attachmentName, new
			 * File(attachmentFilename)); }
			 */
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return true;
	}

}
