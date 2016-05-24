/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.metiers;

import ch.qos.logback.classic.util.ContextInitializer;
import com.google.common.collect.Lists;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import runtheshow.resource.entities.Evenement;
import runtheshow.resource.entities.Lieu;
import runtheshow.resource.repository.EventRepository;
import runtheshow.resource.repository.LieuRepository;
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
    
    @Autowired
    private LieuRepository lieuRepository;

    @Override
    public Boolean addEvent(Principal user, Evenement event) 
    {
        System.out.println("Lieu " + event.getLieu().getAdresse() + "  " + event.getLieu().getCp() + "    " + event.getLieu().getDescription());
        Evenement eventPersist = new Evenement(event.getIntitule(), event.getDescription(), event.getDateHeureDebut(), event.getDateHeureFin(), event.getInfoComp(), userRepository.findUserByLogin(user.getName()), lieuRepository.findByAdresseAndCpAndDescription(event.getLieu().getAdresse(), event.getLieu().getCp(), event.getLieu().getDescription()));
        event = eventRepository.save(eventPersist);
        return event != null;
    }

    @Override
    public List<Evenement> getAllEvent(Principal user) {
        return Lists.newArrayList(eventRepository.findByCreateur(userRepository.findUserByLogin(user.getName())));
    }    

    @Override
    public Evenement getEventById(long idEvent) {
        return eventRepository.findById(idEvent);
    }

    @Override
    public Boolean updateEvenement(Evenement event) {
        Evenement eventBind = eventRepository.findOne(event.getId());
        eventBind.setIntitule(event.getIntitule());
        eventBind.setDescription(event.getDescription());
        eventBind.setDateHeureDebut(event.getDateHeureDebut());
        eventBind.setDateHeureFin(event.getDateHeureFin());
        eventBind.setInfoComp(event.getInfoComp());
        
        eventBind = eventRepository.save(eventBind);
        return eventBind != null;
    }

    @Override
    public Boolean deleteEvenement(Evenement event) {
        eventRepository.delete(event);
        return eventRepository.findOne(event.getId()) != null;
    }
}
