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
@Table(name = "SOUSTAG")
public class SousTag implements Serializable{
    
    @Column(name="SOUSTAG_LIBELLE")
    @NotNull
    @Id
    private String libelle;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference("sous_tag")
    @JoinTable(
            name = "TAG_SOUS",
            joinColumns = @JoinColumn(name = "SOUSTAG_LIBELLE", referencedColumnName = "SOUSTAG_LIBELLE"),
            inverseJoinColumns = @JoinColumn(name = "TAG_LIBELLE", referencedColumnName = "TAG_LIBELLE"))
    private List<Tag> tag;
    
    public SousTag(){
        
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
    
}
