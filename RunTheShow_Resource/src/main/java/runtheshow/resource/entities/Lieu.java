/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "Lieu")
public class Lieu extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    @Column(name="lieu_adresse")
    @NotNull
    private String adresse;
    
    @Column(name="lieu_ville")
    @NotNull
    private String ville;
    
    @Column(name="lieu_codePostal")
    @NotNull
    private String cp;
    
    @Lob
    @Column(name="lieu_description")
    @NotNull
    private String description;
    
    @OneToMany(mappedBy = "lieu", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    private List<Media> mesMedias;
    
    @OneToMany(mappedBy = "lieu", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    private List<Evenement> mesEvenements;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinTable(
            name = "TAG_LIEU",
            joinColumns = @JoinColumn(name = "LIEU_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_LIBELLE", referencedColumnName = "TAG_LIBELLE"))
    private List<Tag> tag;
    
    // constructeur
    public Lieu() {
        this.mesEvenements = new ArrayList<>();
        this.mesMedias = new ArrayList<>();
    }
    
    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the mesMedias
     */
    public List<Media> getMesMedias() {
        return mesMedias;
    }

    /**
     * @param mesMedias the mesMedias to set
     */
    public void setMesMedias(List<Media> mesMedias) {
        this.mesMedias = mesMedias;
    }

    /**
     * @return the mesEvenements
     */
    public List<Evenement> getMesEvenements() {
        return mesEvenements;
    }

    /**
     * @param mesEvenements the mesEvenements to set
     */
    public void setMesEvenements(List<Evenement> mesEvenements) {
        this.mesEvenements = mesEvenements;
    }

    /**
     * @return the tag
     */
    public List<Tag> getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }
}
