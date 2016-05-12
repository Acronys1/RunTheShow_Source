package runtheshow.resource.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Propriétés de la classe
     */
    @Column(name = "user_login")
    @NotNull
    private String login;

    @Column(name = "user_password")
    @NotNull
    private String password;

    @Column(name = "user_enabled")
    @NotNull
    private Boolean enabled;

    @Column(name = "user_nom")
    @NotNull
    private String nom;

    @Column(name = "user_prenom")
    @NotNull
    private String prenom;

    @Column(name = "user_artiste_nom")
    private String nomArtiste;

    @Column(name = "user_sexe")
    private int sexe;

    @Temporal(TemporalType.DATE)
    @Column(name = "user_date_de_naissance")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Calendar dateDeNaissance;

    @Column(name = "user_mail_contact")
    private String mailContact;

    @Column(name = "user_telephone_fixe")
    private String telephoneFixe;

    @Column(name = "user_telephone_portable")
    private String telephonePortable;

    @Column(name = "user_adresse")
    private String adresse;

    @Column(name = "user_code_postal")
    private String codePostal;

    @Column(name = "user_ville")
    private String ville;

    @Lob
    @Column(name = "user_description")
    private String description;

    @Column(name = "user_photo")
    private String photo;

    /**
     * Relations avec les aures entités
     */
    @OneToMany(mappedBy = "createur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonBackReference
    private List<Evenement> mesEvenements;

    @OneToMany(mappedBy = "expediteur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Invitation> mesInvitationsExpediteur;

    @OneToMany(mappedBy = "destinataire", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Invitation> mesInvitationsDestintaire;

    @OneToMany(mappedBy = "organisateur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Contrat> mesContratsOrganisateur;

    @OneToMany(mappedBy = "artiste", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Contrat> mesContratsArtiste;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
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

    public User(String login, String password, String nom, String prenom, String nomArtiste, String mailContact, int sexe, List<Role> roles) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.enabled = true;
        this.nomArtiste = nomArtiste;
        this.mailContact = mailContact;
        this.sexe = sexe;
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

        String dateDeNaissanceFormatted = "";

        if (dateDeNaissance != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dateDeNaissanceFormatted = format.format(dateDeNaissance.getTime());
        }

        return String.format("{\"id\":\"%s\","
                + "\"login\":\"%s\","
                + "\"nom\":\"%s\","
                + "\"prenom\":\"%s\","
                + "\"nomArtiste\":\"%s\","
                + "\"sexe\":\"%s\","
                + "\"dateDeNaissance\":\"%s\","
                + "\"mailContact\":\"%s\","
                + "\"telephoneFixe\":\"%s\","
                + "\"telephonePortable\":\"%s\","
                + "\"adresse\":\"%s\","
                + "\"codePostal\":\"%s\","
                + "\"ville\":\"%s\","
                + "\"description\":\"%s\","
                + "\"photo\":\"%s\","
                + "\"enabled\":%s,"
                + "\"roles\":%s}",
                id, login, nom, prenom, nomArtiste, sexe, dateDeNaissanceFormatted, mailContact, telephoneFixe, telephonePortable, adresse, codePostal, ville, description, photo, enabled, getRoles().toString());
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public Calendar getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Calendar dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getMailContact() {
        return mailContact;
    }

    public void setMailContact(String mailContact) {
        this.mailContact = mailContact;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    public String getTelephonePortable() {
        return telephonePortable;
    }

    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
