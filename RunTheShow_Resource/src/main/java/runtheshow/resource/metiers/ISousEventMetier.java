/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.metiers;

import java.security.Principal;
import java.util.List;
import runtheshow.resource.entities.SousEvenement;
import runtheshow.resource.entities.User;

/**
 *
 * @author Sonatines
 */
public interface ISousEventMetier {
    
    public Boolean addSousEvent(Principal user, SousEvenement sousEvent);

    public List<SousEvenement> getAllSousEvent(Principal user);
    
    public SousEvenement findSousEventById(Long id);

    public List<SousEvenement> getSousEventByIdEvent(Long id);

    public List<SousEvenement> getSousEventById(Long idSousEvent, Principal user);
}
