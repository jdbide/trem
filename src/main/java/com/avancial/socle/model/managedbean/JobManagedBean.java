/**
 * 
 */
package com.avancial.socle.model.managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.avancial.socle.business.JobBean;
import com.avancial.socle.data.controller.dao.JobDao;
import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
@Named("jobManagedBean")
@ViewScoped
public class JobManagedBean extends AManageBean {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private List<JobBean>     selectItems;
   private String            nomTechnique;
   private String            libelle;
   private String            classe;

   @Inject
   private JobBean           selectedItem;

   /**
    * Constructeur
    */
   public JobManagedBean() {
      this.selectItems = new ArrayList<>();
      this.reload();

   }

   public void reload() {
      this.selectItems.clear();
      this.selectItems.addAll(JobBean.getAll());
   }

   public void initProperties() {
      this.libelle = "";
      this.nomTechnique = "";
      this.classe = "";
   }

   /**
    * @return
    * @throws ASocleException
    */
   @Override
   public String add() throws ASocleException {

      super.add();
      JobBean bean = new JobBean();
      bean.setLibelleJob(this.libelle);
      bean.setNomTechniqueJob(this.nomTechnique);
      bean.setClasseJob(this.classe);

      try {
         bean.save();
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Le Job a �t� cr��."));
         RequestContext.getCurrentInstance().update(":dataTable");
         this.closeDialog = true;
         return SocleMenuManagedBean.goJob();
      } catch (ASocleException e) {
         // RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         e.getClientMessage();
         FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
         // RequestContext.getCurrentInstance().addCallbackParam("notValid", true);
         // throw e;
      }
      return null;
   }

   public void updateById() throws ASocleException {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      Map<String, String> parameterMap = externalContext.getRequestParameterMap();
      String param = parameterMap.get("itemId");
      if (param != null) {
         Integer idJob = Integer.valueOf(param);
         for (JobBean jobBean : selectItems) {
            if (jobBean.getIdJob().equals(idJob.longValue())) {
               this.selectedItem = jobBean;
               break;
            }
         }
      } else {
         // redirection vers la page job.xhtml
      }

   }

   @Override
   public String update() throws ASocleException {
      super.update();
      if (null != this.selectedItem) {

         try {

            this.selectedItem.update();

            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Enregistrement modifi�"));
            this.closeDialog = true;
            return SocleMenuManagedBean.goJob();
         } catch (ASocleException e) {
            e.printStackTrace();
            // RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
            e.getClientMessage();
            // RequestContext.getCurrentInstance().addCallbackParam("notValid", true);
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
            // throw e;
         }
      }
      return null;
   }

   @Override
   public String delete() throws ASocleException {
      super.delete();
      if (null != this.selectedItem) {
         JobDao dao = new JobDao();
         try {
            this.selectedItem.delete();
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "Enregistrement supprim�"));
            this.closeDialog = true;
            this.reload();
         } catch (ASocleException e) {
            FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", "Enregistrement non effac�"));
            throw e;
         }
      }
      return null;
   }

   /**
    * @return the nomtechnique
    */
   public String getNomTechnique() {
      return this.nomTechnique;
   }

   /**
    * @param nomtechnique
    *           the nomtechnique to set
    */
   public void setNomTechnique(String nomtechnique) {
      this.nomTechnique = nomtechnique;
   }

   /**
    * @return the libelle
    */
   public String getLibelle() {
      return this.libelle;
   }

   /**
    * @param libelle
    *           the libelle to set
    */
   public void setLibelle(String libelle) {
      this.libelle = libelle;
   }

   /**
    * sets value for roleSelected
    * 
    * @param selectedItem
    *           the roleSelected to set
    */
   public void setSelectedItem(JobBean selectedItem) {
      if (null != selectedItem) {
         this.selectedItem = selectedItem;
         this.libelle = selectedItem.getLibelleJob();
         this.nomTechnique = selectedItem.getNomTechniqueJob();
         this.classe = selectedItem.getClasseJob();
      }
   }

   @Override
   public Boolean getCloseDialog() {
      return this.closeDialog;
   }

   @Override
   public void setCloseDialog(Boolean closeDialog) {
      this.closeDialog = closeDialog;
   }

   public List<JobBean> getSelectedItems() {
      return this.selectItems;
   }

   public void setSelectedItems(List<JobBean> jobs) {
      this.selectItems = jobs;
   }

   public JobBean getSelectedItem() {
      return this.selectedItem;
   }

   public String getClasse() {
      return this.classe;
   }

   public void setClasse(String classe) {
      this.classe = classe;
   }

}
