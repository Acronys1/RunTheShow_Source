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
import runtheshow.resource.entities.Evenement;
import runtheshow.resource.entities.User;
import runtheshow.resource.metiers.IEventMetier;

/**
 *
 * @author Sonatines
 */
@RestController
@RequestMapping("/event")
public class EventService {
    
    @Autowired
    private IEventMetier metier;
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public Boolean addEvent(Principal user, @RequestBody Evenement event, HttpServletResponse response) {
        return metier.addEvent(user, event);
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Evenement> getAllEvent(Principal user, HttpServletResponse response) {
        return metier.getAllEvent(user);
    }
}
