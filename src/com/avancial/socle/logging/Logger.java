package com.avancial.socle.logging;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.resources.constants.ELogSeverite;
import com.avancial.socle.resources.constants.ELogSortie;

/**
 * Impl�mentation de l'interface de Logger,
 * elle contient une liste de strat�gies de logging
 * d�terminant comment seront enregistr�s les logs
 * soumis par cette instance.
 * @author heloise.guillemaud
 *
 */
public class Logger implements ILogger {
	
	/**
	 * Collection des sorties vers lesquelles le Logger
	 * doit envoyer/ enregistrer le log
	 */
	private Map<ELogSortie, IStrategySortieLog> sortiesLogger;
	
	/**
	 * Liste des sorties qui ne sont actives que pour le prochain log
	 */
	private List<ELogSortie> sortiesActivees;
	
	/**
	 * Liste des sorties qui sont d�sactiv�es, juste sur le prochain log
	 */
	private List<ELogSortie> sortiesDesactivees;
	
	public Logger() {
		this.sortiesLogger = new HashMap<>();
		this.sortiesActivees = new ArrayList<>();
		this.sortiesDesactivees = new ArrayList<>();
	}
	
	/**
	 * Log sur toutes les sorties demand�es
	 * @param logBean Objet � logger
	 * @throws Exception
	 * @throws ASocleException 
	 */
	private void log(ALogBean logBean) throws Exception, ASocleException {
		/* Log sur toutes les sorties */
		for (IStrategySortieLog sortie : this.sortiesLogger.values()) {
			sortie.log(logBean);
		}
		
		/* Remettre les sorties d�sactiv�es pour ce log */
		for (ELogSortie sortieLog : this.sortiesDesactivees) {
			this.sortiesLogger.put(sortieLog, StrategySortieLogFactory.getStrategySortieLog(sortieLog));
		}
		
		/* Enlever les sorties activ�es pour ce log */
		for (ELogSortie sortieLog : this.sortiesActivees) {
			this.sortiesLogger.remove(sortieLog);
		}
	}
	
	@Override
	public void ajouterSortie(ELogSortie sortie) throws Exception {
		this.sortiesLogger.put(sortie, StrategySortieLogFactory.getStrategySortieLog(sortie));
	}
	
	@Override
	public void enleverSortie(ELogSortie sortie) {
		this.sortiesLogger.remove(sortie);
	}
	
	@Override
	public void activerSortie(ELogSortie sortie) throws Exception {
		/* On regarde si la sortie ne serait pas d�j� pr�sente */
		IStrategySortieLog strategieSortie = this.sortiesLogger.get(sortie);
		/*
		 * Si la sortie est d�j� pr�sente dans la map,
		 * elle ne doit pas �tre enlev�e apr�s le prochain log,
		 * donc on ne l'ajoute pas dans la liste des sorties activ�es
		 */
		if (strategieSortie == null) {
			this.sortiesActivees.add(sortie);
			this.sortiesLogger.put(sortie, StrategySortieLogFactory.getStrategySortieLog(sortie));
		}
	}
	
	@Override
	public void desactiverSortie(ELogSortie sortie) {
		/* On regarde si la sortie set bien pr�sente dans la map */
		IStrategySortieLog strategieSortie = this.sortiesLogger.get(sortie);
		/*
		 * Si la sortie n'est pas pr�sente dans la map,
		 * elle ne doit pas �tre remise apr�s le prochain log,
		 * donc on ne l'ajoute pas dans la liste des sorties d�sactiv�es
		 */
		if (strategieSortie != null) {
			this.sortiesDesactivees.add(sortie);
			this.sortiesLogger.remove(sortie);
		}
	}

	@Override
	public void info(ALogBean logBean) throws Exception, ASocleException {
		logBean.setDate(new Date());
		logBean.setSeverite(ELogSeverite.INFO);
		log(logBean);
	}

	@Override
	public void warn(ALogBean logBean) throws Exception, ASocleException {
		logBean.setDate(new Date());
		logBean.setSeverite(ELogSeverite.WARNING);
		log(logBean);
	}

	@Override
	public void error(ALogBean logBean) throws Exception, ASocleException {
		logBean.setDate(new Date());
		logBean.setSeverite(ELogSeverite.ERROR);
		log(logBean);
	}

	@Override
	public void debug(ALogBean logBean) throws Exception, ASocleException {
		logBean.setDate(new Date());
		logBean.setSeverite(ELogSeverite.DEBUG);
		log(logBean);
	}

}
