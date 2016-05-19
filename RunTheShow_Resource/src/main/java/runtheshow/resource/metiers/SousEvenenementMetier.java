/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.metiers;

import ch.qos.logback.classic.util.ContextInitializer;
import com.google.common.collect.Lists;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtheshow.resource.entities.Evenement;
import runtheshow.resource.entities.SousEvenement;
import runtheshow.resource.repository.EventRepository;
import runtheshow.resource.repository.SousEventRepository;
import runtheshow.resource.repository.UserRepository;

/**
 *
 * @author Sonatines
 */
@Service("sous_event_metier")
public class SousEvenenementMetier implements ISousEventMetier {
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SousEventRepository sousEventRepository;

    

    @Override
    public Boolean addSousEvent(Principal user, SousEvenement sousEvent) {
        Evenement event = eventRepository.findTop1ByCreateurOrderByIdDesc(userRepository.findUserByLogin(user.getName()));
        
        /*if(deb.length>0)
        {
            for(int i=0; i<deb.length; i++)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                try
                {
                    Date dateDeb = sdf.parse(deb[i]);
                    Date dateFin = sdf.parse(fin[i]);

                    Calendar calendarDeb = Calendar.getInstance();
                    calendarDeb.setTime(dateDeb);
                    Calendar calendarFin = Calendar.getInstance();
                    calendarFin.setTime(dateFin);

                    SousEvenement sousEventPersit = new SousEvenement(calendarDeb, calendarFin, intitule[i], Integer.parseInt(etage[i]), event);
                    return sousEventRepository.save(sousEventPersit) != null;
                }
                catch(Exception e)
                {
                    System.out.println("Problème dans conversion date de [SousEventMetier : addSousEvent] " + e);
                }


            }
        }*/
        
        SousEvenement sousEventPersist = new SousEvenement(sousEvent.getDateDebut(), sousEvent.getDateFin(), sousEvent.getIntitule(), sousEvent.getEtage(), event);
        sousEvent = sousEventRepository.save(sousEventPersist);
        
        return sousEvent != null;
    }

    @Override
    public List<SousEvenement> getAllSousEvent(Principal user) {
        return Lists.newArrayList(sousEventRepository.findByUser(userRepository.findUserByLogin(user.getName())));
    }

    @Override
    public List<SousEvenement> getSousEventByIdEvent(Long id) {
        return Lists.newArrayList(sousEventRepository.findByEvenement(eventRepository.findById(id)));
    }
    
    @Override
    public SousEvenement findSousEventById(Long id) {
        return sousEventRepository.findOne(id);
    }
    
    @Override
    public List<SousEvenement> getSousEventById(Long id, Principal user) {
        return Lists.newArrayList(sousEventRepository.findByIdAndUser(id, userRepository.findUserByLogin(user.getName())));
    }

    @Override
    public Boolean updateSousEvenement(SousEvenement sevent) {
        SousEvenement sEventBind = sousEventRepository.findById(sevent.getId());
        sEventBind.setDateDebut(sevent.getDateDebut());
        sEventBind.setDateFin(sevent.getDateFin());
        sEventBind.setIntitule(sevent.getIntitule());
        sEventBind.setEtage(sevent.getEtage());
        
        sEventBind = sousEventRepository.save(sEventBind);
        return sEventBind != null;
    }
}
