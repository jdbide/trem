/**
 * 
 */
package com.avancial.app.serviceDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avancial.app.data.dto.filtrePlanTransport.FiltrePlanTransportDto;
import com.avancial.app.data.dto.filtrePlanTransport.MealServiceDto;
import com.avancial.app.data.dto.filtrePlanTransport.RMCodeDto;
import com.avancial.app.data.dto.filtrePlanTransport.ServiceABoardDto;
import com.avancial.app.data.dto.filtrePlanTransport.ServicesDto;
import com.avancial.app.data.dto.filtrePlanTransport.TrainTrancheDateDto;
import com.avancial.app.data.objetsMetier.PlanTransport.*;

/**
 * Service qui transforme un plan de transport (Filtré) a un plan de transport DTO
 * 
 * @author hamza.laterem
 *
 */
public class FiltrePlanTransportServiceDto {
   private PlanTransport planTransport;
   
   /**
    * Constructor vide
    */
   public FiltrePlanTransportServiceDto () {
      
   }

   /**
    * Methode principale pour le transforme du planTransport a un Filtre planTransportDto
    * 
    * @return FiltrePlanTransportDto
    */
   public FiltrePlanTransportDto transforme() {
      FiltrePlanTransportDto res = new FiltrePlanTransportDto();
      
      if (this.planTransport == null || this.planTransport.getTrains() == null || this.planTransport.getTrains().isEmpty()) {
         return null;
      }

      for (Train train : this.planTransport.getTrains()) {
         if (train.getTranches() != null && !train.getTranches().isEmpty()) {
            for (Tranche tranche : train.getTranches()) {
               if (tranche.getRegime() != null && tranche.getRegime().getListeJours() != null && !tranche.getRegime().getListeJours().isEmpty()) {
                  for (Date date : tranche.getRegime().getListeJours()) {
                     TrainTrancheDateDto trainTrancheDateDto = new TrainTrancheDateDto();
                     
                     trainTrancheDateDto.setDateJour(date);
                     trainTrancheDateDto.setCompany(this.planTransport.getCompagnie().toString());
                     trainTrancheDateDto.setNumeroTrain(train.getNumeroTrain());
                     trainTrancheDateDto.setValidForRR(this.getBooleanToYesNo(train.isValidForRR()));
                     trainTrancheDateDto.setNumeroTranche(tranche.getNumeroTranche());
                     trainTrancheDateDto.setStatus(tranche.getTrancheStatut().toString());
                     
                     this.getDataByAttribut(trainTrancheDateDto, tranche, date);
                     
                     
                     res.addTrainTrancheDateDto(trainTrancheDateDto);
                  }
               }
            }
         }
         
      }
      
      return res;
   }
   
   /**
    * Fonction qui parcoure les attribut d'une tranche pour recuperer les données a inserer dans TrainTrancheDateDto
    * 
    * @param trainTrancheDateDto
    * @param tranche
    * @param date
    */
   @SuppressWarnings("unchecked")
   private void getDataByAttribut(TrainTrancheDateDto trainTrancheDateDto, Tranche tranche, Date currentDate) {
      if (tranche.getAttributs() == null || tranche.getAttributs().isEmpty()) {
         return;
      }
      
      this.getDataByAttributTrainTranche(trainTrancheDateDto, ((List<OrigineDestination>) tranche.getAttributsField(OrigineDestination.class)), ((List<Distribution>) tranche.getAttributsField(Distribution.class)), currentDate);
      this.getDataByAttributForRMCodeDto(trainTrancheDateDto, ((List<Composition>) tranche.getAttributsField(Composition.class)), ((List<FareProfile>) tranche.getAttributsField(FareProfile.class)), currentDate);
      this.getDataByAttributForServicesDto(trainTrancheDateDto, ((List<TypeEquipement>) tranche.getAttributsField(TypeEquipement.class)), ((List<ServiceABord>) tranche.getAttributsField(ServiceABord.class)),
            ((List<Repas>) tranche.getAttributsField(Repas.class)), ((List<Composition>) tranche.getAttributsField(Composition.class)), currentDate);
      this.getDataByAttributForCompositionDto(trainTrancheDateDto, ((List<Composition>) tranche.getAttributsField(Composition.class)), ((List<Specification>) tranche.getAttributsField(Specification.class)), currentDate);
      this.getDataByAttributForStopsDto(trainTrancheDateDto, ((List<Desserte>) tranche.getAttributsField(Desserte.class)), ((List<Restriction>) tranche.getAttributsField(Restriction.class)) ,currentDate);
      
      this.getDataByAttributForTospAndSat(trainTrancheDateDto, ((List<Tosp>) tranche.getAttributsField(Tosp.class)), ((List<CodeSat>) tranche.getAttributsField(CodeSat.class)), currentDate);
   }

