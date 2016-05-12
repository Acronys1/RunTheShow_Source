/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author decalf_m
 */
@Entity
@Table(name = "PROFILE_ARTISTE")
public class ProfileArtiste extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Propriétés de la classe
     */
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk")
    private User userArtiste;

    @Column(name = "profile_artiste_nom")
    @NotNull
    private String nomArtiste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "profile_artiste_type")
    private TypeArtiste typeArtiste;

    @Column(name = "profile_artiste_taille")
    private int tailleGroupe;
    
    @Column(name = "profile_artiste_localisation")
    private String localisation;

    @Column(name = "profile_artiste_note")
    @NotNull
    private Long note;

    @Lob
    @Column(name = "profile_artiste_description")
    private String description;

    @Column(name = "profile_artiste_imageprofile")
    private String imageProfile;

    @Column(name = "profile_artiste_imagebanniere")
    private String imageBanniere;

    @Column(name = "profile_artiste_imagespresentation")
    @ElementCollection
    @OrderColumn(name = "images_profile_artiste")
    private List<String> imagesPresentation;

    @Column(name = "profile_artiste_facebook")
    private String facebookArtiste;

    @Column(name = "profile_artiste_soundcloud")
    private String soundcloudArtiste;

    @Column(name = "profile_artiste_youtube")
    private String youtubeArtiste;

    public ProfileArtiste() {

    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public TypeArtiste getTypeArtiste() {
        return typeArtiste;
    }

    public void setTypeArtiste(TypeArtiste typeArtiste) {
        this.typeArtiste = typeArtiste;
    }
    
    public int getTailleGroupe() {
        return tailleGroupe;
    }

    public void setTailleGroupe(int tailleGroupe) {
        this.tailleGroupe = tailleGroupe;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Long getNote() {
        return note;
    }

    public void setNote(Long note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getImageBanniere() {
        return imageBanniere;
    }

    public void setImageBanniere(String imageBanniere) {
        this.imageBanniere = imageBanniere;
    }

    public List<String> getImagesPresentation() {
        return imagesPresentation;
    }

    public void setImagesPresentation(List<String> imagesPresentation) {
        this.imagesPresentation = imagesPresentation;
    }

    public String getFacebookArtiste() {
        return facebookArtiste;
    }

    public void setFacebookArtiste(String facebookArtiste) {
        this.facebookArtiste = facebookArtiste;
    }

    public String getSoundcloudArtiste() {
        return soundcloudArtiste;
    }

    public void setSoundcloudArtiste(String soundcloudArtiste) {
        this.soundcloudArtiste = soundcloudArtiste;
    }

    public String getYoutubeArtiste() {
        return youtubeArtiste;
    }

    public void setYoutubeArtiste(String youtubeArtiste) {
        this.youtubeArtiste = youtubeArtiste;
    }

    public User getUserArtiste() {
        return userArtiste;
    }

    public void setUserArtiste(User userArtiste) {
        this.userArtiste = userArtiste;
    }

}
