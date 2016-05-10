/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author maxim
 */

@Entity
@Table(name = "PROFIL_ARTISTE")
public class ProfilArtiste extends AbstractEntity{
    
    private static final long serialVersionUID = 1L;

    @Column(name="profil_artiste_nom")
    private String nomArtiste;
    
    @Column(name="profil_artiste_type")
    private String typeArtiste;
    
    @Column(name="profil_artiste_taille")
    private int tailleGroupe;
    
    @Column(name="profil_artiste_note")
    private Float noteArtiste;
    
    @Lob
    @Column(name="profil_artiste_description")
    private String descriptionArtiste;
    
    @Column(name="profil_artiste_imageprofil")
    private String imageProfilArtiste;
    
    @Column(name="profil_artiste_imagebanniere")
    private String imageBanniereArtiste;
    
}
