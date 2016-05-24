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

/**
 *
 * @author Sonatines
 */
@Service("lieu_metier")
public class LieuMetier implements ILieuMetier {
    
    @Autowired
    private LieuRepository lieuRepository;

    @Override
    public Boolean addLieu(Lieu lieu) 
    {
        Lieu lieuPersist = new Lieu(lieu.getAdresse(), lieu.getCp());
        lieu = lieuRepository.save(lieuPersist);
        
        return lieu != null;
    }
}
