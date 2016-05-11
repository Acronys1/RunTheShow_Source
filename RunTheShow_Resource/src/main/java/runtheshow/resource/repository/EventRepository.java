/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.repository;

import java.security.Principal;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import runtheshow.resource.entities.Evenement;
import runtheshow.resource.entities.Role;
import runtheshow.resource.entities.User;

/**
 *
 * @author Sonatines
 */
public interface EventRepository extends CrudRepository<Evenement, Long> {
    
    // Le dernier évènement créé par un utilisateur
    Evenement findTop1ByCreateur(User user);
    
    List<Evenement> findByCreateur(User user);
    
}
