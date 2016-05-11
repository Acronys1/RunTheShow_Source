/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.repository;

import org.springframework.data.repository.CrudRepository;
import runtheshow.resource.entities.TypeArtiste;

/**
 *
 * @author simaydin
 */
public interface TypeArtisteRepository extends CrudRepository<TypeArtiste, Long> {
	// recherche d'un rôle via son nom
	TypeArtiste findRoleByNom(String name);
}
