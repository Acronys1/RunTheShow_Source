/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.webservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import java.security.Principal;
import java.util.ArrayList;
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
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author ElManoush
 */
@RestController
@RequestMapping("/invitation")
public class InvitationService {

    @Autowired
    private IUserMetier metier;

    @RequestMapping(value = "/filter/{caracteres}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String filter(@PathVariable String caracteres) {
        return metier.getUsersBySearch(caracteres).toString();
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public Boolean addInvitation(@RequestBody Object o, HttpServletResponse response) throws JSONException {
        LinkedHashMap lhm = (LinkedHashMap) o;
        Set<Long> set = new HashSet<Long>((Collection<Long>)lhm.get("id_art"));
        Set<Long> setLong = new HashSet<Long>();
        
        Iterator i= set.iterator(); // on crée un Iterator pour parcourir notre HashSet
        while(i.hasNext()) // tant qu'on a un suivant
        {
                setLong.add(Long.parseLong(i.next().toString()));
        }
        List<User> cavabuger = metier.getUsersArtisteByListId(setLong);
        
        
        /*ArrayList<String> list = new ArrayList<String>();     
        JSONArray jsonArray = (JSONArray)o; 
        if (jsonArray != null) { 
           int len = jsonArray.length();
           for (int i=0;i<len;i++){ 
            list.add(jsonArray.get(i).toString());
           } 
        }*/
     
        return null;
    }
}
