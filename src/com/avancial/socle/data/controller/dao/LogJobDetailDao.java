package com.avancial.socle.data.controller.dao;

import java.util.List;

import com.avancial.socle.data.model.databean.LogJobDetailDataBean;

public class LogJobDetailDao extends AbstractDao {

	@Override
	public List<LogJobDetailDataBean> getAll() {
		return this.getEntityManager().createQuery("FROM LogJobDetailDataBean").getResultList();
	}

}
