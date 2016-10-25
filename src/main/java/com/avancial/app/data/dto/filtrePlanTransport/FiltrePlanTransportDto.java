/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.ArrayList;
import java.util.List;

/**
 * FiltrePlanTransportDto représente le planTransport dto qui sera affiché sur la page SEARCH
 * 
 * @author hamza.laterem
 *
 */
public class FiltrePlanTransportDto {
   private List<TrainTrancheDateDto> listTrainTrancheDateDto;
   
   /**
    * Contructor vide
    */
   public FiltrePlanTransportDto () {
      this.listTrainTrancheDateDto = new ArrayList<TrainTrancheDateDto>();
   }
   
   /**
    * getter listTrainTrancheDateDto
    * @return the trainTrancheDateDto
    * 
    * @return void
    */
   public List<TrainTrancheDateDto> getListTrainTrancheDateDto() {
      return this.listTrainTrancheDateDto;
   }
   
   /**
    * Setter listTrainTrancheDateDto
    * @param trainTrancheDateDto the trainTrancheDateDto to set
    * 
    * @return void
    */
   public void setListTrainTrancheDateDto(List<TrainTrancheDateDto> trainTrancheDateDto) {
      this.listTrainTrancheDateDto = trainTrancheDateDto;
   }

   /**
    * Ajout d'un trainTrancheDateDto dans la listTrainTrancheDateDto
    * @param trainTrancheDateDto
    * 
    * @return void
    */
   public void addTrainTrancheDateDto(TrainTrancheDateDto trainTrancheDateDto) {
      this.listTrainTrancheDateDto.add(trainTrancheDateDto);
   }
}
