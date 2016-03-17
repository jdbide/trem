package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.EmptyStackException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.avancial.app.data.controller.dao.implementation.TremasApplicationDAO;
import com.avancial.app.data.controller.dao.implementation.TremasJeuDonneeDAO;
import com.avancial.app.data.controller.dao.inter.ITremasApplicationDAO;
import com.avancial.app.data.controller.dao.inter.ITremasJeuDonneeDAO;
import com.avancial.app.data.databean.TremasJeuDonnee;
import com.avancial.app.resources.constants.APP_MENU_constants;
import com.avancial.app.service.implementation.TremasApplicationService;
import com.avancial.app.service.implementation.TremasJeuDonneeService;

@Named("importDonnees")
@ViewScoped
public class ImportationDonneesBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	
/*	@ManagedProperty(value="#{TremasJeuDonneeDAO}")
	private ITremasJeuDonneeDAO dao;*/	
	
	@Inject
	private TremasJeuDonneeService jeuDonneeService;
	
	@Inject
   private TremasApplicationService applicationService;
	
	@Inject
	private TremasJeuDonnee tremasJeuDonneeBean;
	
	public ImportationDonneesBean() {
		//this.dao = new TremasJeuDonneeDAO();		
	}
	
	public String traitement() {
		try {
		   throw new Exception();
		   //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Importation réusie", "^^"));
		   /* FOR Post-Redirect-Get */
		   //return APP_MENU_constants.NAVIGATION_IMPORT_DATA.toString();
			//throw new Exception();
			/*throw new EmptyStackException();
			this.tremasJeuDonneeBean.setTremasApplication(this.applicationService.getById(1));//this.iTremasApplicationDAO.read(new Integer(1))
			this.tremasJeuDonneeBean.setActifJeuDonnees(true);
			this.tremasJeuDonneeBean.setOrdreJeuDonnees(0);
			this.tremasJeuDonneeBean.setIdUtilisateurCreateJeuDonnees(1);
			this.tremasJeuDonneeBean.setIdUtilisateurLastUpdateJeuDonnees(1);
			this.tremasJeuDonneeBean.setDateCreateJeuDonnees(new Date());
			this.tremasJeuDonneeBean.setDateLastUpdateJeuDonnees(new Date());
			this.jeuDonneeService.save(this.tremasJeuDonneeBean);
			
			FacesContext.getCurrentInstance().addMessage("myForm:errorMsg", new FacesMessage("Importation réusite!! "));
			
			return true;*/
		} catch (Exception e) {
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec d'importation", null));
			return null;
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
