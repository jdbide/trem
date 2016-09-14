package com.avancial.socle.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Email implements Sendable {//extends AbstractEmail 		
	protected String 	contenu;
	protected TypeEmail type;
	protected Multipart multipart;
	protected String	sujet;
	protected File[]	fileTab;
	
	public Email() {
		//this.setType(TypeEmail.TEXTE);
		this.multipart = new MimeMultipart();
		//multipart.addBodyPart(messageBodyPart);
	}
	
	@Override
	public void send(String listeDiffusionTo, String mailFrom) throws Exception {
		final Properties props = System.getProperties();
		props.put("mail.smtp.host", "CZC3137DTH.sysrail.local");
		props.put("mail.smtp.port", "8090");

		final Session session = Session.getInstance(props, null);
		final Message message = new MimeMessage(session);

		this.prepareMessage(message, listeDiffusionTo, mailFrom);

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(this.contenu, this.type.toString());
		this.multipart.addBodyPart(messageBodyPart);
		message.setContent(this.multipart);
		
		Transport.send(message);
		
		System.out.println(message.getContent().toString());
	}

	private void prepareMessage(final Message message, String listeDiffusionTo, String mailFrom) throws Exception {
		message.setFrom(new InternetAddress(mailFrom));

		InternetAddress[] toAddrsBCC = null;
		InternetAddress[] toAddrsCC = null;
		InternetAddress[] toAddrsTO = null;
		InternetAddress[] replyToAddrs = null;

		if (listeDiffusionTo != null)
			toAddrsTO = InternetAddress.parse(listeDiffusionTo, false);

		message.setRecipients(Message.RecipientType.BCC, toAddrsBCC);
		message.setRecipients(Message.RecipientType.CC, toAddrsCC);
		message.setRecipients(Message.RecipientType.TO, toAddrsTO);
		message.setReplyTo(replyToAddrs);
		message.setSubject(this.sujet);
	}

	@Override
	public String getContenu() {
		return this.contenu;
	}

	@Override
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public TypeEmail getType() {
		return this.type;
	}

	@Override
	public Multipart getMultipart() {
		return this.multipart;
	}

	@Override
	public void setType(TypeEmail type) {
		this.type = type;
	}

	@Override
	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}

	@Override
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	@Override
	public void setFileTab(File[] fileTab) {
		this.fileTab = fileTab;
	}

	@Override
	public File[] getFileTab() {
		return this.fileTab;
	}
}
