/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "SousEvenement")
public class SousEvenement extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    @Column(name="sevent_dateDebut")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone="CET")
    private java.util.Calendar dateDebut;
    
    @Column(name="sevent_dateFin")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone="CET")
    private java.util.Calendar dateFin;
    
    @Lob
    @Column(name="sevent_description", length = 500)
    @NotNull
    private String intitule;
    
    @Column(name="sevent_etage")
    @NotNull
    private int etage;
    
    @Column(name="sevent_scene")
    @NotNull
    private int scene;
    
    @Column(name="sevent_statut")
    @NotNull
    private String statut;
    
    @ManyToOne
    private Evenement evenement;
    
    @OneToMany(mappedBy="sousEvenement",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    private List<Contrat> lesContrats;
    
    @OneToMany(mappedBy="sousEvenement",cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Invitation> lesInvitations;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference("tag_sousevent")
    @JoinTable(
            name = "TAG_SOUSEVENT",
            joinColumns = @JoinColumn(name = "SOUSEVENEMENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_LIBELLE", referencedColumnName = "TAG_LIBELLE"))
    private List<Tag> tag;
    
    @ManyToOne
    private User user;
    
    public SousEvenement(){
        this.lesContrats = new ArrayList<>();
        this.lesInvitations = new ArrayList<>();
    }
    
    public SousEvenement(Calendar dateDebut, Calendar dateFin, String intitule, int etage, Evenement evenement)
    {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.intitule = intitule;
        this.etage = etage;
        this.scene = 0;
        this.statut = "rien";
        this.evenement = evenement;
        this.lesContrats = new ArrayList<>();
        this.lesInvitations = new ArrayList<>();
    }
    /**
     * @return the dateDebut
     */
    public java.util.Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(java.util.Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * @return the dateFin
     */
    public java.util.Calendar getDateFin() {
        return dateFin;
    }

    /**
     * @param dateFin the dateFin to set
     */
    public void setDateFin(java.util.Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * @return the intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * @param intitule the intitule to set
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * @return the etage
     */
    public int getEtage() {
        return etage;
    }

    /**
     * @param etage the etage to set
     */
    public void setEtage(int etage) {
        this.etage = etage;
    }

    /**
     * @return the scene
     */
    public int getScene() {
        return scene;
    }

    /**
     * @param scene the scene to set
     */
    public void setScene(int scene) {
        this.scene = scene;
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
     * @return the lesContrats
     */
    public List<Contrat> getLesContrats() {
        return lesContrats;
    }

    /**
     * @param lesContrats the lesContrats to set
     */
    public void setLesContrats(List<Contrat> lesContrats) {
        this.lesContrats = lesContrats;
    }

    /**
     * @return the lesInvitations
     */
    public List<Invitation> getLesInvitations() {
        return lesInvitations;
    }

    /**
     * @param lesInvitations the lesInvitations to set
     */
    public void setLesInvitations(List<Invitation> lesInvitations) {
        this.lesInvitations = lesInvitations;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
