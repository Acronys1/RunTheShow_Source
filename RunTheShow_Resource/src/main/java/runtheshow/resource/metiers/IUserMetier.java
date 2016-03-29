package runtheshow.resource.metiers;

import java.util.List;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;

public interface IUserMetier {

	// liste des utilisateur
	public List<User> getAllUser();
        
        //récupérer un utilisateur par son nom
        public User getUserByName(String name);
        
        //ajout d'un utilisateur
        public Boolean AddUser(User user);
        
        //update d'un utilisateur
        public Boolean UpdateUser(User user);
        
        //delete d'un utilisateur
        public Boolean DeleteUser(User user);
        
        public List<Role> getAllRole();
}