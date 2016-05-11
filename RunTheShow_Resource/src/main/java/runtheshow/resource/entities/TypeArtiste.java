/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author maxim
 */
@Entity
@Table(name = "TYPE_ARTISTE")
public class TypeArtiste extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "type_nom")
    @NotNull
    private String nom;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ProfileArtiste> profils;

    public TypeArtiste() {
    }

    public TypeArtiste(String nom, List<ProfileArtiste> profils) {
        this.nom = nom;
        this.profils = profils;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<ProfileArtiste> getProfils() {
        return profils;
    }

    public void setProfils(List<ProfileArtiste> profils) {
        this.profils = profils;
    }
    
    
    

}
