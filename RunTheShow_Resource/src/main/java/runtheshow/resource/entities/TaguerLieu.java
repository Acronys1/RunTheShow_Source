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
@Table(name = "TAG_LIEU")
public class TaguerLieu extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    
    @ManyToOne
	@JoinColumn(name = "TAG_LIBELLE")
	private Tag tag;
    
    @ManyToOne
	@JoinColumn(name = "LIEU_ID")
	private Lieu lieu;
    
    // constructeurs
    public TaguerLieu() {

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
     * @return the lieu
     */
    public Lieu getLieu() {
        return lieu;
    }

    /**
     * @param lieu the lieu to set
     */
    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
}
