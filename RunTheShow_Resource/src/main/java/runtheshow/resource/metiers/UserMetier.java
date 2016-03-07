package runtheshow.resource.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.bcrypt.BCrypt;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;
import runtheshow.resource.repository.UserRepository;


@Service("user_metier")
public class UserMetier implements IUserMetier {

	@Autowired
	private UserRepository userRepository;
        

	// implémentation interface
	@Override
	public List<User> getAllUser() {
		return Lists.newArrayList(userRepository.findAll());
	}
        
        @Override
	public User getUserByName(String name) {
		return userRepository.findUserByLogin(name);
	}
        
        @Override
	public Boolean addUser(String login, String password, List<Role> roles) {
                password = BCrypt.hashpw(password, BCrypt.gensalt(12));
                User user = new User(login,password,roles);
                user = userRepository.save(user);
                return user != null;
	}

}
