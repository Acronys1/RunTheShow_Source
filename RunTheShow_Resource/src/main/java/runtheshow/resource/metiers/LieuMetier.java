/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.metiers;

import org.springframework.beans.factory.annotation.Autowired;
import runtheshow.resource.entities.Lieu;
import runtheshow.resource.repository.LieuRepository;
import org.springframework.stereotype.Service;
import runtheshow.resource.entities.Evenement;
import runtheshow.resource.repository.EventRepository;

/**
 *
 * @author Sonatines
 */
@Service("lieu_metier")
public class LieuMetier implements ILieuMetier {
    
    @Autowired
    private LieuRepository lieuRepository;
    
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Boolean addLieu(Lieu lieu) 
    {
        Lieu lieuPersist = new Lieu(lieu.getAdresse(), lieu.getCp(), lieu.getDescription());
        lieu = lieuRepository.save(lieuPersist);
        
        return lieu != null;
    }

    @Override
    public Boolean updateLieu(Lieu lieu) {
        Lieu lieuBind = lieuRepository.findOne(lieu.getId());
        lieuBind.setAdresse(lieu.getAdresse());
        lieuBind.setCp(lieu.getCp());
        lieuBind.setDescription(lieu.getDescription());
        
        lieuBind = lieuRepository.save(lieuBind);
        return lieuBind != null;
    }

    @Override
    public Boolean deleteLieu(Evenement event) {
        Evenement ev = eventRepository.findOne(event.getId());
        
        lieuRepository.delete(ev.getLieu());
        
        return lieuRepository.findOne(ev.getLieu().getId()) != null;
    }
}
