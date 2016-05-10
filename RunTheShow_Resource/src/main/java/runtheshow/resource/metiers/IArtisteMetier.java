package runtheshow.resource.metiers;

import java.security.Principal;
import java.util.List;
import runtheshow.resource.entities.ProfileArtiste;

public interface IArtisteMetier {

    // liste des profiles d'artiste
    public List<ProfileArtiste> getAllArtiste();

    //récupérer un artiste par son nom
    public ProfileArtiste getArtisteByName(String name);
    
    //récupérer un artiste par son id
    public ProfileArtiste getArtisteById(Long id);

    //update d'un artiste
    public Boolean UpdateArtiste(ProfileArtiste artiste, Principal user);

    //recherche d'un artiste par le nom
    public List<ProfileArtiste> getArtisteBySearch(String artisteCherche);
}
