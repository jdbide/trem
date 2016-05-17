package com.avancial.socle.mail;

import java.io.File;

import javax.mail.Multipart;

public interface Sendable {
	public void send(String listeDiffusionTo, String mailFrom) throws Exception;
	public void setSujet(String sujet);
	public void setContenu(String contenu);
	public String getContenu();
	public void setType(TypeEmail type);
	public void setMultipart(Multipart multipart);
	public Multipart getMultipart();
	public void setFileTab(File[] fileTab);
	public File[] getFileTab();
}
