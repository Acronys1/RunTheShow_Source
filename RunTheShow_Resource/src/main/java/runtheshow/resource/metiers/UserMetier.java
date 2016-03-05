package runtheshow.resource.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
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

}
