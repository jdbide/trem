/**
 * 
 */
package com.avancial.socle.webService;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author bruno.legloahec
 *
 */
public class AWebService {

   /**
    * Permet de récupérer une liste d'objet de type destClass à partir d'une liste d'objet généralement de type dataBean
    * 
    * @param elements
    * @param destClasse
    * @return
    */
   @SuppressWarnings("unchecked")
   protected List<?> getConvertedList(List<?> elements, @SuppressWarnings("rawtypes") Class destClasse) {
      List<Object> liste = new ArrayList<>();
      Mapper mapper = new DozerBeanMapper();
      Object objetDest;
      for (Object bean : elements) {
         objetDest = mapper.map(bean, destClasse);
         liste.add(objetDest);
      }
      return liste;

   }

   @SuppressWarnings("unchecked")
   protected Object getConvertedObject(Object element, @SuppressWarnings("rawtypes") Class destClasse) {

      Mapper mapper = new DozerBeanMapper();
      Object objetDest;

      objetDest = mapper.map(element, destClasse);

      return objetDest;

   }

}
