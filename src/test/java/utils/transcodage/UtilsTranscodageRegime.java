/**
 * 
 */
package utils.transcodage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Classe utils pour le transcodage d'un r�gime binaire
 * 
 * @author guillaume.bouziou
 * 
 */
public class UtilsTranscodageRegime {

	private static final SimpleDateFormat FORMAT_DATE_DDMMYY = new SimpleDateFormat("ddMMyy");
	private static final SimpleDateFormat FORMAT_DATE_DDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
	private static final char CIRCULE = '1';
	private static final char CIRCULE_PAS = '0';

	private List<JourPourRegime> listeJourPourRegime;
	private String regime;
	private Date dateDebutService;
	// private List<Date> listeJoursFerie;

	private List<EnumTypeJour> listeTypeJourCircule = new ArrayList<EnumTypeJour>();
	private List<JourPourRegime> listeExceptionNegative = new ArrayList<JourPourRegime>();
	private List<JourPourRegime> listeExceptionPositive = new ArrayList<JourPourRegime>();
	private List<PeriodeException> listePeriodeExceptions = new ArrayList<PeriodeException>();
	private List<Date> listeDateException = new ArrayList<>();

	private HashMap<Integer, DetailCpt> mapCpt;

	private static final String INDICATEUR_EXECPTION_MOINS = "MOINS";
	private static final String INDICATEUR_EXECPTION_PLUS = "PLUS";

	/**
	 * Constructor
	 * 
	 * @param regime
	 * @param dateDebutService
	 * @throws ParseException
	 */
	public UtilsTranscodageRegime(String regime, Date dateDebutService) throws ParseException {

		this.regime = regime;
		this.dateDebutService = dateDebutService;
		initMapCpt();
		initListeJourPourRegime();
		formatRegimeGeneral();
	}

