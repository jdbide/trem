package com.avancial.app.importation.status;

import java.io.Serializable;

public class StatusTraitementImportation implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private int status = 0;
	private String headerMessage = null;
	private String contentMessage = null;
	
	public StatusTraitementImportation() {
		this.status = 0;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the headerMessage
	 */
	public String getHeaderMessage() {
		return this.headerMessage;
	}

	/**
	 * @param headerMessage the headerMessage to set
	 */
	public void setHeaderMessage(String headerMessage) {
		this.headerMessage = headerMessage;
	}

	/**
	 * @return the contentMessage
	 */
	public String getContentMessage() {
		return this.contentMessage;
	}

	/**
	 * @param contentMessage the contentMessage to set
	 */
	public void setContentMessage(String contentMessage) {
		this.contentMessage = contentMessage;
	}
	
}
