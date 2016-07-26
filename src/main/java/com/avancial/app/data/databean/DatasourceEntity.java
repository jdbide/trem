package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tremas_datasource database table.
 * 
 */
@Entity
@Table(name="tremas_datasource")
@NamedQuery(name="DatasourceEntity.findAll", query="SELECT t FROM DatasourceEntity t")
public class DatasourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDatasource;

	private byte actifDatasource;

	@Lob
	private String commentaireDataSource;

	private String driverClassName;

	private String libelleDataSource;

	private String nomTechniqueDataSource;

	private String url;

	public DatasourceEntity() {
	}

	public int getIdDatasource() {
		return this.idDatasource;
	}

	public void setIdDatasource(int idDatasource) {
		this.idDatasource = idDatasource;
	}

	public byte getActifDatasource() {
		return this.actifDatasource;
	}

	public void setActifDatasource(byte actifDatasource) {
		this.actifDatasource = actifDatasource;
	}

	public String getCommentaireDataSource() {
		return this.commentaireDataSource;
	}

	public void setCommentaireDataSource(String commentaireDataSource) {
		this.commentaireDataSource = commentaireDataSource;
	}

	public String getDriverClassName() {
		return this.driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getLibelleDataSource() {
		return this.libelleDataSource;
	}

	public void setLibelleDataSource(String libelleDataSource) {
		this.libelleDataSource = libelleDataSource;
	}

	public String getNomTechniqueDataSource() {
		return this.nomTechniqueDataSource;
	}

	public void setNomTechniqueDataSource(String nomTechniqueDataSource) {
		this.nomTechniqueDataSource = nomTechniqueDataSource;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}