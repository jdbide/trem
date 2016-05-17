package com.avancial.socle.data.model.databean;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "socle_ihm_chapitre")
public class IhmChapitreDataBean extends AbstractDataBean {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChapitre;
    private String libelleIhmChapitre;
    private boolean actifIhmChapitre;
    private Long ordreIhmChapitre;
    
    @ManyToOne
    @JoinColumn(name = "idRubrique", nullable = false)
    @JsonIgnore
    private IhmRubriqueDataBean ihmRubriqueTypeDataBean;
    
    @OneToMany
    @JoinColumn(name = "idChapitre")
    private List<IhmPageDataBean> pages; 

    public Long getIdChapitre() {
        return this.idChapitre;
    }

    public void setIdChapitre(Long idChapitre) {
        this.idChapitre = idChapitre;
    }

    public String getLibelleIhmChapitre() {
        return this.libelleIhmChapitre;
    }

    public void setLibelleIhmChapitre(String libelleIhmChapitre) {
        this.libelleIhmChapitre = libelleIhmChapitre;
    }

    public boolean isActifIhmChapitre() {
        return this.actifIhmChapitre;
    }

    public void setActifIhmChapitre(boolean actifIhmChapitre) {
        this.actifIhmChapitre = actifIhmChapitre;
    }

    public Long getOrdreIhmChapitre() {
        return this.ordreIhmChapitre;
    }

    public void setOrdreIhmChapitre(Long ordreIhmChapitre) {
        this.ordreIhmChapitre = ordreIhmChapitre;
    }

    public IhmRubriqueDataBean getIhmRubriqueTypeDataBean() {
        return this.ihmRubriqueTypeDataBean;
    }

    public void setIhmRubriqueTypeDataBean(IhmRubriqueDataBean ihmRubriqueTypeDataBean) {
        this.ihmRubriqueTypeDataBean = ihmRubriqueTypeDataBean;
    }

    public List<IhmPageDataBean> getPages() {
        return this.pages;
    }

    public void setPages(List<IhmPageDataBean> pages) {
        this.pages = pages;
    }

}
