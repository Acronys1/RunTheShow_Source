/**
 *
 */
package runtheshow.resource.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    // propriétés
    @Column(name="role_name")
    private String name;

    // constructeurs
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    // identité
    @Override
    public String toString() {
        return String.format("\"%s\"", name);
    }

    // getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
