package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Propriétés de la classe
     */
    @Column(name="user_login")
    @NotNull
    private String login;
    
    @Column(name="user_password")
    @NotNull
    private String password;
    
    @Column(name="user_enabled")
    @NotNull
    private Boolean enabled;
    
    /**
     * Relations avec les aures entités
     */
    
    @OneToMany(mappedBy="createur",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    private List<Evenement> mesEvenements;
    
    @OneToMany(mappedBy="expediteur",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    private List<Invitation> mesInvitationsExpediteur;
    
    @OneToMany(mappedBy="destinataire",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Invitation> mesInvitationsDestintaire;
    
    @OneToMany(mappedBy="organisateur",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Contrat> mesContratsOrganisateur;
    
    @OneToMany(mappedBy="artiste",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Contrat> mesContratsArtiste;
    
    @OneToMany(mappedBy="user",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<SousEvenement> mesSousEvenements;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference("users_roles")
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
        this.mesContratsArtiste = new ArrayList<>();
        this.mesContratsOrganisateur = new ArrayList<>();
        this.mesEvenements = new ArrayList<>();
        this.mesInvitationsDestintaire = new ArrayList<>();
        this.mesInvitationsExpediteur = new ArrayList<>();
    }

    // identité
    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"login\":\"%s\",\"enabled\":%s,\"roles\":%s}", id, login, enabled, getRoles().toString());
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

    /**
     * @return the mesEvenements
     */
    public List<Evenement> getMesEvenements() {
        return mesEvenements;
    }

    /**
     * @param mesEvenements the mesEvenements to set
     */
    public void setMesEvenements(List<Evenement> mesEvenements) {
        this.mesEvenements = mesEvenements;
    }

    /**
     * @return the mesInvitationsExpediteur
     */
    public List<Invitation> getMesInvitationsExpediteur() {
        return mesInvitationsExpediteur;
    }

    /**
     * @param mesInvitationsExpediteur the mesInvitationsExpediteur to set
     */
    public void setMesInvitationsExpediteur(List<Invitation> mesInvitationsExpediteur) {
        this.mesInvitationsExpediteur = mesInvitationsExpediteur;
    }

    /**
     * @return the mesInvitationsDestintaire
     */
    public List<Invitation> getMesInvitationsDestintaire() {
        return mesInvitationsDestintaire;
    }

    /**
     * @param mesInvitationsDestintaire the mesInvitationsDestintaire to set
     */
    public void setMesInvitationsDestintaire(List<Invitation> mesInvitationsDestintaire) {
        this.mesInvitationsDestintaire = mesInvitationsDestintaire;
    }

    /**
     * @return the mesContratsOrganisateur
     */
    public List<Contrat> getMesContratsOrganisateur() {
        return mesContratsOrganisateur;
    }

    /**
     * @param mesContratsOrganisateur the mesContratsOrganisateur to set
     */
    public void setMesContratsOrganisateur(List<Contrat> mesContratsOrganisateur) {
        this.mesContratsOrganisateur = mesContratsOrganisateur;
    }

    /**
     * @return the mesContratsArtiste
     */
    public List<Contrat> getMesContratsArtiste() {
        return mesContratsArtiste;
    }

    /**
     * @param mesContratsArtiste the mesContratsArtiste to set
     */
    public void setMesContratsArtiste(List<Contrat> mesContratsArtiste) {
        this.mesContratsArtiste = mesContratsArtiste;
    }

    /**
     * @return the mesSousEvenements
     */
    public List<SousEvenement> getMesSousEvenements() {
        return mesSousEvenements;
    }

    /**
     * @param mesSousEvenements the mesSousEvenements to set
     */
    public void setMesSousEvenements(List<SousEvenement> mesSousEvenements) {
        this.mesSousEvenements = mesSousEvenements;
    }


}
