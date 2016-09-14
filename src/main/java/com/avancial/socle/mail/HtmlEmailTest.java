package com.avancial.socle.mail;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.subethamail.wiser.Wiser;

public class HtmlEmailTest {
	
	Wiser smtpServer = new Wiser();
	
	@Test
	public void envoieMail() throws Exception {		
		HtmlEmail email = new HtmlEmail(new Email());
		email.setType(TypeEmail.HTML);
		email.setSujet("test");
		email.setContenu("ceci est un test au format html");
		email.send("sebastien.benede@avancial.com", "was@avancial.com");
		
		assertNotNull(this.smtpServer.getMessages());
		assertTrue(this.smtpServer.getMessages().toString().contains("text/html"));
		assertTrue(this.smtpServer.getMessages().toString().contains("ceci est un test au format html"));
		assertTrue(this.smtpServer.getMessages().toString().contains("<body>"));
	}
	
	@Before
	public void beforeTest(){
		this.smtpServer.setPort(8090);
		this.smtpServer.start();
	}
	@After
	public void afterTest(){
		this.smtpServer.stop();
	}
}