   /**
    * @param trainTrancheDateDto
    * @param list
    * @param list2
    * @param date
    */
   private void getDataByAttributForTospAndSat(TrainTrancheDateDto trainTrancheDateDto, List<Tosp> list, List<CodeSat> list2, Date currentDate) {
      // TODO Auto-generated method stub
      
   }

   /**
    * @param trainTrancheDateDto
    * @param list
    * @param list2
    * @param date
    */
   private void getDataByAttributForStopsDto(TrainTrancheDateDto trainTrancheDateDto, List<Desserte> list, List<Restriction> list2, Date currentDate) {
      // TODO Auto-generated method stub
      
   }

   /**
    * Recuperation des données a partir de l'attribut origineDestination et Distribution par date
    * 
    * @param trainTrancheDateDto
    * @param list
    * @param list2
    * @param date
    */
   private void getDataByAttributTrainTranche(TrainTrancheDateDto trainTrancheDateDto, List<OrigineDestination> listOrigineDestination, List<Distribution> listDistribution, Date currentDate) {
      this.getDataByDistribution (trainTrancheDateDto, listDistribution, currentDate);
      this.getDataByOrigineDestination (trainTrancheDateDto, listOrigineDestination, currentDate);
   }

   /**
    * Recuperation de l'INDICATOR FOR DIstribution
    * 
    * @param trainTrancheDateDto
    * @param listDistribution
    * @param currentDate
    */
   private void getDataByDistribution(TrainTrancheDateDto trainTrancheDateDto, List<Distribution> listDistribution, Date currentDate) {
      if (listDistribution != null) {
         for (Distribution distribution : listDistribution) {
            if ( distribution.getRegime() != null &&  distribution.getRegime().getListeJours() != null && !distribution.getRegime().getListeJours().isEmpty()) {
               for (Date date : distribution.getRegime().getListeJours()) {
                  if (date.compareTo(currentDate) == 0) {
                     trainTrancheDateDto.setIndicateurDistribution(distribution.getIndiceDistribution());
                     return;
                  }
               }
            }
         }
      }
      
   }

   /**
    * Recuperation de gare d'origine & destination "OD"
    *
    * @param trainTrancheDateDto
    * @param listOrigineDestination
    * @param currentDate
    */
   private void getDataByOrigineDestination(TrainTrancheDateDto trainTrancheDateDto, List<OrigineDestination> listOrigineDestination, Date currentDate) {
      if (listOrigineDestination != null) {
           for (OrigineDestination origineDestination : listOrigineDestination) {
              if ( origineDestination.getRegime() != null &&  origineDestination.getRegime().getListeJours() != null && !origineDestination.getRegime().getListeJours().isEmpty()) {
                 for (Date date : origineDestination.getRegime().getListeJours()) {
                    if (date.compareTo(currentDate) == 0) {
                       if (origineDestination.getDestination() != null) {
                          trainTrancheDateDto.setDestination(origineDestination.getDestination().getCodeGare());
                       }

                       if (origineDestination.getOrigine() != null) {
                          trainTrancheDateDto.setOrigine(origineDestination.getOrigine().getCodeGare());
                       }

                       return;
                    }
                 }
              }
           }
       }
   }

