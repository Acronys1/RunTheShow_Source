package runtheshow.resource.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import runtheshow.resource.entities.Invitation;
import runtheshow.resource.entities.User;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {

    
    
    @Query("SELECT invit "
                + "FROM Invitation invit "
                + "WHERE invit.destinataire.id = ?1")
    public List<Invitation> findInvitReceivedByUser(Long id);
    
    
    @Query("SELECT invit "
                + "FROM Invitation invit "
                + "WHERE invit.expediteur.id = ?1")
    public List<Invitation> findInvitSentByUser(Long id);
}
