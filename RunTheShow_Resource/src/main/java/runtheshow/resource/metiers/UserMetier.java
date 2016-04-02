package runtheshow.resource.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCrypt;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;
import runtheshow.resource.repository.RoleRepository;
import runtheshow.resource.repository.UserRepository;


@Service("user_metier")
public class UserMetier implements IUserMetier {

	@Autowired
	private UserRepository userRepository;
        
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
                User userPersist = new User(user.getLogin(),user.getPassword(), user.getEnabled(), user.getNom(), user.getPrenom(), user.getRoles());
                user = userRepository.save(userPersist);
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
       
}
