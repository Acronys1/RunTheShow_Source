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

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "Invitation")
public class Invitation extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    /**
     * Attributs de classe
     */
    
    @Column(name="invitation_statut")
    @NotNull
    private String statut;
    
    @Column(name="invitation_commentaire", columnDefinition="TEXT")
    @NotNull
    private String commentaire;
    
    /**
     * Relations avec les autre entités
     */
    
    @ManyToOne
    @NotNull
    private SousEvenement sousEvenement;
    
    @ManyToOne
    @NotNull
    private User expediteur;
    
    @ManyToOne
    @NotNull
    private User destinataire;
    
    //Constructeur
    public Invitation(){
        
    }
    
    /**
     * GETTEUR AND SETTEUR
     */

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
     * @return the expediteur
     */
    public User getExpediteur() {
        return expediteur;
    }

    /**
     * @param expediteur the expediteur to set
     */
    public void setExpediteur(User expediteur) {
        this.expediteur = expediteur;
    }

    /**
     * @return the destinataire
     */
    public User getDestinataire() {
        return destinataire;
    }

    /**
     * @param destinataire the destinataire to set
     */
    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }
    
    
}