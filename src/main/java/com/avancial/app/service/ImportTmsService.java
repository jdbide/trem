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
import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.session.Session;

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

	@Inject
	private Session session;

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
            jeuDonneeEntityDraft = this.jeuDonneeService
                    .getJeuDonneeParIdCompagnieEtStatus(compagnieEnvironnementEntity, Status.DRAFT);
            jeuDonneeEntityActif = this.jeuDonneeService
                    .getJeuDonneeParIdCompagnieEtStatus(compagnieEnvironnementEntity, Status.ACTIVE);

            UserDao userDao = new UserDao();
            UserDataBean user;
            if (jeuDonneeEntityDraft != null) {
                user = userDao.getUserById((long) jeuDonneeEntityDraft.getIdUtilisateurCreateJeuDonnees());
                newImportTmsDto.mergeByJeuDonneesBrouillon(jeuDonneeEntityDraft,
                        user == null ? "/" : user.getNomComplet());
            }

            if (jeuDonneeEntityActif != null) {
                user = userDao.getUserById((long) jeuDonneeEntityActif.getIdUtilisateurCreateJeuDonnees());
                String importBy = user == null ? "/" : user.getNomComplet();
                user = userDao.getUserById((long) jeuDonneeEntityActif.getIdUtilisateurLastUpdateJeuDonnees());
                String validateBy = user == null ? "/" : user.getNomComplet();
                newImportTmsDto.mergeByJeuDonneesActif(jeuDonneeEntityActif, importBy, validateBy);
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
	 * 
	 * @param importTmsDto
	 */
	public boolean validateDraft(ImportTmsDto importTmsDto) throws Exception {
		// on passe le status de ACTIVE à LASTACTIVE
		try {
			JeuDonneeEntity jeuDonneeEntityActif = this.jeuDonneeService.getById(importTmsDto.getIdJeuDonneesActif());
			jeuDonneeEntityActif.setStatusJeuDonnees(Status.LASTACTIVE);
			jeuDonneeEntityActif.setDateLastUpdateJeuDonnees(new Date());
			jeuDonneeEntityActif.setIdUtilisateurLastUpdateJeuDonnees(this.session.getUser().getIdUser().intValue());
			this.jeuDonneeService.update(jeuDonneeEntityActif);
		} catch (Exception e) {
			// pas de jeu de données actif
			e.printStackTrace();
			throw e;
		}

		// on passe le status de DRAFT à ACTIVE
		try {
			JeuDonneeEntity jeuDonneeEntityDraft = this.jeuDonneeService
					.getById(importTmsDto.getIdJeuDonneeBrouillon());
			jeuDonneeEntityDraft.setStatusJeuDonnees(Status.ACTIVE);
			jeuDonneeEntityDraft.setDateLastUpdateJeuDonnees(new Date());
			jeuDonneeEntityDraft.setIdUtilisateurLastUpdateJeuDonnees(this.session.getUser().getIdUser().intValue());
			this.jeuDonneeService.update(jeuDonneeEntityDraft);

			importTmsDto.setStatusJeudonneeActif(jeuDonneeEntityDraft.getStatusJeuDonnees());
			importTmsDto.setDateValidateJeuDonneesActif(jeuDonneeEntityDraft.getDateLastUpdateJeuDonnees());
			importTmsDto.setValidateJeuDonneesActifBy(new StringBuilder().append(this.session.getUser().getPrenomUser()).append(" ").append(this.session.getUser().getNomUser()).toString());
			importTmsDto.setDateImportJeuDonneesActif(jeuDonneeEntityDraft.getDateCreateJeuDonnees());
			importTmsDto.setImportJeuDonneesActifBy(importTmsDto.getImportJeuDonneesBrouillonBy());
			importTmsDto.setStatusJeudonneeBrouillon(null);
			importTmsDto.setDateImportJeuDonneesBrouillon(null);
			importTmsDto.setIdJeuDonneeBrouillon(0);
			importTmsDto.setImportJeuDonneesBrouillonBy(null);
			importTmsDto.setPathValidateJeuDonneesBrouillon(null);
		} catch (Exception e) {
			// pas de jeu de données draft
			e.printStackTrace();
			throw e;
		}

		return true;
	}

}
