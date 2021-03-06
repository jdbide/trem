package com.avancial.app.data.databean.importMotrice;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;
import com.avancial.app.data.databean.CompagnieEntity;

@Entity
@Table(name = "tremas_motrice_ref_gare")
@NamedQueries({@NamedQuery(name = "MotriceRefGare.getAll", query = "SELECT t FROM MotriceRefGareEntity t"),
        @NamedQuery(name = "MotriceRefGare.getById",
                query = "SELECT t FROM MotriceRefGareEntity t WHERE t.idMotriceRefGare = :idMotriceRefGare"),
        @NamedQuery(name = "MotriceRefGare.getUnique",
                query = "SELECT t FROM MotriceRefGareEntity t where t.codeGareMotriceRefGare = :codeGare and t.compagnie = :compagnie"),
        @NamedQuery(name = "MotriceRefGare.getCodeGareByLabelAndCompagnie",
                query = "SELECT t FROM MotriceRefGareEntity t where t.labelMotriceRefGare = :labelGare and t.compagnie = :compagnie"),
        @NamedQuery(name = "MotriceRefGare.getByCompagnie",
                query = "SELECT t FROM MotriceRefGareEntity t where t.compagnie = :compagnie"),
        @NamedQuery(name = "MotriceRefGare.getListGareByOd",
                query = "SELECT t FROM MotriceRefGareEntity t join t.motriceRefOd2gares od2g join od2g.motriceRefODEntity refOd where refOd.idMotriceRefOd = :idMotriceRefOd"),})
public class MotriceRefGareEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRefGare;

    @Column(length = 5, nullable = false)
    private String codeGareMotriceRefGare;

    @Column(length = 50, nullable = true)
    private String labelMotriceRefGare;

    @ManyToOne
    @JoinColumn(name = "idCompagnie")
    @ForeignKey(name = "FK_motrice_ref_gare_idCompagnie")
    private CompagnieEntity compagnie;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "motriceRefGareEntity")
    private List<MotriceRefOd2gareEntity> motriceRefOd2gares;

    public Long getIdMotriceRefGare() {
        return this.idMotriceRefGare;
    }

    public void setIdMotriceRefGare(Long idMotriceRefGare) {
        this.idMotriceRefGare = idMotriceRefGare;
    }

    public String getCodeGareMotriceRefGare() {
        return this.codeGareMotriceRefGare;
    }

    public void setCodeGareMotriceRefGare(String codeGareMotriceRefGare) {
        this.codeGareMotriceRefGare = codeGareMotriceRefGare;
    }

    public String getLabelMotriceRefGare() {
        return this.labelMotriceRefGare;
    }

    public void setLabelMotriceRefGare(String labelMotriceRefGare) {
        this.labelMotriceRefGare = labelMotriceRefGare;
    }

    public CompagnieEntity getCompagnie() {
        return this.compagnie;
    }

    public void setCompagnie(CompagnieEntity compagnie) {
        this.compagnie = compagnie;
    }

    public List<MotriceRefOd2gareEntity> getMotriceRefOd2gares() {
        return this.motriceRefOd2gares;
    }

    public void setMotriceRefOd2gares(List<MotriceRefOd2gareEntity> motriceRefOd2gares) {
        this.motriceRefOd2gares = motriceRefOd2gares;
    }

}
