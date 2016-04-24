/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.webservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import runtheshow.resource.entities.User;
import runtheshow.resource.metiers.IUserMetier;

/**
 *
 * @author ElManoush
 */
@RestController
@RequestMapping("/invitation")
public class InvitationService {

    @Autowired
    private IUserMetier metier;

    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String filter(@PathVariable String caracteres) {
        return metier.getUsersBySearch(caracteres).toString();
    }
}
