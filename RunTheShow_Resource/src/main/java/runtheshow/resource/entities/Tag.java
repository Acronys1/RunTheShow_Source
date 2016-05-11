/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "TAG")
public class Tag implements Serializable{
    
    @Column(name="TAG_LIBELLE")
    @NotNull
    @Id
    private String libelle;
    
    @ManyToMany(mappedBy = "tag")
    private List<Evenement> evenement;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference("TAGLIEU")
    @JoinTable(
            name = "TAG_LIEU",
            joinColumns = @JoinColumn(name = "TAG_LIBELLE", referencedColumnName = "TAG_LIBELLE"),
            inverseJoinColumns = @JoinColumn(name = "LIEU_ID", referencedColumnName = "ID"))
    private List<Lieu> lieu;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference("TAGSOUSTAG")
    @JoinTable(
            name = "TAG_SOUS",
            joinColumns = @JoinColumn(name = "TAG_LIBELLE", referencedColumnName = "TAG_LIBELLE"),
            inverseJoinColumns = @JoinColumn(name = "SOUSTAG_LIBELLE", referencedColumnName = "SOUSTAG_LIBELLE"))
    private List<SousTag> sousTag;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference("TAGSOUSEVENT")
    @JoinTable(
            name = "TAG_SOUSEVENT",
            joinColumns = @JoinColumn(name = "TAG_LIBELLE", referencedColumnName = "TAG_LIBELLE"),
            inverseJoinColumns = @JoinColumn(name = "SOUSEVENEMENT_ID", referencedColumnName = "ID"))
    private List<SousEvenement> sousEvent;
    
    public Tag(){
        
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return the evenement
     */
    public List<Evenement> getEvenement() {
        return evenement;
    }

    /**
     * @param evenement the evenement to set
     */
    public void setEvenement(List<Evenement> evenement) {
        this.evenement = evenement;
    }

    /**
     * @return the lieu
     */
    public List<Lieu> getLieu() {
        return lieu;
    }

    /**
     * @param lieu the lieu to set
     */
    public void setLieu(List<Lieu> lieu) {
        this.lieu = lieu;
    }

    /**
     * @return the sousTag
     */
    public List<SousTag> getSousTag() {
        return sousTag;
    }

    /**
     * @param sousTag the sousTag to set
     */
    public void setSousTag(List<SousTag> sousTag) {
        this.sousTag = sousTag;
    }

    /**
     * @return the sousEvent
     */
    public List<SousEvenement> getSousEvent() {
        return sousEvent;
    }

    /**
     * @param sousEvent the sousEvent to set
     */
    public void setSousEvent(List<SousEvenement> sousEvent) {
        this.sousEvent = sousEvent;
    }
}
