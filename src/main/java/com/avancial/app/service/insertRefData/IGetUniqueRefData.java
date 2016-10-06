package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;

public interface IGetUniqueRefData<T> {

   public List<T> getUniqueKeyEntity(T refDataEntity, EntityManager em) throws Exception;
}
