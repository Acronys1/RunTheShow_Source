/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.resource.metiers;

import runtheshow.resource.entities.Evenement;
import runtheshow.resource.entities.Lieu;

/**
 *
 * @author Sonatines
 */
public interface ILieuMetier {

    public Boolean addLieu(Lieu lieu);

    public Boolean updateLieu(Lieu lieu);

    public Boolean deleteLieu(Evenement event);
    
}
