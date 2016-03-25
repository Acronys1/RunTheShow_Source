package runtheshow.resource.metiers;

import java.util.List;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;

public interface IUserMetier {

	// liste des utilisateur
	public List<User> getAllUser();
        
        //récupérer un utilisateur par son nom
        public User getUserByName(String name);
        
        //ajout/update d'un utilisateur
        public Boolean AddUpdateUser(User user);
        
        public List<Role> getAllRole();
}