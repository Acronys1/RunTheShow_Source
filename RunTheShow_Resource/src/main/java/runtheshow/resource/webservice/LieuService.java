 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.webservice;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import runtheshow.resource.entities.Lieu;
import runtheshow.resource.metiers.ILieuMetier;

/**
 *
 * @author Sonatines
 */
@RestController
@RequestMapping("/lieu")
public class LieuService 
{   
    @Autowired
    private ILieuMetier metier;
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Boolean addLieu(@RequestBody Lieu lieu, HttpServletResponse response) {
        return metier.addLieu(lieu);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
    public Boolean updateUser(@RequestBody Lieu lieu, HttpServletResponse response) {
        return metier.updateLieu(lieu);
    }
}
