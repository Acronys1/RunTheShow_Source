package runtheshow.resource.metiers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import runtheshow.resource.entities.Invitation;
import runtheshow.resource.entities.User;
import runtheshow.resource.repository.InvitationRepository;

@Service("invitation_metier")
public class InvitationMetier implements IInvitationMetier {

    @Autowired
    private InvitationRepository invitationRepository;

    @Override
    public Invitation addInvitation(Invitation invitation) {
        return invitationRepository.save(invitation);
    }  

    @Override
    public Invitation accepterInvitation(Invitation invit) {
        invit.setStatut("Acceptée");
        invitationRepository.save(invit);
        return invit;
    }

    @Override
    public Invitation refuserInvitation(Invitation invit) {
        invit.setStatut("Refusée");
        invitationRepository.save(invit);
        return invit;
    }

    @Override
    public Invitation getInvitationById(Long id) {
        return invitationRepository.findOne(id);
    }
    
}
