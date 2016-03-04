package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.avancial.app.data.controller.dao.implementation.TremasApplicationDAO;
import com.avancial.app.data.controller.dao.implementation.TremasJeuDonneeDAO;
import com.avancial.app.data.controller.dao.inter.ITremasApplicationDAO;
import com.avancial.app.data.controller.dao.inter.ITremasJeuDonneeDAO;
import com.avancial.app.data.databean.TremasJeuDonnee;

@Named("importDonnees")
@ViewScoped
public class ImportationDonneesBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{TremasJeuDonneeDAO}")
	private ITremasJeuDonneeDAO dao;
	
	@ManagedProperty(value="#{TremasApplicationDAO}")
	private ITremasApplicationDAO iTremasApplicationDAO;
	
	@Inject
	private TremasJeuDonnee tremasJeuDonneeBean;
	
	public ImportationDonneesBean() {
		this.dao = new TremasJeuDonneeDAO();
		this.iTremasApplicationDAO = new TremasApplicationDAO();
	}
	
	public Boolean traitement() {
		try {
			
			this.tremasJeuDonneeBean.setTremasApplication(this.iTremasApplicationDAO.read(new Integer(1)));
			this.tremasJeuDonneeBean.setActifJeuDonnees(true);
			this.tremasJeuDonneeBean.setOrdreJeuDonnees(0);
			this.tremasJeuDonneeBean.setIdUtilisateurCreateJeuDonnees(1);
			this.tremasJeuDonneeBean.setIdUtilisateurLastUpdateJeuDonnees(1);
			this.tremasJeuDonneeBean.setDateCreateJeuDonnees(new Date());
			this.tremasJeuDonneeBean.setDateLastUpdateJeuDonnees(new Date());
			dao.insert(tremasJeuDonneeBean);
			
			return true;
		} catch (Exception e) {
			
			return false;
		}		
	}

	/**
	 * @return the tremasJeuDonneeBean
	 */
	public TremasJeuDonnee getTremasJeuDonneeBean() {
		return tremasJeuDonneeBean;
	}

	/**
	 * @param tremasJeuDonneeBean the tremasJeuDonneeBean to set
	 */
	public void setTremasJeuDonneeBean(TremasJeuDonnee tremasJeuDonneeBean) {
		this.tremasJeuDonneeBean = tremasJeuDonneeBean;
	}

}
