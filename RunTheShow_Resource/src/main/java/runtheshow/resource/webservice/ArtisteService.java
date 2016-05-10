/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import runtheshow.resource.entities.ProfileArtiste;
import runtheshow.resource.metiers.IArtisteMetier;

/**
 *
 * @author madelcaf
 */

@RestController
@RequestMapping("/artiste")
public class ArtisteService {
    
    @Autowired
    private IArtisteMetier artisteMetier;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProfileArtiste getUserById(@PathVariable Long id) {
        return artisteMetier.getArtisteById(id);
    }
}
