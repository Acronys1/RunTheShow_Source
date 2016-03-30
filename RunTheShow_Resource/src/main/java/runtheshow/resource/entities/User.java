package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    // propriétés
    @Column(name="user_login")
    @NotNull
    private String login;
    
    @Column(name="user_password")
    @NotNull
    private String password;
    
    @Column(name="user_enabled")
    @NotNull
    private Boolean enabled;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private List<Role> roles;

    // constructeur
    public User() {
    }

    public User(String login, String password, Boolean enabled, List<Role> roles) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    // identité
    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"login\":\"%s\",\"enabled\":%s,\"roles\":%s}", id, login, enabled, roles.toString());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}
