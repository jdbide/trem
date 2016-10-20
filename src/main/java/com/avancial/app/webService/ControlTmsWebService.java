/**
 * 
 */
package com.avancial.app.webService;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.EStatusControl;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.ExcelImportException;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;
import com.avancial.app.resources.constants.APP_Directory;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.JeuDonneesControlService;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesContext;
import com.avancial.app.service.controlePlanTransport.excelImport.eurostar.EurostarDessertesImportProcess;
import com.avancial.app.serviceDto.JeuDonneesControlServiceDto;
import com.avancial.app.serviceDto.JeuDonneesServiceDto;
import com.avancial.app.utilitaire.pattern.structuredProcess.StructuredProcessException;
import com.avancial.app.webService.bean.ResponseBean;
import com.avancial.socle.service.RefDirectoryService;
import com.avancial.socle.session.Session;
import com.avancial.socle.utils.FileUtils;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * WebService "/app/controlTms" qui gère la page Control du module Train Manager
 * System
 * 
 * Les action implémentés sont les suivantes :
 * 
 * - GET : Récupération de la liste des CompagnieEnvironnement actif et les les
 * jeux données qui correspondant.<\br> - DELETE : Suppression d'un jeu de
 * données par nomTechniqueCompagnieEnvironnement - PUT : Acivation d'un jeu de
 * données draft à la place l'ancien jd active
 * 
 * 
 * @author hamza.laterem
 *
 */
@Path("/app/controlTms")
@RequestScoped
public class ControlTmsWebService {
	// Logger log4j
	private static Logger logger = Logger.getLogger(ControlTmsWebService.class);

	@Inject
	private CompagnieEnvironnementService compagnieEnvironnementService;

	@Inject
	private JeuDonneesControlService jeuDonneesControlService;

	@Inject
	private JeuDonneesControlServiceDto jeuDonneesControlServiceDto;

	@Inject
	private JeuDonneesServiceDto jeuDonneesServiceDto;

	@Inject
	private RefDirectoryService refDirectoryService;

	@Inject
	private Session session;


	@Path("getImportParPartition/{idCompagnieEnvironnement}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getImportParIdPartition(@PathParam("idCompagnieEnvironnement") final int idCompagnieEnvironnement) {
		logger.info("Début (WebService : '/app/controlTms', Action : 'getImportParPartition', methode : @GET)");
		ResponseBuilder responseBuilder = null;
		final ResponseBean responseBean = new ResponseBean();
		List<EStatus> status = new ArrayList<EStatus>();
		status.add(EStatus.ACTIVE);
		status.add(EStatus.DRAFT);
		try {
			responseBean.setData(
					this.jeuDonneesServiceDto.getAllJeuDonneesForControlDtoParIdCompagnieEnvironnementEtListStatus(
							idCompagnieEnvironnement, status));
			responseBean.setStatus(true);
			logger.info("Fin (WebService : '/app/controlTms', Action : 'getImportParPartition', methode : @GET)");
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setStatus(false);
			responseBean.setMessage("Erreur de chargement ...");
			logger.error("Exception (WebService : '/app/controlTms', Action : 'getImportParPartition', methode : @GET)",
					e);
		} finally {
			responseBuilder = Response.ok((Object) responseBean);
			return responseBuilder.build();
		}
	}

