package com.avancial.socle.mail;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;

public class PjEmail implements Sendable {
	protected Sendable email;
	
	public PjEmail(Sendable wrappedEmail) {
		this.email = wrappedEmail;
	}

	@Override
	public void send(String listeDiffusionTo, String mailFrom) throws Exception {
		this.addPj();
		this.email.send(listeDiffusionTo, mailFrom);
	}

	/**
	 * Ajoute les pièces jointes
	 * @param multipart
	 * @param fileTab
	 * @return multipart
	 * @throws MessagingException
	 */
	private void addPj() throws MessagingException {
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		for (int i = 0; i < this.getFileTab().length; i++) {
			// Partie de la pièce jointe
			messageBodyPart = new MimeBodyPart();
			final DataSource source = new FileDataSource(this.getFileTab()[i]);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(this.getFileTab()[i].getName());

			// Ajout de la partie pièce jointe
			this.getMultipart().addBodyPart(messageBodyPart);
		}
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
