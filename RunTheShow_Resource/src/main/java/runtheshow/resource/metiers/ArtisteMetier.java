package runtheshow.resource.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import java.security.Principal;
import runtheshow.resource.entities.ProfileArtiste;
import runtheshow.resource.entities.User;
import runtheshow.resource.repository.ProfileArtisteRepository;
import runtheshow.resource.repository.UserRepository;

@Service("profile_artiste_metier")
public class ArtisteMetier implements IArtisteMetier {

    @Autowired
    private ProfileArtisteRepository artisteRepository;
    
    @Autowired
    private UserRepository userRepository;

    // liste des profiles d'artiste
    @Override
    public List<ProfileArtiste> getAllArtiste(){
        return Lists.newArrayList(artisteRepository.findAll());
    }

    //récupérer un artiste par son nom
    @Override
    public ProfileArtiste getArtisteByName(String name){
        return artisteRepository.findByNomArtiste(name);
    }
    
    //récupérer un utilisateur par son id
    @Override
    public ProfileArtiste getArtisteById(Long id){
        return artisteRepository.findOne(id);
    }

    //update d'un artiste, anti contrafaçon de l'id du profile
    @Override
    public Boolean UpdateArtiste(ProfileArtiste artiste,Principal user){
        User unUser = userRepository.findUserByLogin(user.getName());
        ProfileArtiste unProfile = artisteRepository.findByUserArtiste(unUser);
        artiste.setId(unProfile.getId());
        unProfile = artisteRepository.save(artiste);
        return unProfile!=null;
    }

    @Override
    public List<ProfileArtiste> getArtisteBySearch(String artisteCherche) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
