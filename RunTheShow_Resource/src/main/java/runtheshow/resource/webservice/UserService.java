/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.webservice;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;
import runtheshow.resource.metiers.IUserMetier;

/**
 *
 * @author DECALF_M
 */
@RestController
@RequestMapping("/user")
public class UserService {

    @Autowired
    private IUserMetier metier;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getUserByName(@PathVariable String name) {
        return metier.getUserByName(name).toString();
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public Boolean addUser(@RequestBody User user, HttpServletResponse response) {
        return metier.addUser(user.getLogin(), user.getPassword(), user.getEnabled(), user.getRoles());
    }
    
    @RequestMapping("/all")
    public String getAllUser() {
        return metier.getAllUser().toString();
    }
    
    @RequestMapping("/current")
    public String getCurrentUser(Principal user) {
        return metier.getUserByName(user.getName()).toString();
        
    }
    
    @RequestMapping("/role/all")
    public List<Role> getAllRole() {
        return metier.getAllRole();
        
    }
}
