/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "TAG_EV")
public class TaguerEv extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
	@JoinColumn(name = "TAG_LIBELLE")
	private Tag tag;
    
    @ManyToOne
	@JoinColumn(name = "EVENEMENT_ID")
	private Evenement evenement;
    
    
    
    // constructeurs
	public TaguerEv() {

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
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
