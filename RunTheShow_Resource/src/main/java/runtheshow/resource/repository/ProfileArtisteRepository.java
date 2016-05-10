package runtheshow.resource.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import runtheshow.resource.entities.ProfileArtiste;
import runtheshow.resource.entities.User;

public interface ProfileArtisteRepository extends CrudRepository<ProfileArtiste, Long> {

    // recherche d'un profile d'artiste via son nom
    ProfileArtiste findByNomArtiste(String nom);
    
    ProfileArtiste findByUserArtiste(User unUser);
    
    
}
