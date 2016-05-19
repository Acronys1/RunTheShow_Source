/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.webservice;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import runtheshow.resource.entities.ProfileArtiste;
import runtheshow.resource.entities.Region;
import runtheshow.resource.entities.TypeArtiste;
import runtheshow.resource.metiers.IArtisteMetier;
import runtheshow.resource.metiers.IUserMetier;

/**
 *
 * @author madelcaf
 */

@RestController
@RequestMapping("/artiste")
public class ArtisteService {
    
    @Autowired
    private IArtisteMetier artisteMetier;
    
    @Autowired
    private IUserMetier userMetier;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProfileArtiste getUserById(@PathVariable Long id) {
        return artisteMetier.getArtisteById(id);
    }
    
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ProfileArtiste getUserById(Principal user) {
        return artisteMetier.getArtisteByUserName(userMetier.getUserByName(user.getName()));
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
    public Boolean updateArtiste(@RequestBody ProfileArtiste artiste, Principal user, HttpServletResponse response) {
        return artisteMetier.UpdateArtiste(artiste, user);
    }
    
    @RequestMapping(value = "/search/{type}/{localisation}", method = RequestMethod.GET)
    public List<ProfileArtiste> searchArtiste(@PathVariable String type,@PathVariable String localisation) {
        return artisteMetier.SearchArtiste(type, localisation);
    }
    
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public List<TypeArtiste> getAllArtistTypes(){
        return artisteMetier.GetAllArtistTypes();
    }
    
    @RequestMapping(value = "/regions", method = RequestMethod.GET)
    public List<Region> getAllRegion(){
        return artisteMetier.getAllRegion();
    }
}
