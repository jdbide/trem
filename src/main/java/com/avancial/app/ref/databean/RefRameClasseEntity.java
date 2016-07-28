package com.avancial.app.ref.databean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Table de référence locale pour les rames_classe
 * @author sebastien.benede
 *
 */
@Entity
@Table(name = "tremas_ref_rame_classe")
@NamedQuery(name = "RefRameClasse.getAll", query = "SELECT t FROM RefRameClasseEntity t")
public class RefRameClasseEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long idRefRameClasse;

	@Column(length = 6, nullable = false)
	private String codeRameRefRameClasse;

	@Column(length = 255, nullable = false)
	private String rameCodeRefRameClasse;

	@Column(length = 3, nullable = false)
	private String numResaRefRameClasse;

	@Column(length = 3, nullable = false)
	private String numCompRefRameClasse;

	@Column(length = 5, nullable = false)
	private String classeRefRameClasse;
}
