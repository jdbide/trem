/**
 * 
 */
package com.avancial.socle.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.avancial.socle.data.controller.dao.Item2RoleDao;
import com.avancial.socle.data.controller.dao.RoleDao;
import com.avancial.socle.data.model.databean.Item2RoleDataBean;
import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.model.managedbean.AManageBean;
import com.avancial.socle.model.managedbean.IhmManagedBean;

/**
 * Gère les droits des utilisateurs sur les objets de l'application.<br/>
 * Se base sur les rôles des utilisateurs et la table item2role.<br/>
 * Sur une page, placer une des propriétés de l'objet security dans le rendered ou autre propriété de l'objet.<br/>
 * ex : rendered=""{security.isRendered('pageJobPlanif')}" permet d'afficher l'élément si l'utilisateur a le droit rendered sur pageJobPlanif.
 * 
 * 
 * @author bruno
 *
 */
@Named("security")
@SessionScoped
public class SecurityManagedBean extends AManageBean {
   /**
    * 
    */
   private static final long              serialVersionUID = 1L;
   private Map<String, Item2RoleDataBean> listeItem2Role;
   private String                         NameItem2Role;
   private Boolean                        isRendered;
   private Boolean                        isEditable;
   private Boolean                        isAddable;
   private Boolean                        isDeletable;
   private RoleDataBean                   idRole;

   private Integer                        idRoleSelected;

   private List<RoleDataBean>             listRole;

   @Inject
   private IhmManagedBean                 ihmManagedBean;
   @Inject
   private Item2RoleDataBean              selectedItem;

   @Inject
   RoleDao                                roleDao;
   @Inject
   Item2RoleDao                           dao;

   /**
    * Constructeur
    */
   public SecurityManagedBean() {
      this.listeItem2Role = new HashMap<>();
      this.listRole = new ArrayList<>();

   }

   @PostConstruct
   public void init2() {
      this.reload();
   }

   // public void changeSelectedRole(ValueChangeEvent event) {
   // this.idRoleSelected = (Integer) event.getNewValue();
   // }

   public List<Map.Entry<String, Item2RoleDataBean>> getlisteItem2Role() {
      Set<Map.Entry<String, Item2RoleDataBean>> listItem2Role = this.listeItem2Role.entrySet();

      return new ArrayList<Map.Entry<String, Item2RoleDataBean>>(listItem2Role);
   }

