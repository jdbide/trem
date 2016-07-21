/**
 * 
 */
package utils.transcodage;

import java.util.Calendar;
import java.util.Date;

/**
 * @author guillaume.bouziou
 *
 */
public class Periode implements Comparable<Periode> {
	private Date dateDebut;
	private Date dateFin;
	/**
	 * getter dateDebut
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	/**
	 * setter the dateDebut
	 * @param dateDebut 
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	/**
	 * getter dateFin
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	/**
	 * setter the dateFin
	 * @param dateFin 
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * Constructor 
	 * @param dateDebut
	 * @param dateFin
	 */
	public Periode(Date dateDebut, Date dateFin) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateDebut);
		StringBuilder num = new StringBuilder();
		num.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.WEEK_OF_YEAR));
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Periode o) {
		return this.getDateDebut().compareTo(o.getDateDebut());
	}
}
