package com.avancial.app.resources.constants;

/**
 * @author hamza.laterem
 * 
 * Enum pour la gestion du menu de l'application
 *
 */
public enum APP_MENU_constants {
	NAVIGATION_TEST("/pages/private/test?faces-redirect=true"),
	NAVIGATION_IMPORT_DATA("/pages/private/importation_data?faces-redirect=true");
	
	private String constant;
	
	private APP_MENU_constants(String constant) {
		this.constant = constant;
	}
	
	@Override
	public String toString() {
		return this.constant;
	}
	
	/**
	 * @return Le chemin et le nom de la page xhtml
	 */
	public String getPagePath() {
		return this.constant.replaceAll("\\?faces-redirect=true", ".xhtml");
	}
	
}