	/**
	 * récupération
	 * 
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("getDatasByIdPartition/{idCompagnieEnvironnement}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getDatasByIdPartition(@PathParam("idCompagnieEnvironnement") Integer idCompagnieEnvironnement)
			throws Exception {
		logger.info(
				"Début (WebService : '/app/controlTms', Action : 'getDatas/{idCompagnieEnvironnement}', methode : @GET)");

		JSONArray jsonArray = new JSONArray();
		try {
			List<EStatusControl> listStatusControl = new ArrayList<EStatusControl>();
			listStatusControl.add(EStatusControl.LOADING);
			listStatusControl.add(EStatusControl.FINISHED);
			jsonArray.addAll(this.jeuDonneesControlServiceDto
					.getAllJeuDonneesControlDtoParIdCompagnieEnvironnementEtListStatusControl(idCompagnieEnvironnement,
							listStatusControl));

			logger.info(
					"Fin (WebService : '/app/controlTms' Action : 'getDatas/{idCompagnieEnvironnement}', methode : @GET)");

			return Response.status(200).entity(jsonArray).build();
		} catch (Throwable ex) {
			logger.error(
					"Exception (WebService : '/app/controlTms' Action : 'getDatas/{idCompagnieEnvironnement}', methode : @GET))",
					ex);
			return Response.status(400).build();
		}
	}

	@SuppressWarnings("finally")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createControl(final int idCompagnieEnvironnement) {
		logger.info("Début (WebService : '/app/controlTms', Action : 'createControl', methode : @POST)");
		ResponseBuilder responseBuilder = null;
		final ResponseBean responseBean = new ResponseBean();
		try {
			responseBean.setData(jeuDonneesControlServiceDto.createJeuDonneesControl(
					this.compagnieEnvironnementService.getCompagnieEnvironnementById(idCompagnieEnvironnement),
					this.session.getUser().getIdUser().intValue()));
			responseBean.setStatus(true);
			logger.info("Fin (WebService : '/app/controlTms', Action : 'createControl', methode : @POST)");
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setStatus(false);
			responseBean.setMessage("Erreur de chargement ...");
			logger.error("Exception (WebService : '/app/controlTms', Action : 'createControl', methode : @POST)", e);
		} finally {
			responseBuilder = Response.ok((Object) responseBean);
			return responseBuilder.build();
		}
	}

	@Path("/{id}")
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteJeuDonneesControl(@PathParam("id") int idJeuDonneesControl) {
		logger.info("Début (WebService : '/app/controlTms', Action : 'deleteJeuDonneesControl', methode : @DELETE)");
		ResponseBuilder responseBuilder = null;
		final ResponseBean responseBean = new ResponseBean();
		try {
			responseBean.setData(this.jeuDonneesControlService.deleteById(idJeuDonneesControl));
			responseBean.setStatus(true);
			logger.info("Fin (WebService : '/app/controlTms', Action : 'deleteJeuDonneesControl', methode : @DELETE)");
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setStatus(false);
			responseBean.setMessage("Erreur de chargement ...");
			logger.error(
					"Exception (WebService : '/app/controlTms', Action : 'deleteJeuDonneesControl', methode : @DELETE)",
					e);
		} finally {
			responseBuilder = Response.ok((Object) responseBean);
			return responseBuilder.build();
		}
	}

	@Path("uploadFile/{idJeuDonneesControl}/{typeFile}")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.MULTIPART_FORM_DATA, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" })
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@PathParam("idJeuDonneesControl") int idJeuDonneesControl, @PathParam("typeFile") String typeFile) {

		logger.info("Début (WebService : '/app/controlTms', Action : 'uploadFile', methode : @UPLOAD)");

		final ResponseBean responseBean = new ResponseBean();
		try {
			String directory = this.refDirectoryService
					.getRefDirectoryByTechnicalName(APP_Directory.PathImport.toString()).getPathRefDirectory();
			StringBuilder filePath = new StringBuilder(directory).append(idJeuDonneesControl).append("\\")
					.append(typeFile).append("\\");
			
			if(!FileUtils.existFile(filePath.toString())) {
				// création des répertoires nécessaires
				if (!FileUtils.mkDirs(filePath.toString())) {
					responseBean.setMessage("Error creating repository");
					responseBean.setStatus(false);
					return Response.ok((Object) responseBean).build();
				}
			} else {
				FileUtils.forceDelete(new File(filePath.toString()));
				
				if (!FileUtils.mkDirs(filePath.toString())) {
					responseBean.setMessage("Error creating repository");
					responseBean.setStatus(false);
					return Response.ok((Object) responseBean).build();
				}
			}

			// save it			
			if(!FileUtils.writeToFile(uploadedInputStream, filePath.append(fileDetail.getFileName()).toString())) {
				responseBean.setMessage("Error writing file");
				responseBean.setStatus(false);
				return Response.ok((Object) responseBean).build();
			}

			System.out.println("************************TEST BEGINING************************");
			SocleExcelReadFile file = new SocleExcelReadFile(filePath.toString());
			EurostarDessertesImportProcess importer = new EurostarDessertesImportProcess();
			PlanTransport plan = importer.execute(file);
			
			responseBean.setStatus(true);	
			return Response.ok((Object) responseBean).build();

		} catch (StructuredProcessException e) {
			System.out.println("Une erreur est survenue : ");
//			e.printStackTrace();
			
			StringBuilder sb = new StringBuilder("Error on ");
			
			DessertesContext context = (DessertesContext) e.getContext();
			System.out.println("autres erreurs " + (context.getValidationErrors().size()
					+ context.getParsingErrors().size() + context.getExtractionErrors().size()) + " : ");
			
			if (!context.getParsingErrors().isEmpty()) {
				sb.append("parsing (column ").append(context.getParsingErrors().get(0).getCell().getColumnIndex()).append(" / line ").append(context.getParsingErrors().get(0).getCell().getRowIndex()).append(")");
				
				System.out.println("- de parsing :");
				System.out.println("colonne : " + context.getParsingErrors().get(0).getCell().getColumnIndex() + " / " + "ligne : " + context.getParsingErrors().get(0).getCell().getRowIndex());
				context.getParsingErrors().get(0).printStackTrace();
				for (ExcelImportException error : context.getParsingErrors()) {
					System.out.println("    " + error.getMessage() + " -> " + error.getStackTrace()[0].toString());
				}
			}
			if (!context.getValidationErrors().isEmpty()) {
				if(sb.lastIndexOf("parsing")!=-1)
					sb.append(" and ");
				sb.append("validation");
				
				System.out.println("- de validation :");
				for (ExcelImportException error : context.getValidationErrors()) {
					System.out.println("    " + error.getMessage() + " -> " + error.getStackTrace()[0].toString());
				}
			}
			if (!context.getExtractionErrors().isEmpty()) {
				if(sb.lastIndexOf("parsing")!=-1 || sb.lastIndexOf("validation")!=-1)
					sb.append(" and ");
				sb.append("extraction");
				
				System.out.println("- d'extraction :");
				for (ExcelImportException error : context.getExtractionErrors()) {
					System.out.println("    " + error.getMessage() + " -> " + error.getStackTrace()[0].toString());
				}
			}
			responseBean.setStatus(false);
			responseBean.setMessage(sb.toString());
			return Response.ok((Object) responseBean).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setStatus(false);
			responseBean.setMessage("Error : " + e.getMessage());
			logger.error(
					"Exception (WebService : '/app/controlTms', Action : 'deleteJeuDonneesControl', methode : @DELETE)",
					e);
			return Response.ok((Object) responseBean).build();
			
		}

	}

}