   /**
    * @param trainTrancheDateDto
    * @param list
    * @param list2
    * @param date
    */
   private void getDataByAttributForCompositionDto(TrainTrancheDateDto trainTrancheDateDto, List<Composition> list, List<Specification> list2, Date currentDate) {
      // TODO Auto-generated method stub
      
   }

   /**
    * @param trainTrancheDateDto
    * @param list4 
    * @param list
    * @param list2
    * @param list3
    * @param date
    */
   private void getDataByAttributForServicesDto(TrainTrancheDateDto trainTrancheDateDto, List<TypeEquipement> listTypeEquipement, List<ServiceABord> listServiceABord, List<Repas> listRepas, List<Composition> listComposition, Date currentDate) {
      ServicesDto servicesDto = new ServicesDto();
      List<ServiceABoardDto> services = new ArrayList<>();
      List<MealServiceDto> mealServices = new ArrayList<>();
      
      this.getDataTypeEquipementForServicesDto(servicesDto, listTypeEquipement, currentDate);
      this.getDataServiceABordForServicesDto(services, listServiceABord, currentDate);
      this.getDataServiceABordForMealServiceDto(mealServices, listComposition, listRepas, currentDate);
      
      
      servicesDto.setServices(services);
      
      
      
      trainTrancheDateDto.setService(servicesDto);
   }

