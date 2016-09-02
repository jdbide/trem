package com.avancial.socle.ihm.menu.model.databean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avancial.socle.ihm.menu.Rubrique;

@Entity
@Table(name = "socle_ihm_rubrique")
@NamedQueries({ @NamedQuery(name = RubriqueDataBean.QUERY_GET_ALL,
      query = "FROM RubriqueDataBean"),
      @NamedQuery(name = RubriqueDataBean.QUERY_GET_ALL_ACTIF,
            query = "From RubriqueDataBean where actif=1"),
      @NamedQuery(name = RubriqueDataBean.QUERY_GET_ACTIF_BY_RUBRIQUE,
            query = "FROM RubriqueDataBean where actif=1 and idRubrique = :idRubrique"),
      @NamedQuery(name = RubriqueDataBean.QUERY_GET_BY_ID,
            query = "FROM RubriqueDataBean where id = :id"),
      @NamedQuery(name = RubriqueDataBean.QUERY_DELETE_BY_ID,
            query = "DELETE FROM RubriqueDataBean where id = :id") })
public class RubriqueDataBean extends Rubrique implements Serializable {
   public final static String     QUERY_GET_ALL               = "rubriqueGetAll";
   public final static String     QUERY_GET_ALL_ACTIF         = "rubriqueGetAllActif";
   public final static String     QUERY_GET_ACTIF_BY_RUBRIQUE = "rubriqueGetActifByRubrique";
   public static final String     QUERY_GET_BY_ID             = "rubriqueGetById";
   public static final String     QUERY_DELETE_BY_ID          = "rubriqueDeleteById";

   private static final long      serialVersionUID            = 1L;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "idIhmRubrique")
   private List<ChapitreDataBean> chapitres;

   public List<ChapitreDataBean> getChapitres() {
      return this.chapitres;
   }

   public void setChapitres(List<ChapitreDataBean> chapitres) {
      this.chapitres = chapitres;
   }

}
