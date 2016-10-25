package com.avancial.socle.mail;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.subethamail.wiser.Wiser;

public class PjEmailTest {

   Wiser smtpServer = new Wiser();

   @Test
   public void envoieMail() throws Exception {
      // PjEmail email = new PjEmail(new Email());
      Email email = new Email();
      email.setSujet("test");
      email.setContenu("ceci est un test avec PJ");
      // email.setFileTab(new File[] { new File("D:\\was_tmp\\AffectationADPXBean.java") });
      email.setType(TypeEmail.TEXTE);
      email.send("bruno.legloahec@avancial.com", "was@avancial.com");

      assertNotNull(this.smtpServer.getMessages());
      assertTrue(this.smtpServer.getMessages().toString().contains("text/plain"));
      assertTrue(this.smtpServer.getMessages().toString().contains("ceci est un test avec PJ"));
      // assertTrue(this.smtpServer.getMessages().toString().contains("<body>"));
      assertTrue(this.smtpServer.getMessages().toString().contains("AffectationADPXBean.java"));
   }

   @Before
   public void beforeTest() {
      this.smtpServer.setPort(8090);
      this.smtpServer.start();
   }

   @After
   public void afterTest() {
      this.smtpServer.stop();
   }
}
