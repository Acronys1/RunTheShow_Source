/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.repository;

import org.springframework.data.repository.CrudRepository;
import runtheshow.resource.entities.SousEvenement;

/**
 *
 * @author Sonatines
 */
public interface SousEventRepository extends CrudRepository<SousEvenement, Long> {
    
}
