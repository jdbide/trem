package com.avancial.socle.data.model.databean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "socle_log_job_detail")
public class LogJobDetailDataBean extends AbstractDataBean {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLogJobDetail;
	@OneToOne
	@JoinColumn(name = "idLogJob", nullable=false)
	private LogJobDataBean logJob;
	private int severiteLogJobDetail;
	private String messageLogJobDetail;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLogJobDetail;
	private String messageExceptionLogJobDetail;
	
	public Long getIdLogJobDetail() {
		return this.idLogJobDetail;
	}
	public void setIdLogJobDetail(Long idLogJobDetail) {
		this.idLogJobDetail = idLogJobDetail;
	}
	public LogJobDataBean getLogJob() {
		return this.logJob;
	}
	public void setLogJob(LogJobDataBean logJob) {
		this.logJob = logJob;
	}
	public int getSeveriteLogJobDetail() {
		return this.severiteLogJobDetail;
	}
	public void setSeveriteLogJobDetail(int severiteLogJobDetail) {
		this.severiteLogJobDetail = severiteLogJobDetail;
	}
	public String getMessageLogJobDetail() {
		return this.messageLogJobDetail;
	}
	public void setMessageLogJobDetail(String messageLogJobDetail) {
		this.messageLogJobDetail = messageLogJobDetail;
	}
	public Date getDateLogJobDetail() {
		return this.dateLogJobDetail;
	}
	public void setDateLogJobDetail(Date dateLogJobDetail) {
		this.dateLogJobDetail = dateLogJobDetail;
	}
	public String getMessageExceptionLogJobDetail() {
		return this.messageExceptionLogJobDetail;
	}
	public void setMessageExceptionLogJobDetail(String messageExceptionLogJobDetail) {
		this.messageExceptionLogJobDetail = messageExceptionLogJobDetail;
	}
	
	
}
