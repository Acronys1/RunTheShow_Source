package runtheshow.resource.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import runtheshow.resource.entities.ProfileArtiste;
import runtheshow.resource.entities.Region;
import runtheshow.resource.entities.TypeArtiste;
import runtheshow.resource.entities.User;

public interface ProfileArtisteRepository extends CrudRepository<ProfileArtiste, Long> {

    // recherche d'un profile d'artiste via son nom
    ProfileArtiste findByNomArtiste(String nom);

    ProfileArtiste findById(Long id);

    ProfileArtiste findByUserArtiste(User unUser);

    List<ProfileArtiste> findByLocalisationIn(List<Region> regions);

    @Query("SELECT DISTINCT pa "
            + "FROM ProfileArtiste pa "
            + "JOIN pa.typeArtiste pat "
            + "WHERE pat.id IN :type")
    public List<ProfileArtiste> findArtisteByType(@Param("type") Set<Long> type);
    
    @Query("SELECT DISTINCT pa "
            + "FROM ProfileArtiste pa "
            + "JOIN pa.localisation pal "
            + "WHERE pal.id IN :localisation")
    public List<ProfileArtiste> findArtisteByLocalisation(@Param("localisation") Set<Long> localisation);
    
    @Query("SELECT DISTINCT pa "
            + "FROM ProfileArtiste pa "
            + "JOIN pa.typeArtiste pat JOIN pa.localisation pal "
            + "WHERE pat.id IN :type AND pal.id IN :localisation")
    public List<ProfileArtiste> findArtisteByTypeAndLocalisation(@Param("type") Set<Long> type, @Param("localisation") Set<Long> localisation);
}
