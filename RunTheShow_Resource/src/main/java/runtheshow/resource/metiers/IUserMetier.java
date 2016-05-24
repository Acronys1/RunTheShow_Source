package runtheshow.resource.metiers;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;
import runtheshow.resource.entities.Invitation;

public interface IUserMetier {

	// liste des utilisateur
	public List<User> getAllUser();
        
        //récupérer un utilisateur par son nom
        public User getUserByName(String name);
        
        //ajout d'un utilisateur
        public Boolean AddUser(User user);
        
        //update d'un utilisateur
        public int UpdateUser(User user);
        
        //delete d'un utilisateur
        public Boolean DeleteUser(User user);
        
        public List<Role> getAllRole();
        
        public List<User> getUsersBySearch(String motCherche);
        
        public List<User> getUsersArtisteByListId(Set ids);
        
        public List<Invitation> getAllInvitationUserReceived(User u);

        public List<Invitation> getAllInvitationUserSent(User u);
}