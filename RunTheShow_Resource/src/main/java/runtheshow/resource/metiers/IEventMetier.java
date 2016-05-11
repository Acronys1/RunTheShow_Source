/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.metiers;

import java.security.Principal;
import runtheshow.resource.entities.Evenement;
import java.util.List;

/**
 *
 * @author Sonatines
 */
public interface IEventMetier {
    
    public List<Evenement> getAllEvent(Principal user);
    
    public Boolean addEvent(Principal user, Evenement event);
}
