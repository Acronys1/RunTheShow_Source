/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "Evenement")
public class Evenement extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    @Column(name="event_intitule")
    @NotNull
    private String intitule;
    
    @Lob
    @Column(name="event_description")
    @NotNull
    private String description;
    
    @Column(name="event_dateHeureDebut")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone="CET")
    private Calendar dateHeureDebut;
    
    @Column(name="event_dateHeureFin")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone="CET")
    private Calendar dateHeureFin;
    
    @Lob
    @Column(name="event_infoComp")
    private String infoComp;
    
    @ManyToOne
    private User createur; 
    
    @ManyToOne
    private Lieu lieu;
    
    @OneToMany(mappedBy = "evenement", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<SousEvenement> mesSousEvenements;
    
    @OneToMany(mappedBy = "evenement", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    private List<Media> mesMedias;
    
    @ManyToMany
    @JsonBackReference("event_tag")
    @JoinTable(
            name = "TAG_EV",
            joinColumns = @JoinColumn(name = "EVENEMENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_LIBELLE", referencedColumnName = "TAG_LIBELLE"))
    private List<Tag> tag;

    // constructeur
    public Evenement() {
        this.mesSousEvenements = new ArrayList<>();
        this.mesMedias = new ArrayList<>();
    }
    
    public Evenement(String intitule, String description, Calendar dateHeureDebut, Calendar dateHeureFin, String infoComp, User u){
        this.intitule = intitule;
        this.description = description;
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
        this.infoComp = infoComp;
        this.createur = u;
        this.mesSousEvenements = new ArrayList<>();
        this.mesMedias = new ArrayList<>();
        this.tag = new ArrayList<>();
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
     * @return the dateHeureDebut
     */
    public java.util.Calendar getDateHeureDebut() {
        return dateHeureDebut;
    }

    /**
     * @param dateHeureDebut the dateHeureDebut to set
     */
    public void setDateHeureDebut(java.util.Calendar dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    /**
     * @return the dateHeureFin
     */
    public java.util.Calendar getDateHeureFin() {
        return dateHeureFin;
    }

    /**
     * @param dateHeureFin the dateHeureFin to set
     */
    public void setDateHeureFin(java.util.Calendar dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    /**
     * @return the infoComp
     */
    public String getInfoComp() {
        return infoComp;
    }

    /**
     * @param infoComp the infoComp to set
     */
    public void setInfoComp(String infoComp) {
        this.infoComp = infoComp;
    }

    /**
     * @return the createur
     */
    public User getCreateur() {
        return createur;
    }

    /**
     * @param createur the createur to set
     */
    public void setCreateur(User createur) {
        this.createur = createur;
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
    
    public String toString()
    {
        String dateDebutEvent = "";
        String dateFinEvent = "";
        
        if (dateHeureFin != null && dateHeureDebut != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dateDebutEvent = format.format(dateHeureDebut.getTime());
            dateFinEvent = format.format(dateHeureFin.getTime());
        }
        
        return String.format("{\"id\":\"%s\","
                + "\"intitule\":\"%s\","
                + "\"description\":\"%s\","
                + "\"dateHeureDebut\":\"%s\","
                + "\"dateHeureFin\":\"%s\","
                + "\"infoComp\":\"%s\"}",
                id, intitule, description, dateHeureDebut, dateHeureFin, infoComp);
    }
}