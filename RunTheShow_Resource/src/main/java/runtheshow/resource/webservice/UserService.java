/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.webservice;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    
    @RequestMapping("/all")
    public String getAllUser() {
        return metier.getAllUser().toString();
    }
    
    @RequestMapping("/current")
    public String getCurrentUser(Principal user) {
        return metier.getUserByName(user.getName()).toString();
    }
}
