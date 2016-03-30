/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Emmjavay
 */
@Entity
@Table(name = "Contrat")
public class Contrat extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    /**
     * Propriétés de la tables
     */
    
    @Column(name="contrat_commentaire", columnDefinition="TEXT")
    @NotNull
    private String commentaire;
    
    @Column(name="contrat_chez")
    @NotNull
    private boolean chez;
    
    @Column(name="contrat_statut")
    @NotNull
    private String statut;
    
    @Column(name="contrat_valSup")
    @NotNull
    private Float valSup;
    
    @Column(name="contrat_valInf")
    private Float valInf;
    
    @Column(name="contrat_aNegocier")
    @NotNull
    private boolean aNegocier;
    
    @Column(name="contrat_okOrganisateur")
    @NotNull
    private boolean okOrganisateur;
    
    @Column(name="contrat_okArtiste")
    @NotNull
    private boolean okArtiste;
    
    @Column(name="contrat_dateNegociation")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private java.util.Calendar dateNegociation;
    
    /*
    * Relations avec les autres tables
    */
    
    @ManyToOne
    @NotNull
    private TypeTarification typeTarification;
    
    @ManyToOne
    @NotNull
    private SousEvenement sousEvenement;
    
    @ManyToOne
    @NotNull
    private User organisateur;
    
    @ManyToOne
    @NotNull
    private User artiste;
    
    

    // constructeur
    public Contrat() {
    }
    
    /**
     * GETTEUR ET SETTEUR
     */

    /**
     * @return the commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * @return the chez
     */
    public boolean isChez() {
        return chez;
    }

    /**
     * @param chez the chez to set
     */
    public void setChez(boolean chez) {
        this.chez = chez;
    }

    /**
     * @return the statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * @return the valSup
     */
    public Float getValSup() {
        return valSup;
    }

    /**
     * @param valSup the valSup to set
     */
    public void setValSup(Float valSup) {
        this.valSup = valSup;
    }

    /**
     * @return the valInf
     */
    public Float getValInf() {
        return valInf;
    }

    /**
     * @param valInf the valInf to set
     */
    public void setValInf(Float valInf) {
        this.valInf = valInf;
    }

    /**
     * @return the aNegocier
     */
    public boolean isaNegocier() {
        return aNegocier;
    }

    /**
     * @param aNegocier the aNegocier to set
     */
    public void setaNegocier(boolean aNegocier) {
        this.aNegocier = aNegocier;
    }

    /**
     * @return the okOrganisateur
     */
    public boolean isOkOrganisateur() {
        return okOrganisateur;
    }

    /**
     * @param okOrganisateur the okOrganisateur to set
     */
    public void setOkOrganisateur(boolean okOrganisateur) {
        this.okOrganisateur = okOrganisateur;
    }

    /**
     * @return the okArtiste
     */
    public boolean isOkArtiste() {
        return okArtiste;
    }

    /**
     * @param okArtiste the okArtiste to set
     */
    public void setOkArtiste(boolean okArtiste) {
        this.okArtiste = okArtiste;
    }

    /**
     * @return the dateNegociation
     */
    public java.util.Calendar getDateNegociation() {
        return dateNegociation;
    }

    /**
     * @param dateNegociation the dateNegociation to set
     */
    public void setDateNegociation(java.util.Calendar dateNegociation) {
        this.dateNegociation = dateNegociation;
    }

    /**
     * @return the typeTarification
     */
    public TypeTarification getTypeTarification() {
        return typeTarification;
    }

    /**
     * @param typeTarification the typeTarification to set
     */
    public void setTypeTarification(TypeTarification typeTarification) {
        this.typeTarification = typeTarification;
    }

    /**
     * @return the sousEvenement
     */
    public SousEvenement getSousEvenement() {
        return sousEvenement;
    }

    /**
     * @param sousEvenement the sousEvenement to set
     */
    public void setSousEvenement(SousEvenement sousEvenement) {
        this.sousEvenement = sousEvenement;
    }

    /**
     * @return the organisateur
     */
    public User getOrganisateur() {
        return organisateur;
    }

    /**
     * @param organisateur the organisateur to set
     */
    public void setOrganisateur(User organisateur) {
        this.organisateur = organisateur;
    }

    /**
     * @return the artiste
     */
    public User getArtiste() {
        return artiste;
    }

    /**
     * @param artiste the artiste to set
     */
    public void setArtiste(User artiste) {
        this.artiste = artiste;
    }
    
    
}
