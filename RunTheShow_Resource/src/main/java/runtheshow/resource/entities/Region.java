/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author maxim
 */
@Entity
@Table(name = "REGION")
public class Region extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "region_nom")
    @NotNull
    private String nom;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "localisation")
    @JsonBackReference
    private List<ProfileArtiste> profils;

    public Region() {
    }

    public Region(String nom, List<ProfileArtiste> profils) {
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
