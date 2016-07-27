package com.avancial.app.ref.databean;

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
	private Integer rame2RefCodeRm;
}
