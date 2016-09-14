package com.avancial.socle.mail;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.subethamail.wiser.Wiser;

public class EmailTest {

	Wiser smtpServer = new Wiser();

	@Test
	public void envoieMail() throws Exception {
		Sendable mail = new Email();
		mail.setSujet("test");
		mail.setType(TypeEmail.TEXTE);
		mail.setContenu("ceci est un test au format text");
		mail.send("sebastien.benede@avancial.com", "was@avancial.com");
		
		assertNotNull(this.smtpServer.getMessages());
		assertTrue(this.smtpServer.getMessages().toString().contains("text/plain"));
		assertTrue(this.smtpServer.getMessages().toString().contains("ceci est un test au format text"));
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
