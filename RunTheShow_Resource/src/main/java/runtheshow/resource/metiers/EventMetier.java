/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.metiers;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import runtheshow.resource.entities.Evenement;
import runtheshow.resource.repository.EventRepository;
import runtheshow.resource.repository.UserRepository;

/**
 *
 * @author Sonatines
 */
@Service("event_metier")
public class EventMetier implements IEventMetier {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Evenement> findAllEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean addEvent(Principal user, Evenement event) 
    {
        
        Evenement eventPersist = new Evenement(event.getIntitule(), event.getDescription(), event.getDateHeureDebut(), event.getDateHeureFin(), event.getInfoComp(), userRepository.findUserByLogin(user.getName()));
        event = eventRepository.save(eventPersist);
        return event != null;
    }
    
    
}
