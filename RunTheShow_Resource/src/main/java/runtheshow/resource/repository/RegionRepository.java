/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.repository;

import org.springframework.data.repository.CrudRepository;
import runtheshow.resource.entities.Region;
import runtheshow.resource.entities.TypeArtiste;

/**
 *
 * @author simaydin
 */
public interface RegionRepository extends CrudRepository<Region, Long> {
	// recherche d'une region
	
}
