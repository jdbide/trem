package com.avancial.app.data.databean.importMotrice;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_composition")
@NamedQueries({
        @NamedQuery(name = "MotriceRegimeComposition.getAll", query = "SELECT t FROM MotriceRegimeCompositionEntity t"),
        @NamedQuery(name = "MotriceRegimeComposition.deleteAll", query = "DELETE FROM MotriceRegimeCompositionEntity"),
        @NamedQuery(name = "MotriceRegimeCompositionEntity.getLastId", query = "SELECT MAX( idMotriceRegimeComposition ) FROM MotriceRegimeCompositionEntity")})
public class MotriceRegimeCompositionEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long idMotriceRegimeComposition;

	@Column(length = 1, nullable = false)
	private String classCodeMotriceRegimeComposition;

	@Column(length = 3, nullable = false)
	private String diagCodeMotriceRegimeComposition;

	@Column(length = 6, nullable = false)
	private String rameCodeMotriceRegimeComposition;

	@Column(length = 3, nullable = false)
	private String rmCodeMotriceRegimeComposition;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegimeComposition")
	private List<MotriceRegimeCompositionCoachEntity> carsNumbers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMotriceRegime")
	@ForeignKey(name = "FK_motrice_regime_composition_idMotriceRegime")
	private MotriceRegimeEntity motriceRegime;

	/**
	 * @return the idMotriceRegimeComposition
	 */
	public Long getIdMotriceRegimeComposition() {
		return this.idMotriceRegimeComposition;
	}

	/**
	 * @param idMotriceRegimeComposition
	 *            the idMotriceRegimeComposition to set
	 */
	public void setIdMotriceRegimeComposition(Long idMotriceRegimeComposition) {
		this.idMotriceRegimeComposition = idMotriceRegimeComposition;
	}

	/**
	 * @return the rmCodeMotriceRegimeComposition
	 */
	public String getRmCodeMotriceRegimeComposition() {
		return this.rmCodeMotriceRegimeComposition;
	}

	/**
	 * @param rmCodeMotriceRegimeComposition
	 *            the rmCodeMotriceRegimeComposition to set
	 */
	public void setRmCodeMotriceRegimeComposition(String rmCodeMotriceRegimeComposition) {
		this.rmCodeMotriceRegimeComposition = rmCodeMotriceRegimeComposition;
	}

	/**
	 * @return the rameCodeMotriceRegimeComposition
	 */
	public String getRameCodeMotriceRegimeComposition() {
		return this.rameCodeMotriceRegimeComposition;
	}

	/**
	 * @param rameCodeMotriceRegimeComposition
	 *            the rameCodeMotriceRegimeComposition to set
	 */
	public void setRameCodeMotriceRegimeComposition(String rameCodeMotriceRegimeComposition) {
		this.rameCodeMotriceRegimeComposition = rameCodeMotriceRegimeComposition;
	}

	/**
	 * @return the classCodeMotriceRegimeComposition
	 */
	public String getClassCodeMotriceRegimeComposition() {
		return this.classCodeMotriceRegimeComposition;
	}

	/**
	 * @param classCodeMotriceRegimeComposition
	 *            the classCodeMotriceRegimeComposition to set
	 */
	public void setClassCodeMotriceRegimeComposition(String classCodeMotriceRegimeComposition) {
		this.classCodeMotriceRegimeComposition = classCodeMotriceRegimeComposition;
	}

	/**
	 * @return the carsNumbers
	 */
	public List<MotriceRegimeCompositionCoachEntity> getCarsNumbers() {
		return this.carsNumbers;
	}

	/**
	 * @param carsNumbers
	 *            the carsNumbers to set
	 */
	public void setCarsNumbers(List<MotriceRegimeCompositionCoachEntity> carsNumbers) {
		this.carsNumbers = carsNumbers;
	}

	/**
	 * @return the motriceRegime
	 */
	public MotriceRegimeEntity getMotriceRegime() {
		return this.motriceRegime;
	}

	/**
	 * @param motriceRegime
	 *            the motriceRegime to set
	 */
	public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
		this.motriceRegime = motriceRegime;
	}

	public String getDiagCodeMotriceRegimeComposition() {
		return this.diagCodeMotriceRegimeComposition;
	}

	public void setDiagCodeMotriceRegimeComposition(String diagCodeMotriceRegimeComposition) {
		this.diagCodeMotriceRegimeComposition = diagCodeMotriceRegimeComposition;
	}

}
