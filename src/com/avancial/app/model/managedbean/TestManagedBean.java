package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.avancial.app.data.controller.dao.implementation.TremasApplicationDAOImpl;
import com.avancial.app.data.controller.dao.inter.TremasApplicationDAO;
import com.avancial.app.data.databean.TremasApplication;
import com.avancial.socle.model.managedbean.AManageBean;

@Named("test")
@ViewScoped
public class TestManagedBean extends AManageBean implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	//@Resource(name = "tremasApplicationDAO")
	@ManagedProperty(value="#{tremasApplicationDAO}")
    private TremasApplicationDAO dao;
	
	private List<TremasApplication> tremasApplication;
	
	@Inject
	private TremasApplication           selectedItem;
	
	public TestManagedBean() {
		this.dao = new TremasApplicationDAOImpl();
		this.tremasApplication = new ArrayList<>();		
	}
	
	public void reload() {
	      this.tremasApplication.clear();
	      this.tremasApplication.addAll(this.dao.retrieveAll());
	}

	/**
	 * @return the tremasApplication
	 */
	public List<TremasApplication> getTremasApplication() {
		return tremasApplication;
	}

	/**
	 * @param tremasApplication the tremasApplication to set
	 */
	public void setTremasApplication(List<TremasApplication> tremasApplication) {
		this.tremasApplication = tremasApplication;
	}

	/**
	 * @return the selectedItem
	 */
	public TremasApplication getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(TremasApplication selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	

}
