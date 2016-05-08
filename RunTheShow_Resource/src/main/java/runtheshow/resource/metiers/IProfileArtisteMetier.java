package runtheshow.resource.metiers;

import java.security.Principal;
import java.util.List;
import runtheshow.resource.entities.ProfileArtiste;

public interface IProfileArtisteMetier {

    // liste des profiles d'artiste
    public List<ProfileArtiste> getAllArtiste();

    //récupérer un utilisateur par son nom
    public ProfileArtiste getArtisteByName(String name);

    //update d'un artiste
    public Boolean UpdateArtiste(ProfileArtiste artiste, Principal user);

    //recherche d'un artiste par le nom
    public List<ProfileArtiste> getArtisteBySearch(String artisteCherche);
}
