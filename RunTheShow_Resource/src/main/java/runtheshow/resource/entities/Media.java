/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "Media")
public class Media extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    @Column(name="media_nom")
    @NotNull
    private String nom;
    
    @Column(name="media_renommer")
    @NotNull
    private String renommer;
    
    @Column(name="media_location")
    @NotNull
    private String location;
    
    @Column(name="media_alt")
    @NotNull
    private String alt;
    
    @ManyToOne
    private Evenement evenement;
    
    @ManyToOne
    private Lieu lieu;
    
    public Media(){
        
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the alt
     */
    public String getAlt() {
        return alt;
    }

    /**
     * @param alt the alt to set
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * @return the evenement
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement the evenement to set
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * @return the lieu
     */
    public Lieu getLieu() {
        return lieu;
    }

    /**
     * @param lieu the lieu to set
     */
    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    /**
     * @return the renommer
     */
    public String getRenommer() {
        return renommer;
    }

    /**
     * @param renommer the renommer to set
     */
    public void setRenommer(String renommer) {
        this.renommer = renommer;
    }
    
}
