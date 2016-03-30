/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Sonatines
 */
@Entity
@Table(name = "TAG_SOUS")
public class TaguerSousEvent extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
	@JoinColumn(name = "TAG_LIBELLE")
	private Tag tag;
    
    @ManyToOne
	@JoinColumn(name = "SOUSEVENEMENT_ID")
	private SousEvenement sousEvent;
    
    // constructeurs
    public TaguerSousEvent() {

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

    /**
     * @return the sousEvent
     */
    public SousEvenement getSousEvent() {
        return sousEvent;
    }

    /**
     * @param sousEvent the sousEvent to set
     */
    public void setSousEvent(SousEvenement sousEvent) {
        this.sousEvent = sousEvent;
    }
    
    
}