   /**
    * Recharge les droits. A utiliser après un changement au niveau de la base de données.
    */
   public void reload() {
      this.listeItem2Role.clear();
      /* Charge les roles */
      this.listRole.addAll(this.roleDao.getAll());
      try {
         this.remplirListeItem2Role();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   /**
    * Remplit la liste des droits des utilisateur
    * 
    * @throws Exception
    * 
    */
   public void init() {
      try {
         this.remplirListeItem2Role();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // @Override
   // public String add() throws ASocleException {
   // super.add();
   // Item2RoleDataBean item2RoleDataBean = new Item2RoleDataBean();
   // item2RoleDataBean.setNameItem2Role(this.NameItem2Role);
   // item2RoleDataBean.setIsAddable(this.isAddable);
   // item2RoleDataBean.setIsEditable(this.isEditable);
   // item2RoleDataBean.setIsDeletable(this.isDeletable);
   // item2RoleDataBean.setIsRendered(this.isRendered);
   // item2RoleDataBean.setRoleDataBean(this.idRole);
   // try {
   //
   // this.dao.save(item2RoleDataBean);
   // FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Ajout r�ussi"));
   //
   // RequestContext.getCurrentInstance().update(":dataTable");
   // this.closeDialog = true;
   // return SocleMenuManagedBean.goItem2Role();
   // } catch (ASocleException e) {
   // // RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
   // e.getClientMessage();
   // FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_UPD_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
   // // RequestContext.getCurrentInstance().addCallbackParam("notValid", true);
   //
   // }
   // return null;
   // }
   // @Override
   // public String delete() throws ASocleException {
   // super.delete();
   // if (null != this.selectedItem) {
   // Item2RoleDao dao = new Item2RoleDao();
   // try {
   // dao.delete(this.selectedItem);
   // FacesContext.getCurrentInstance().addMessage(SOCLE_constants.PAGE_ID_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_INFO, "message", "R�le supprim�"));
   // this.closeDialog = true;
   // } catch (ASocleException e) {
   // FacesContext.getCurrentInstance().addMessage(SOCLE_constants.DIALOG_DEL_MESSAGES.toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "message", e.getClientMessage()));
   // }
   // }
   // return null;
   // }

   public Item2RoleDataBean getSelectedItem() {
      return this.selectedItem;
   }

   public void rowSelect(SelectEvent event) {
      this.selectedItem = (Item2RoleDataBean) event.getObject();
   }

   public void setselectedItem(Item2RoleDataBean selectedItem) {
      if (null != selectedItem) {
         this.selectedItem = selectedItem;
         this.NameItem2Role = selectedItem.getNameItem2Role();
         this.isRendered = selectedItem.getIsRendered();
         this.isEditable = selectedItem.getIsEditable();
         this.isAddable = selectedItem.getIsAddable();
         this.isDeletable = selectedItem.getIsDeletable();
         this.idRole = selectedItem.getRoleDataBean();
      }
   }

   public Map<String, Item2RoleDataBean> getListeItem2Role() {
      return this.listeItem2Role;
   }

   public void setListeItem2Role(Map<String, Item2RoleDataBean> listeItem2Role) {
      this.listeItem2Role = listeItem2Role;
   }

   public String getNameItem2Role() {
      return this.NameItem2Role;
   }

   public void setNameItem2Role(String nameItem2Role) {
      this.NameItem2Role = nameItem2Role;
   }

   public Boolean getIsRendered() {
      return this.isRendered;
   }

   public void setIsRendered(Boolean isRendered) {
      this.isRendered = isRendered;
   }

   public Boolean getIsEditable() {
      return this.isEditable;
   }

   public void setIsEditable(Boolean isEditable) {
      this.isEditable = isEditable;
   }

   public Boolean getIsAddable() {
      return this.isAddable;
   }

   public void setIsAddable(Boolean isAddable) {
      this.isAddable = isAddable;
   }

   public Boolean getIsDeletable() {
      return this.isDeletable;
   }

   public void setIsDeletable(Boolean isDeletable) {
      this.isDeletable = isDeletable;
   }

   /**
    * Indique qu'un utilisateur n'a aucun rôle sur l'application.
    */
   private boolean noRole;

   /**
    * Indique si l'utilisateur a le droit d'afficher l'élément
    * 
    * @param itemName
    * @return
    */
   public boolean isRendered(String itemName) {
      Boolean result = false;
      Item2RoleDataBean bean = this.listeItem2Role.get(itemName);
      if (null != bean) {
         return bean.getIsRendered();
      }
      return result;
   }

   /**
    * Indique si l'utilisateur a le droit d'ajouter l'élément
    * 
    * @param itemName
    * @return
    */
   public boolean isAddable(String itemName) {
      Boolean result = false;
      Item2RoleDataBean bean = this.listeItem2Role.get(itemName);
      if (null != bean) {
         return bean.getIsAddable();
      }
      return result;
   }

   /**
    * Indique si l'utilisateur a le droit de mettre à jour l'élément
    * 
    * @param itemName
    * @return
    */
   public boolean isEditable(String itemName) {
      Boolean result = false;
      Item2RoleDataBean bean = this.listeItem2Role.get(itemName);
      if (null != bean) {
         return bean.getIsEditable();
      }
      return result;
   }

   /**
    * Remplit la liste des droits
    * 
    * @throws Exception
    * 
    */
   private void remplirListeItem2Role() throws Exception {
      this.listeItem2Role.clear();

      this.noRole = false;
      if (null != this.ihmManagedBean) {

         if (null != this.ihmManagedBean.getCurrentUser()) {
            if (this.ihmManagedBean.isLogged() && this.ihmManagedBean.getCurrentUser().getRoles().size() == 0) {
               this.noRole = true;
               return;
            }

         } else
            return;
      } else
         return;
      for (Item2RoleDataBean bean : this.dao.getitemByListId(this.ihmManagedBean.getCurrentUser().getRoles())) {
         this.listeItem2Role.put(bean.getNameItem2Role(), bean);
      }

   }

   /**
    * @param b
    */
   public void setNoRole(boolean b) {
      this.noRole = b;

   }

   /**
    * get value for noRole
    * 
    * @return the noRole
    */
   public boolean isNoRole() {
      return this.noRole;
   }

   public RoleDataBean getIdRole() {
      return this.idRole;
   }

   public void RoleDataBean(RoleDataBean idRole) {
      this.idRole = idRole;
   }

   public Integer getIdRoleSelected() {
      return idRoleSelected;
   }

   public void setIdRoleSelected(Integer idRoleSelected) {
      this.idRoleSelected = idRoleSelected;
   }

   public List<RoleDataBean> getListRole() {
      return listRole;
   }

   public void setListRole(List<RoleDataBean> listRole) {
      this.listRole = listRole;
   }
}
