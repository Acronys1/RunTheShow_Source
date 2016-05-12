/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.webservice;

import java.security.Principal;
import java.util.LinkedHashMap;
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
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import runtheshow.resource.entities.Invitation;
import runtheshow.resource.entities.SousEvenement;
import runtheshow.resource.metiers.IInvitationMetier;
import runtheshow.resource.metiers.ISousEventMetier;

/**
 *
 * @author ElManoush
 */
@RestController
@RequestMapping("/invitation")
public class InvitationService {
    
    private static final String STATUT_CREEE = "Invitation envoyée.";
    private static final String STATUT_REPONDU_OUI = "Invitation acceptée.";
    private static final String STATUT_REPONDU_NON = "Invitation refusée.";

    @Autowired
    private IUserMetier userMetier;
    @Autowired
    private ISousEventMetier sousEventMetier;
    @Autowired
    private IInvitationMetier invitationMetier;

    /**
     * 
     * @param caracteres
     * @return 
     */
    @RequestMapping(value = "/filter/{caracteres}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String filter(@PathVariable String caracteres) {
        return userMetier.getUsersBySearch(caracteres).toString();
    }
    
    /**
     * 
     * @param user
     * @return 
     */
    @RequestMapping(value = "/retreiveReceivedInvit", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Invitation> retreiveReceivedInvit(Principal user) {
        return userMetier.getAllInvitationUserReceived(userMetier.getUserByName(user.getName()));
    }
    
    /**
     * 
     * @param user
     * @return 
     */
    @RequestMapping(value = "/retreiveSentInvit", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Invitation> retreiveSentInvit(Principal user) {
        return userMetier.getAllInvitationUserSent(userMetier.getUserByName(user.getName()));
    }
    
    /**
     * 
     * @param invit
     * @return 
     */
    @RequestMapping(value = "/accepterInvit", method = RequestMethod.POST,consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public JSONObject accepterInvitation(@RequestBody Object o, HttpServletResponse response) {
        LinkedHashMap lhm = (LinkedHashMap) o;
        long idInvit = (long) (int) lhm.get("id");
        invitationMetier.accepterInvitation(invitationMetier.getInvitationById(idInvit));
        return null;
    }
    
    /**
     * 
     * @param invit
     * @return 
     */
    @RequestMapping(value = "/refuserInvit", method = RequestMethod.POST,consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public JSONObject refuserInvit(@RequestBody Object o, HttpServletResponse response) {
        LinkedHashMap lhm = (LinkedHashMap) o;
        long idInvit = (long) (int) lhm.get("id");
        invitationMetier.refuserInvitation(invitationMetier.getInvitationById(idInvit));
        return null;
    }
    
    /**
     * 
     * @param o
     * @param response
     * @param user
     * @return
     * @throws JSONException 
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8",produces = "application/json; charset=UTF-8")
    public JSONObject addInvitation(@RequestBody Object o, HttpServletResponse response, Principal user) throws JSONException {
        LinkedHashMap lhm = (LinkedHashMap) o;
        Set<Long> set = new HashSet<>((Collection<Long>)lhm.get("id_art"));
        String msgPerso = null;
        long idSousEvent = (long) (int) lhm.get("idSousEvent");
        if(lhm.get("message_perso") != null)
           msgPerso = (String) lhm.get("message_perso");
        Set<Long> setLong = new HashSet<>();
        
        Iterator i= set.iterator(); // on crée un Iterator pour parcourir notre HashSet
        while(i.hasNext()){
                setLong.add(Long.parseLong(i.next().toString()));
        }
        List<User> lstArtiste = userMetier.getUsersArtisteByListId(setLong);
        System.out.println(idSousEvent);
        SousEvenement ssEvent = sousEventMetier.findSousEventById(idSousEvent); 
        User exp = userMetier.getUserByName(user.getName());
        
        
        for(User u:lstArtiste){
            Invitation newInvit = new Invitation();
            if(msgPerso != null)
                newInvit.setCommentaire(msgPerso);
            newInvit.setDestinataire(u);
            newInvit.setExpediteur(exp);
            newInvit.setSousEvenement(ssEvent);
            newInvit.setStatut(STATUT_CREEE);
            invitationMetier.addInvitation(newInvit);
        }

        return null;
    }
    
    
}
