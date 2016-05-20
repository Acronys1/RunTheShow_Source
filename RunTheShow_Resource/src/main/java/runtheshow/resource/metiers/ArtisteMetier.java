package runtheshow.resource.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import runtheshow.resource.entities.FileUpload;
import runtheshow.resource.entities.ProfileArtiste;
import runtheshow.resource.entities.Region;
import runtheshow.resource.entities.TypeArtiste;
import runtheshow.resource.entities.User;
import runtheshow.resource.repository.FileUploadRepository;
import runtheshow.resource.repository.ProfileArtisteRepository;
import runtheshow.resource.repository.RegionRepository;
import runtheshow.resource.repository.TypeArtisteRepository;
import runtheshow.resource.repository.UserRepository;

@Service("profile_artiste_metier")
public class ArtisteMetier implements IArtisteMetier {

    @Autowired
    private ProfileArtisteRepository artisteRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TypeArtisteRepository typeArtisteRepository;
    
    @Autowired
    private RegionRepository regionRepository;
    
    @Autowired
    FileUploadRepository fileUploadRepository;

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
    
    //récupérer un artiste par son user
    @Override
    public ProfileArtiste getArtisteByUserName(User user){
        return artisteRepository.findByUserArtiste(user);
    }
    
    //récupérer un utilisateur par son id
    @Override
    public ProfileArtiste getArtisteById(Long id){
        ProfileArtiste art = artisteRepository.findById(id);
        return art;
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

    @Override
    public List<TypeArtiste> GetAllArtistTypes() {
        return Lists.newArrayList(typeArtisteRepository.findAll());
    }
    
    @Override
    public List<Region> getAllRegion() {
        return Lists.newArrayList(regionRepository.findAll());
    }
    
    @Override
    public List<ProfileArtiste> SearchArtiste(String type, String localisation) {
        
        String[] typeArtiste = type.split(",");
        String[] localisationArtiste = localisation.split(",");
        
        Long[] typeLong = new Long[typeArtiste.length];
        for(int i=0; i<typeArtiste.length;i++){
            typeLong[i] = Long.valueOf(typeArtiste[i]);
        }
        
        Long[] localisationLong = new Long[localisationArtiste.length];
        for(int i=0; i<localisationArtiste.length; i++){
            localisationLong[i] = Long.valueOf(localisationArtiste[i]);
        }
        
        Set<Long> setTypeArtiste = new HashSet<>(Arrays.asList(typeLong));
        Set<Long> setLocalisationArtiste = new HashSet<>(Arrays.asList(localisationLong));
        
            if((Long) setTypeArtiste.toArray()[0]==0){
            return Lists.newArrayList(artisteRepository.findArtisteByLocalisation(setLocalisationArtiste));
        }else if((Long) setLocalisationArtiste.toArray()[0]==0)
        {
            return Lists.newArrayList(artisteRepository.findArtisteByType(setTypeArtiste));
        }else 
            return Lists.newArrayList(artisteRepository.findArtisteByTypeAndLocalisation(setTypeArtiste, setLocalisationArtiste));
        
    }
    
}
