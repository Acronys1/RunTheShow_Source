package runtheshow.resource.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCrypt;
import runtheshow.resource.entities.ProfileArtiste;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;
import runtheshow.resource.entities.Invitation;
import runtheshow.resource.repository.ProfileArtisteRepository;
import runtheshow.resource.repository.RoleRepository;
import runtheshow.resource.repository.UserRepository;

@Service("user_metier")
public class UserMetier implements IUserMetier {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProfileArtisteRepository artisteRepository;

    @Autowired
    private RoleRepository roleRepository;

    // implémentation interface
    @Override
    public List<User> getAllUser() {
        return Lists.newArrayList(userRepository.findAll());
    }

    // implémentation interface
    @Override
    public List<Role> getAllRole() {
        return Lists.newArrayList(roleRepository.findAll());
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findUserByLogin(name);
    }

    @Override
    public Boolean AddUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        User userPersist = new User(user.getLogin(), user.getPassword(), user.getNom(), user.getPrenom(), user.getNomArtiste(), user.getMailContact(), user.getSexe(), user.getRoles());
        user = userRepository.save(userPersist);
        
            if (user.getRoles().get(0).getId() == 3){
            ProfileArtiste unProfile = new ProfileArtiste();
            unProfile.setNomArtiste(user.getNomArtiste());
            unProfile.setNote(new Long(0));
            unProfile.setUserArtiste(userPersist);
            artisteRepository.save(unProfile);
        }
        
        return user != null;
    }

    @Override
    public Boolean UpdateUser(User user) {

        User userBind = userRepository.findOne(user.getId());
        userBind.setLogin(user.getLogin());
        userBind.setNom(user.getNom());
        userBind.setPrenom(user.getPrenom());
        userBind.setSexe(user.getSexe());
        userBind.setDateDeNaissance(user.getDateDeNaissance());
        userBind.setTelephoneFixe(user.getTelephoneFixe());
        userBind.setTelephonePortable(user.getTelephonePortable());
        userBind.setRoles(user.getRoles());
        userBind.setAdresse(user.getAdresse());
        userBind.setCodePostal(user.getCodePostal());
        userBind.setVille(user.getVille());
        userBind.setDescription(user.getDescription());

        userBind = userRepository.save(userBind);
        return userBind != null;
    }

    @Override
    public Boolean DeleteUser(User user) {
        userRepository.delete(user);
        return userRepository.findOne(user.getId()) != null;
    }

    /**
     * Retourne une liste de 10 artistes à partir d'un mot clé rentré par un user 
     * @param motCherche
     * @return Liste d'artiste
     * @todo Chercher 5 artistes parmis les favorited et 5 parmis la base principale
     */
    @Override
    public List<User> getUsersBySearch(String motCherche) {
        List<User> lstUserArtiste = userRepository.findUserArtisteByMotCle(motCherche);
        return Lists.newArrayList(lstUserArtiste);
    }

    @Override
    public List<User> getUsersArtisteByListId(Set ids) {
        List<User> lstUserArtiste = userRepository.findUserArtisteByIds(ids);
        return Lists.newArrayList(lstUserArtiste);
    }
    
    @Override
    public List<Invitation> getAllInvitationUserReceived(User u) {
        return u.getMesInvitationsDestintaire();
    }
    
    @Override
    public List<Invitation> getAllInvitationUserSent(User u) {
        return u.getMesInvitationsExpediteur();
    }
    
    

}