   /**
    * @param mealServices
    * @param listComposition
    * @param listRepas
    * @param currentDate
    */
   private void getDataServiceABordForMealServiceDto(List<MealServiceDto> mealServices, List<Composition> listComposition, List<Repas> listRepas, Date currentDate) {
      if (listComposition != null && listRepas != null) {
         for (Composition composition : listComposition) {
            if (composition.getVoitures() != null && !composition.getVoitures().isEmpty() && composition.getRegime() != null &&
                  composition.getRegime().getListeJours() != null && !composition.getRegime().getListeJours().isEmpty()) {
               for (Date date : composition.getRegime().getListeJours()) {
                  if (date.compareTo(currentDate) == 0) {
                     for (Voiture voiture : composition.getVoitures()) {
                        for (Repas repas : listRepas) {
                           if (repas.getRegime() != null &&  repas.getRegime().getListeJours() != null && !repas.getRegime().getListeJours().isEmpty()) {
                              for (Date dateMeal : composition.getRegime().getListeJours()) {
                                 if (dateMeal.compareTo(currentDate) == 0) {
                                    MealServiceDto mealServiceDto = new MealServiceDto();
                                    mealServiceDto.setNumeroCoach(voiture.getNumeroVoiture());
                                    /*
                                     * TODO a enlever 1 c 4
                                     */
                                    mealServiceDto.setMealType("Meal Service");
                                    mealServiceDto.
                                    
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      
   }

   /**
    * Recuperation des services par date
    * @param services
    * @param listServiceABord
    * @param currentDate
    */
   private void getDataServiceABordForServicesDto(List<ServiceABoardDto> services, List<ServiceABord> listServiceABord, Date currentDate) {
      if (listServiceABord != null) {
         for (ServiceABord serviceABord : listServiceABord) {
            if (serviceABord.getRegime() != null &&  serviceABord.getRegime().getListeJours() != null && !serviceABord.getRegime().getListeJours().isEmpty()) {
               for (Date date : serviceABord.getRegime().getListeJours()) {
                  if (date.compareTo(currentDate) == 0) {
                     ServiceABoardDto serviceABoardDto = new ServiceABoardDto();
                     serviceABoardDto.setCode(serviceABord.getCodeService());
                     serviceABoardDto.setClasse(serviceABord.getClasse().toString());
                     serviceABoardDto.setLibelle("");
                     serviceABoardDto.setManualAuto("");
                     serviceABoardDto.setOrigine(serviceABord.getOrigine() == null ? "" : serviceABord.getOrigine().getCodeGare());
                     serviceABoardDto.setDestination(serviceABord.getDestination() == null ? "" : serviceABord.getDestination().getCodeGare());
                     services.add(serviceABoardDto);
                  }
               }
            }
         }
      }
   }

   /**
    * @param servicesDto
    * @param listTypeEquipement
    */
   private void getDataTypeEquipementForServicesDto(ServicesDto servicesDto, List<TypeEquipement> listTypeEquipement, Date currentDate) {
      if (listTypeEquipement != null) {
         for (TypeEquipement typeEquipement : listTypeEquipement) {
            if (typeEquipement.getRegime() != null &&  typeEquipement.getRegime().getListeJours() != null && !typeEquipement.getRegime().getListeJours().isEmpty()) {
               for (Date date : typeEquipement.getRegime().getListeJours()) {
                  if (date.compareTo(currentDate) == 0) {
                     servicesDto.setCodeEquipement(typeEquipement.getTypeEquipement());
                     return;
                  }
               }
            }
         }
      }
   }

   /**
    * @param trainTrancheDateDto
    * @param list
    * @param list2
    * @param date
    */
   private void getDataByAttributForRMCodeDto(TrainTrancheDateDto trainTrancheDateDto, List<Composition> listComposition, List<FareProfile> listFareProfile, Date currentDate) {
      RMCodeDto rmCodeDto = new RMCodeDto();

      this.getDataCompositionForRMCodeDto(rmCodeDto, listComposition, currentDate);
      this.getDataFareProfileForRMCodeDto(rmCodeDto, listFareProfile, currentDate);
      
      trainTrancheDateDto.setRmCode(rmCodeDto);
   }

   /**
    * @param rmCodeDto
    * @param listFareProfile
    * @param currentDate
    */
   private void getDataFareProfileForRMCodeDto(RMCodeDto rmCodeDto, List<FareProfile> listFareProfile, Date currentDate) {
      if (listFareProfile != null) {
         for (FareProfile fareProfile : listFareProfile) {
            if (fareProfile.getRegime() != null &&  fareProfile.getRegime().getListeJours() != null && !fareProfile.getRegime().getListeJours().isEmpty()) {
               for (Date date : fareProfile.getRegime().getListeJours()) {
                  if (date.compareTo(currentDate) == 0) {
                     rmCodeDto.setFareProfileCode(fareProfile.getFareProfileCode());
                     return;
                  }
               }
            }
         }
      }
   }

   /**
    * Remplir l'objet {@link RMCodeDto} by {@link Composition}
    * @param rmCodeDto
    * @param listComposition
    * @param currentDate
    */
   private void getDataCompositionForRMCodeDto(RMCodeDto rmCodeDto, List<Composition> listComposition, Date currentDate) {
      if (listComposition != null) {
         for (Composition composition : listComposition) {
            if (composition.getRegime() != null &&  composition.getRegime().getListeJours() != null && !composition.getRegime().getListeJours().isEmpty()) {
               for (Date date : composition.getRegime().getListeJours()) {
                  if (date.compareTo(currentDate) == 0) {
                     rmCodeDto.setRmCode(composition.getCodeRm());
                     rmCodeDto.setCodeRame1(composition.getCodeRame());
                     /*
                      * TODO remplisage codeRame2 avec la bonne valeur
                      */
                     rmCodeDto.setCodeRame2("");
                     return;
                  }
               }
            }
         }
      }
   }

   /**
    * Fonction qui prend un boolean et elle renvoie un String
    *    true = "YES"
    *    false = "NO"
    * @param validForRR
    * @return
    */
   private String getBooleanToYesNo(boolean value) {
      return ((value) ? "YES" : "NO");
   }

   /**
    * @return the planTransport
    */
   public PlanTransport getPlanTransport() {
      return planTransport;
   }

   /**
    * @param planTransport the planTransport to set
    */
   public void setPlanTransport(PlanTransport planTransport) {
      this.planTransport = planTransport;
   }
}
