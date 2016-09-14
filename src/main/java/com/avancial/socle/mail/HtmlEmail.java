package com.avancial.socle.mail;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Multipart;

public class HtmlEmail implements Sendable {
	protected Sendable email;
	
	public HtmlEmail(Sendable wrappedEmail) {
		this.email = wrappedEmail;
		this.setType(TypeEmail.HTML);
	}

	@Override
	public void send(String listeDiffusionTo, String mailFrom) throws Exception {
		this.html();
		this.email.send(listeDiffusionTo, mailFrom);
	}

	/**
	 * Construit la partie HTML du message
	 * @param multipart
	 * @return multipart
	 * @throws IOException
	 * @throws MessagingException
	 */
	private void html() throws IOException, MessagingException {		
		String debut = HtmlEmail.initDebutContentHtml();
		String fin = HtmlEmail.initFinContentHtml();
		
		StringBuilder sb = new StringBuilder();
		sb.append(debut);
		sb.append(this.getContenu());
		sb.append(fin);
		
		this.setContenu(sb.toString());
	}
	
	private static String initDebutContentHtml() {
	      final StringBuilder debut = new StringBuilder();
	      debut.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
	      debut.append("<html>");
	      debut.append("<head>");
	      debut.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
//	      debut.append(this.headerHtml);
	      debut.append("</head>");
	      debut.append("<body>");

	      return debut.toString();
	   }

	   private static String initFinContentHtml() {
	      return "</body></html>";
	   }

	@Override
	public void setType(TypeEmail type) {
		this.email.setType(type);
	}

	@Override
	public void setMultipart(Multipart multipart) {
		this.email.setMultipart(multipart);
	}

	@Override
	public Multipart getMultipart() {
		return this.email.getMultipart();
	}

	@Override
	public void setSujet(String sujet) {
		this.email.setSujet(sujet);
	}

	@Override
	public void setContenu(String contenu) {
		this.email.setContenu(contenu);
	}

	@Override
	public String getContenu() {
		return this.email.getContenu();
	}

	@Override
	public void setFileTab(File[] fileTab) {
		this.email.setFileTab(fileTab);
	}

	@Override
	public File[] getFileTab() {
		return this.email.getFileTab();
	}
}
