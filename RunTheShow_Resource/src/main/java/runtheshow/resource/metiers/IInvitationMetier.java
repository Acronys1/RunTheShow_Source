package runtheshow.resource.metiers;

import runtheshow.resource.entities.Invitation;

public interface IInvitationMetier {
    public Invitation addInvitation(Invitation invitation);

    public Invitation accepterInvitation(Invitation invit);

    public Invitation refuserInvitation(Invitation invit);

    public Invitation getInvitationById(Long id);
}