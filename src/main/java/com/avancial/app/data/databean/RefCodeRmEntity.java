package com.avancial.app.data.databean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Table de référence locale pour les codes RM
 * @author sebastien.benede
 *
 */
@Entity
@Table(name = "tremas_ref_code_rm")
@NamedQuery(name = "RefCodeRmEntity.getAll", query = "SELECT t FROM RefCodeRmEntity t")
public class RefCodeRmEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long idRefCodeRm;

	@Column(length = 3, nullable = false)
	private String codeRmRefCodeRm;

	@Column(length = 50, nullable = false)
	private String libelleRefCodeRm;

	@Column(length = 5, nullable = false)
	private String rame1RefCodeRm;

	@Column(length = 5, nullable = false)
	private String rame2RefCodeRm;

	/**
	 * @return the idRefCodeRm
	 */
	public Long getIdRefCodeRm() {
		return idRefCodeRm;
	}

	/**
	 * @param idRefCodeRm the idRefCodeRm to set
	 */
	public void setIdRefCodeRm(Long idRefCodeRm) {
		this.idRefCodeRm = idRefCodeRm;
	}

	/**
	 * @return the codeRmRefCodeRm
	 */
	public String getCodeRmRefCodeRm() {
		return codeRmRefCodeRm;
	}

	/**
	 * @param codeRmRefCodeRm the codeRmRefCodeRm to set
	 */
	public void setCodeRmRefCodeRm(String codeRmRefCodeRm) {
		this.codeRmRefCodeRm = codeRmRefCodeRm;
	}

	/**
	 * @return the libelleRefCodeRm
	 */
	public String getLibelleRefCodeRm() {
		return libelleRefCodeRm;
	}

	/**
	 * @param libelleRefCodeRm the libelleRefCodeRm to set
	 */
	public void setLibelleRefCodeRm(String libelleRefCodeRm) {
		this.libelleRefCodeRm = libelleRefCodeRm;
	}

	/**
	 * @return the rame1RefCodeRm
	 */
	public String getRame1RefCodeRm() {
		return rame1RefCodeRm;
	}

	/**
	 * @param rame1RefCodeRm the rame1RefCodeRm to set
	 */
	public void setRame1RefCodeRm(String rame1RefCodeRm) {
		this.rame1RefCodeRm = rame1RefCodeRm;
	}

	/**
	 * @return the rame2RefCodeRm
	 */
	public String getRame2RefCodeRm() {
		return rame2RefCodeRm;
	}

	/**
	 * @param rame2RefCodeRm the rame2RefCodeRm to set
	 */
	public void setRame2RefCodeRm(String rame2RefCodeRm) {
		this.rame2RefCodeRm = rame2RefCodeRm;
	}
	
	
}