	public boolean circuleToutLesJoursSemaine(List<EnumTypeJour> listeTypeJourCircule) {
		for (EnumTypeJour enumTypeJour : EnumTypeJour.values()) {
			if (enumTypeJour.getNumeroType() <= 7 && !listeTypeJourCircule.contains(enumTypeJour)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Initialisation de la map de gestion des compteurs
	 */
	private void initMapCpt() {
		this.mapCpt = new HashMap<Integer, DetailCpt>();
		for (EnumTypeJour enumTypeJour : EnumTypeJour.values()) {
			this.mapCpt.put(enumTypeJour.getNumeroType(), new DetailCpt(0, 0, false));
		}
	}

	/**
	 * Ex�cute le transcodage et retourne le r�gime format�
	 * 
	 * @return le r�gime format�
	 * @throws ParseException
	 */
	public String executeTranscodage() throws ParseException {
		StringBuilder regimeRetour = new StringBuilder();

		if (this.regime.contains("1")) {

			if (listeTypeJourCircule.isEmpty()) {
				formatRegimeGeneralListeTypeJourEmpty();
				// regimeRetour.append("Cas particulier, aucun jour de circulation majoritaire");
			}
			regimeRetour.append("(");
			if (circuleToutLesJoursSemaine(listeTypeJourCircule)) {

			} else {
				for (EnumTypeJour enumTypeJour : listeTypeJourCircule) {
					if (regimeRetour.toString().length() > 1) {
						regimeRetour.append("+");
					}
					regimeRetour.append(enumTypeJour.getLibelleCourt());
				}
			}
			// Affichage de la p�riode de circulation
			regimeRetour.append("*").append(dateToString(getDateDebutCirculation()));
			regimeRetour.append("/").append(dateToString(getDateFinCirculation()));

			// Affichage des exceptions factoris�es
			regimeRetour.append(getExceptions());
		} else {
			regimeRetour.append("Aucune circulation sur la p�riode.");
		}
		return regimeRetour.toString();
	}

	/**
	 * Retourne la liste de date en string avec le s�parateur en parametre
	 * 
	 * @param listeDate
	 * @param separateur
	 * @return la liste de date en string
	 */
	public String listeDateToString(List<Date> listeDate, String separateur, String indicateur) {
		StringBuilder retour = new StringBuilder();
		Collections.sort(listeDate);
		if (listeDate.isEmpty()) {
			return retour.toString();
		} else {
			for (int i = 0; i < listeDate.size(); i++) {

				// Optimisation de l'affichage lorsque les dates qui se suivent sont du meme mois
				if (i + 1 < listeDate.size()) {
					Calendar dateEncour = Calendar.getInstance();
					dateEncour.setTime(listeDate.get(i));
					Calendar lendemain = Calendar.getInstance();
					lendemain.setTime(listeDate.get(i + 1));
					if (dateEncour.get(Calendar.MONTH) == lendemain.get(Calendar.MONTH)) {
						String date = dateToString(dateEncour.getTime());
						retour.append(date.subSequence(0, 2)).append(indicateur);
					} else {
						retour.append(dateToString(dateEncour.getTime())).append(separateur);
					}
				} else {
					retour.append(dateToString(listeDate.get(i))).append(separateur);
				}
			}
			if (retour.toString().length() > 0) {
				return retour.toString().substring(0, retour.toString().length() - separateur.length());
			} else {
				return "";
			}
		}

	}

	public void getListePeriodeExceptions(List<JourPourRegime> listeException, boolean positif) throws ParseException {
		this.listePeriodeExceptions = new ArrayList<>();
		for (EnumTypeJour enumTypeJour : EnumTypeJour.values()) {
			List<Periode> listePeriode = new ArrayList<Periode>();
			Date dateDebut = null;
			for (JourPourRegime jourPourRegime : this.listeJourPourRegime) {
				// On prend uniquement les jours de la semaine classique (Lundi->Dimanche)
				if (enumTypeJour.getNumeroType() <= 8) {
					if (jourPourRegime.getTypeJour().equals(enumTypeJour)) {

						if ((!jourPourRegime.isCircule() && !positif) || (jourPourRegime.isCircule() && positif)) {
							if (listeException.contains(jourPourRegime)) {
								if (dateDebut == null) {
									dateDebut = jourPourRegime.getDateDuJour();
								}
							}
						} else {
							if (dateDebut != null) {
								listePeriode.add(new Periode(dateDebut, getDateAjoutJour(jourPourRegime.getDateDuJour(), -1)));
								dateDebut = null;
							}
						}

					}
				}
			}
			if (dateDebut != null) {
				listePeriode.add(new Periode(dateDebut, getDateFinCirculation()));
				dateDebut = null;
			}
			if (enumTypeJour.getNumeroType() <= 8 && !listePeriode.isEmpty()) {
				this.listePeriodeExceptions.add(new PeriodeException(enumTypeJour, listePeriode));
			}
		}
		Collections.sort(listePeriodeExceptions);
	}

	/**
	 * Retourne la liste des exceptions factoris�
	 * 
	 * @return la liste des exceptions factoris�
	 * @throws ParseException
	 */
	public String getExceptions() throws ParseException {
		StringBuilder retour = new StringBuilder();
		for (JourPourRegime jourPourRegime : this.listeJourPourRegime) {
			if (!jourPourRegime.isCircule() && listeTypeJourCircule.contains(jourPourRegime.getTypeJour())) {
				listeExceptionNegative.add(jourPourRegime);
			} else if (jourPourRegime.isCircule() && !listeTypeJourCircule.contains(jourPourRegime.getTypeJour())) {
				listeExceptionPositive.add(jourPourRegime);
			}
		}

		if (!listeExceptionPositive.isEmpty() && listeExceptionNegative.isEmpty()) {
			retour.append(")").append(formatFactorisationException(listeExceptionPositive, true));
		} else if (!listeExceptionNegative.isEmpty() && listeExceptionPositive.isEmpty()) {
			retour.append(")").append(formatFactorisationException(listeExceptionNegative, false));
		} else {

			// StringBuilder regime1 = new StringBuilder();
			// // Ajout des exception dans le r�gime g�n�ral
			// String exceptionRegimeGeneral1 = listeDateToString(listeJourRegimeToDate(listeExceptionNegative), ",", "-");
			// String exception1 = formatFactorisationException(listeExceptionPositive, true);
			// if (exceptionRegimeGeneral1.length() > 0) {
			// regime1.append("-").append(exceptionRegimeGeneral1);
			// }
			// regime1.append(")");
			// // Ajout des exception en dehors du regime g�n�ral
			// regime1.append(exception1);

			StringBuilder regime2 = new StringBuilder();
			String exceptionRegimeGeneral2 = listeDateToString(listeJourRegimeToDate(listeExceptionPositive), ",", "+");
			String exception2 = formatFactorisationException(listeExceptionNegative, false);
			if (exceptionRegimeGeneral2.length() > 0) {
				regime2.append("+").append(exceptionRegimeGeneral2);
			}
			regime2.append(")");
			// Ajout des exception en dehors du regime g�n�ral
			regime2.append(exception2);

			// if (regime1.toString().length() > regime2.toString().length()) {
			retour.append(regime2.toString());
			// } else {
			// retour.append(regime1.toString());
			// }

		}

		Collections.sort(listePeriodeExceptions);
		// imprimeListePeriodeException(listePeriodeExceptions);

		return retour.toString();
	}

	public List<Date> listeJourRegimeToDate(List<JourPourRegime> listeJourPourRegime) {
		List<Date> listeDate = new ArrayList<>();
		for (JourPourRegime jourPourRegime : listeJourPourRegime) {
			listeDate.add(jourPourRegime.getDateDuJour());
		}
		return listeDate;
	}

	public String formatFactorisationException(List<JourPourRegime> listeException, boolean positif) throws ParseException {
		getListePeriodeExceptions(listeException, positif);
		StringBuilder retour = new StringBuilder();
		String factorisation = factorisationException();
		String listeDateExceptionString = listeDateToString(listeDateException, ",", "+");
		if (factorisation.length() > 0 || listeDateExceptionString.length() > 0) {
			if (positif) {
				retour.append(INDICATEUR_EXECPTION_PLUS);
			} else {
				retour.append(INDICATEUR_EXECPTION_MOINS);
			}
			retour.append("(");
			retour.append(factorisation);
			if (listeDateExceptionString.length() > 0 && factorisation.length() > 0) {
				retour.append(",");
			}
			retour.append(listeDateExceptionString);
			retour.append(")");
		}
		return retour.toString();
	}

	public String factorisationException() throws ParseException {

		StringBuilder retour = new StringBuilder();
		// imprimeListePeriodeException(listePeriodeExceptions);
		List<EnumTypeJour> listeJourSave = new ArrayList<EnumTypeJour>();
		List<Periode> listeSortiePeriode = new ArrayList<>();
		List<Date> listeSortieDate = new ArrayList<>();

		while (!listePeriodeExceptions.isEmpty()) {
			PeriodeException periodeExceptionSave = listePeriodeExceptions.get(0);
			listeJourSave = new ArrayList<EnumTypeJour>();
			listeJourSave.add(periodeExceptionSave.getEnumTypeJour());
			Date dateSave = periodeExceptionSave.getListePeriode().get(0).getDateDebut();

			// Chargement des jours concern�s par la periode en cours
			for (PeriodeException periodeException : listePeriodeExceptions) {
				if (!periodeException.getEnumTypeJour().equals(periodeExceptionSave.getEnumTypeJour()) && (periodeException.periodeContain(0, getDateAjoutJour(dateSave, 7)) || getDateAjoutJour(dateSave, 7).after(getDateFinCirculation()))) {
					listeJourSave.add(periodeException.getEnumTypeJour());
				}
			}

			boolean periodeTrouve = false;
			Date dateFin = null;
			Periode periodeTemp = new Periode(dateSave, null);

			while (!periodeTrouve) {
				Iterator<PeriodeException> iterator = listePeriodeExceptions.iterator();
				// On passe tout les types de jours de la listes des exceptions
				while (iterator.hasNext()) {
					PeriodeException periodeException = iterator.next();

					Date dateFinPeriodeEnCours = getDateAjoutJour(dateSave, 6);
					if (dateFinPeriodeEnCours.after(getDateFinCirculation())) {
						dateFinPeriodeEnCours = getDateFinCirculation();
					}

					// cas o� au moins un des jours compris dans la periode n'est plus dans la p�riode.
					if ((listeJourSave.contains(periodeException.getEnumTypeJour()) && !periodeException.periodeContain(0, dateFinPeriodeEnCours))) {
						periodeTrouve = true;
						dateFin = getDateAjoutJour(periodeException.getListePeriode().get(0).getDateDebut(), -1);
						// cas o� un des jours non compris dans la periode est dans la p�riode.
					} else if (!listeJourSave.contains(periodeException.getEnumTypeJour()) && periodeException.periodeContain(0, dateFinPeriodeEnCours)) {
						periodeTrouve = true;
						dateFin = getDateAjoutJour(periodeException.getListePeriode().get(0).getDateDebut(), -1);
						// cas o� les jours compris dans la periode reste dans la p�riode, on modifie les dates des p�riodes
					} else if (listeJourSave.contains(periodeException.getEnumTypeJour())) {
						periodeException.getListePeriode().get(0).setDateDebut(getDateAjoutJour(periodeException.getListePeriode().get(0).getDateDebut(), 7));
						// si la modification des dates entrainent un periode vide, on supprime cette periode et c'est la fin de celle ci
						if (periodeException.getListePeriode().get(0).getDateDebut().after(periodeException.getListePeriode().get(0).getDateFin())) {
//							dateFin = getDateAjoutJour(periodeException.getListePeriode().get(0).getDateDebut(), -1);
							periodeException.getListePeriode().remove(0);
							// Si la liste des periodes est vide on supprime la PeriodeException de la iste g�n�rale
							if (periodeException.getListePeriode().isEmpty()) {
								iterator.remove();
							}
							periodeTrouve = true;
						}
					}
				}
				// une periode de sortie est trouv�e
				if (periodeTrouve) {
					// cas o� on arrive a la fin de la de la p�riode de circulation
					if (dateFin == null) {
						periodeTemp.setDateFin(getDateAjoutJour(dateSave, 6));
					} else {
						periodeTemp.setDateFin(dateFin);
						dateFin = null;
					}
					listeSortiePeriode.add(periodeTemp);
					Collections.sort(listePeriodeExceptions);
				} else {
					dateSave = getDateAjoutJour(dateSave, 7);
				}
			}

			String periode = imprimeListeSortie(periodeTemp, listeJourSave);
			if (periode.length() > 0) {
				retour.append(imprimeListeSortie(periodeTemp, listeJourSave)).append(",");
			}
		}

		if (retour.toString().length() > 0) {
			return retour.toString().substring(0, retour.length() - 1);
		} else {
			return "";
		}

	}

	public String imprimeListeSortie(Periode periode, List<EnumTypeJour> listeJourSave) {
		StringBuilder retour = new StringBuilder();

		// Si il y a moins de 3 jours dans la p�riode on affiche les dates unitairement
		Date dateEncours = periode.getDateDebut();
		List<Date> listeDateTemp = new ArrayList<>();
		while (dateEncours.compareTo(periode.getDateFin()) <= 0) {
			for (EnumTypeJour enumTypeJour : listeJourSave) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateEncours);

				if (enumTypeJour.getNumeroType() == cal.get(Calendar.DAY_OF_WEEK)) {
					listeDateTemp.add(dateEncours);
				}
			}
			dateEncours = getDateAjoutJour(dateEncours, 1);
		}

		if (listeDateTemp.size() > 3) {
			for (EnumTypeJour enumTypeJour : listeJourSave) {
				if (retour.toString().length() != 0) {
					retour.append("+");
				}
				retour.append(enumTypeJour.getLibelleCourt());
			}
			retour.append("*").append(dateToString(periode.getDateDebut())).append("/").append(dateToString(periode.getDateFin()));
		} else {
			listeDateException.addAll(listeDateTemp);
		}
		return retour.toString();
	}

	public void imprimeFactorisation(StringBuilder retour, String indicateur, String jour, String dateDebutPeriodeToString, String dateFinPeriodeToString, List<Date> listeDatePeriodeASupprimer) {
		if (retour.toString().contains(indicateur)) {
			retour.append(",");
		} else {
			retour.append(indicateur + "(");
		}
		retour.append(jour + "*");
		retour.append(dateDebutPeriodeToString).append("/").append(dateFinPeriodeToString);

		listeExceptionNegative.removeAll(listeDatePeriodeASupprimer);
	}

	/**
	 * Convertie une date en String
	 * 
	 * @param date
	 * @return la date en String
	 */
	public String dateToString(Date date) {
		StringBuilder retour = new StringBuilder();
		String dateString = FORMAT_DATE_DDMMYY.format(date);

		retour.append(dateString.substring(0, 4)).append(dateString.substring(5, 6));

		return retour.toString();
	}

	/**
	 * Initialisation de la liste des jours pour le r�gime
	 * 
	 * @throws ParseException
	 */
	public void initListeJourPourRegime() throws ParseException {

		listeJourPourRegime = new ArrayList<JourPourRegime>();

		if (regime.contains("1")) {
			// Test si on prend en compte tout les lendemain et/ou toutes les veilles
			int index = regime.indexOf("1");

			// Initialise la liste des jours du r�gime
			for (index = regime.indexOf("1"); index <= regime.lastIndexOf("1"); index++) {
				Calendar dateEnCours = Calendar.getInstance();
				dateEnCours.setTime(getDateByIndex(index));

				JourPourRegime jourEnCours = new JourPourRegime(dateEnCours.getTime());

				jourEnCours.setTypeJour(EnumTypeJour.getEnumTypeJourParNumeroType(dateEnCours.get(Calendar.DAY_OF_WEEK)));

				DetailCpt detailCpt = this.mapCpt.get(jourEnCours.getTypeJour().getNumeroType());

				// Sp�cifie la circulation du jour et incremente le compteurs ad�quate
				if (regime.charAt(index) == CIRCULE) {
					jourEnCours.setCircule(true);
					detailCpt.incrementeCptAutorise();
				} else {
					jourEnCours.setCircule(false);
					detailCpt.incrementeCptInterdit();
				}

				// Ajoute le jour dans la liste g�n�rale
				listeJourPourRegime.add(jourEnCours);
			}
		}
	}

	/**
	 * Met � jour les listes d'exceptions
	 */
	public void majException() {

	}

	/**
	 * Met � jour les compteurs pour savoir si il y plus de jours de la semaine en autorisation ou interdiction
	 * 
	 * @return si il faut un affichage pour les autorisation ou interdiction
	 */
	public void formatRegimeGeneral() {
		for (EnumTypeJour enumTypeJour : EnumTypeJour.values()) {
			DetailCpt detailCpt = this.mapCpt.get(enumTypeJour.getNumeroType());
			if (!(detailCpt.getCptAutorise() == 0 && detailCpt.getCptInterdit() == 0) && detailCpt.getCptAutorise() >= detailCpt.getCptInterdit()) {
				detailCpt.setRegimeGeneral(true);
				this.listeTypeJourCircule.add(enumTypeJour);
			}
		}
	}

	public void formatRegimeGeneralListeTypeJourEmpty() {
		for (EnumTypeJour enumTypeJour : EnumTypeJour.values()) {
			if (this.listeTypeJourCircule.size() > 2) {
				break;
			}
			DetailCpt detailCpt = this.mapCpt.get(enumTypeJour.getNumeroType());
			if (!(detailCpt.getCptAutorise() == 0 && detailCpt.getCptInterdit() == 0)) {
				detailCpt.setRegimeGeneral(true);
				this.listeTypeJourCircule.add(enumTypeJour);
			}
		}
	}

	/**
	 * Ajout du nombre jour en parametre au Calendar en parametre
	 * 
	 * @param date
	 * @param nbreJour
	 * @return la date modifi�e
	 */
	public Date getDateAjoutJour(Calendar date, int nbreJour) {
		Calendar dateRetour = (Calendar) date.clone();
		dateRetour.add(Calendar.DATE, nbreJour);
		return dateRetour.getTime();
	}

	/**
	 * Ajout du nombre jour en parametre � la date en parametre
	 * 
	 * @param date
	 * @param nbreJour
	 * @return la date modifi�e
	 */
	public Date getDateAjoutJour(Date date, int nbreJour) {
		Calendar dateRetour = Calendar.getInstance();
		dateRetour.setTime(date);
		dateRetour.add(Calendar.DATE, nbreJour);
		return dateRetour.getTime();
	}

	/**
	 * Retourne la date du jour du r�gime correspondant � l'index
	 * 
	 * @param index
	 * @return la date du jour du r�gime correspondant � l'index
	 * @throws ParseException
	 */
	public Date getDateByIndex(int index) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateDebutService);
		calendar.add(Calendar.DATE, index);
		return calendar.getTime();
	}

	/**
	 * Retourne la date de d�but de circulation
	 * 
	 * @return la date de d�but de circulation
	 * @throws ParseException
	 */
	public Date getDateDebutCirculation() throws ParseException {
		return getDateByIndex(regime.indexOf("1"));
	}

	/**
	 * Retourne la date de fin de circulation
	 * 
	 * @return la date de fin de circulation
	 * @throws ParseException
	 */
	public Date getDateFinCirculation() throws ParseException {
		return getDateByIndex(regime.lastIndexOf("1"));
	}

	/**
	 * getter listeJourPourRegime
	 * 
	 * @return the listeJourPourRegime
	 */
	public final List<JourPourRegime> getListeJourPourRegime() {
		return listeJourPourRegime;
	}

	/**
	 * setter the listeJourPourRegime
	 * 
	 * @param listeJourPourRegime
	 */
	public final void setListeJourPourRegime(List<JourPourRegime> listeJourPourRegime) {
		this.listeJourPourRegime = listeJourPourRegime;
	}

	/**
	 * getter regime
	 * 
	 * @return the regime
	 */
	public final String getRegime() {
		return regime;
	}

	/**
	 * setter the regime
	 * 
	 * @param regime
	 */
	public final void setRegime(String regime) {
		this.regime = regime;
	}

	/**
	 * getter dateDebutService
	 * 
	 * @return the dateDebutService
	 */
	public final Date getDateDebutService() {
		return dateDebutService;
	}

	/**
	 * setter the dateDebutService
	 * 
	 * @param dateDebutService
	 */
	public final void setDateDebutService(Date dateDebutService) {
		this.dateDebutService = dateDebutService;
	}

}
