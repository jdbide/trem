package com.avancial.app.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.dto.ImportTmsDto;

@SessionScoped
public class ImportTmsService implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Inject
	private CompagnieEnvironnementService compagnieEnvironnementService;

	@Inject
	private JeuDonneeService jeuDonneeService;

	public ImportTmsService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * TODO Recuperation des jeu de données
	 * 
	 * @return
	 */
	public List<ImportTmsDto> getAllImportTmsActif() {
		List<ImportTmsDto> listImportTmsDto = new ArrayList<ImportTmsDto>();
		JeuDonneeEntity jeuDonneeEntityDraft, jeuDonneeEntityActif;
		ImportTmsDto newImportTmsDto;

		for (CompagnieEnvironnementEntity compagnieEnvironnementEntity : this.compagnieEnvironnementService
				.getAllCompagnieEnvironnementActif()) {
			newImportTmsDto = new ImportTmsDto();
			newImportTmsDto.mergeByCompagnieEnvironnement(compagnieEnvironnementEntity);
			
			// id = id && status = act && draft
			jeuDonneeEntityDraft = this.jeuDonneeService.getJeuDonneeParIdCompagnieEtStatus(compagnieEnvironnementEntity, Status.DRAFT);
			jeuDonneeEntityActif = this.jeuDonneeService.getJeuDonneeParIdCompagnieEtStatus(compagnieEnvironnementEntity, Status.ACTIVE);

			
			
			if(jeuDonneeEntityDraft != null) {
				newImportTmsDto.mergeByJeuDonneesBrouillon((JeuDonneeEntity) jeuDonneeEntityDraft, "toto");
			}
			
			if(jeuDonneeEntityActif != null) {
				newImportTmsDto.mergeByJeuDonneesActif((JeuDonneeEntity) jeuDonneeEntityActif, "titi");
			}

			listImportTmsDto.add(newImportTmsDto);
		}

		return listImportTmsDto;
	}

	/**
	 * Suppression d'un jeu de données en brouillon
	 * 
	 * @param importTmsDto
	 * @return
	 */
	public Boolean deleteDraft(ImportTmsDto importTmsDto) {
		try {
			return (this.jeuDonneeService.deleteById(importTmsDto.getIdJeuDonneeBrouillon()) > 0) ? true : false;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Validation d'un jeu de données
	 * @param importTmsDto
	 */
	public boolean validateDraft(ImportTmsDto importTmsDto) {
		// on passe le status de ACTIVE à LASTACTIVE
		JeuDonneeEntity jeuDonneeEntityActif = this.jeuDonneeService.getById(importTmsDto.getIdJeuDonneesActif());
		jeuDonneeEntityActif.setStatusJeuDonnees(Status.LASTACTIVE);
		jeuDonneeEntityActif.setDateLastUpdateJeuDonnees(new Date());
		jeuDonneeEntityActif.setIdUtilisateurLastUpdateJeuDonnees(-1);	
		this.jeuDonneeService.update(jeuDonneeEntityActif);
		
		// on passe le status de DRAFT à ACTIVE 
		JeuDonneeEntity jeuDonneeEntityDraft = this.jeuDonneeService.getById(importTmsDto.getIdJeuDonneeBrouillon());
		jeuDonneeEntityDraft.setStatusJeuDonnees(Status.ACTIVE);
		jeuDonneeEntityDraft.setDateLastUpdateJeuDonnees(new Date());
		jeuDonneeEntityDraft.setIdUtilisateurLastUpdateJeuDonnees(-1);	
		this.jeuDonneeService.update(jeuDonneeEntityDraft);
		
		return true;
	}

}
