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
import runtheshow.resource.entities.SousEvenement;
import runtheshow.resource.metiers.ISousEventMetier;

/**
 *
 * @author Sonatines
 */
@RestController
@RequestMapping("/sousEvent")
public class SousEventService {

    @Autowired
    private ISousEventMetier metier;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public Boolean addSousEvent(Principal user, @RequestBody SousEvenement sousEvent, HttpServletResponse response) {
        return metier.addSousEvent(user, sousEvent);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<SousEvenement> getAllSousEvent(Principal user, HttpServletResponse response) {
        return metier.getAllSousEvent(user);
    }

    @RequestMapping(value = "/filter/{id}", method = RequestMethod.GET)
    public List<SousEvenement> getSousEventByIdEvent(@PathVariable String id) {
        long idEvent = Long.parseLong(id);
        return metier.getSousEventByIdEvent(idEvent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<SousEvenement> getSousEventById(@PathVariable String id, Principal user) {
        long idSousEvent = Long.parseLong(id);
        return metier.getSousEventById(idSousEvent, user);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
    public Boolean updateUser(@RequestBody SousEvenement sevent, HttpServletResponse response) {
        return metier.updateSousEvenement(sevent);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public Boolean deleteSousEvent(@RequestBody SousEvenement sevent, HttpServletResponse response) {
        return metier.deleteSousEvenement(sevent);
    }
}
