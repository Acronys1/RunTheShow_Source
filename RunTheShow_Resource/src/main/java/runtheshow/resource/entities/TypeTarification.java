/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "TypeTarification")
public class TypeTarification implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Attributs de classe
     */
    
    @Column(name="typetarification_libelle")
    @NotNull
    @Id
    private String statut;
    
    /**
     * Relation avec les autres entités 
     */
    
    @OneToMany(mappedBy="typeTarification",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Collection<Contrat> lst_contrats;
    
    public TypeTarification(){
        lst_contrats = new ArrayList<>();
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * @return the lst_contrats
     */
    public ArrayList<Contrat> getLst_contrats() {
        return (ArrayList<Contrat>) lst_contrats;
    }

    /**
     * @param lst_contrats the lst_contrats to set
     */
    public void setLst_contrats(ArrayList<Contrat> lst_contrats) {
        this.lst_contrats = lst_contrats;
    }
}