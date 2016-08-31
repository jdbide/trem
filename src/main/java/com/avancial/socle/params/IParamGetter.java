/**
 * 
 */
package com.avancial.socle.params;

import java.io.Serializable;
import java.util.List;

import com.avancial.socle.params.beans.IParamBean;
import com.avancial.socle.params.exception.ParamCollectionNotLoadedException;
import com.avancial.socle.params.exception.ParamNotFoundException;

/**
 * Cette classe repr�sente le point d'acc�s unique d'acc�s aux param�tres de l'application.<br/>
 * Pour impl�menter cette interface, il faut �tendre la classe abstraite AParamGetter sous la forme :
 * <ul>
 * 
 * 
 * <li>ParamGetterSocle pour le socle applicatif</li>
 * <li>ParamGetterApp pour l'application</li>
 * </ul>
 * 
 * @author bruno.legloahec
 *
 */

public interface IParamGetter extends Serializable {

   public void add(IParamReader iParamReader);

   public IParamBean getParam(String paramType, String paramName) throws ParamNotFoundException, ParamCollectionNotLoadedException;

   public List<IParamBean> getParamsFromCollection(String paramType) throws ParamCollectionNotLoadedException;

}
