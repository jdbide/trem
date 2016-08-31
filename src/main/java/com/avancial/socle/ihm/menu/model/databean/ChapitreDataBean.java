package com.avancial.socle.ihm.menu.model.databean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avancial.socle.ihm.menu.Chapitre;

@Entity
@Table(name = "socle_ihm_chapitre")

@NamedQueries({ @NamedQuery(name = ChapitreDataBean.QUERY_GET_ALL,
      query = "FROM ChapitreDataBean"),
      @NamedQuery(name = ChapitreDataBean.QUERY_GET_ALL_ACTIF,
            query = "From ChapitreDataBean where actif=1"),
      @NamedQuery(name = ChapitreDataBean.QUERY_GET_ACTIF_BY_RUBRIQUE,
            query = "FROM ChapitreDataBean where actif=1 and idRubrique = :idRubrique"),
      @NamedQuery(name = ChapitreDataBean.QUERY_GET_BY_ID,
            query = "FROM ChapitreDataBean where id = :id"),
      @NamedQuery(name = ChapitreDataBean.QUERY_DELETE_BY_ID,
            query = "DELETE FROM ChapitreDataBean where id = :id") })

public class ChapitreDataBean extends Chapitre implements Serializable {
   public final static String QUERY_GET_ALL               = "chapitreGetAll";
   public final static String QUERY_GET_ALL_ACTIF         = "chapitreGetAllActif";
   public final static String QUERY_GET_ACTIF_BY_RUBRIQUE = "chapitreGetActifByRubrique";
   public static final String QUERY_GET_BY_ID             = "chapitreGetById";
   public static final String QUERY_DELETE_BY_ID          = "chapitreDeleteById";

   private static final long  serialVersionUID            = 1L;

   @Column(name = "idIhmRubrique")
   private long               idRubrique;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "idIhmChapitre")
   private List<PageDataBean> pages;

   public List<PageDataBean> getPages() {
      return this.pages;
   }

   public void setPages(List<PageDataBean> pages) {
      this.pages = pages;
   }

   /**
    * @return the idRubrique
    */
   public long getIdRubrique() {
      return idRubrique;
   }

   /**
    * @param idRubrique
    *           the idRubrique to set
    */
   public void setIdRubrique(Long idRubrique) {
      this.idRubrique = idRubrique;
   }

}
