package ac.echo.practice.managers;

import java.util.ArrayList;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Party;

public class PartyManager {
    
    EchoPractice echoPractice;
    ArrayList<Party> parties = new ArrayList<>();

    public PartyManager(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    public void createParty(EchoPlayer ep){
        Party party = new Party(ep);
        ep.getPlayer().sendMessage("You've created a party! Use /party invite <player> to invite people.");
        parties.add(party);
    }

    public boolean addToParty(EchoPlayer ep, Party party){
        party.sendMessage(ep.getPlayer().getDisplayName() + " has joined the party.");
        party.addMember(ep);
        if(party.isInvited(ep)){
            party.removeInvite(ep);
        }
        ep.getPlayer().sendMessage("You have joined " + party.getLeader().getPlayer().getDisplayName() + "'s party!");
        return true;
    }

    public boolean removeFromParty(EchoPlayer ep){
        Party party = getParty(ep);
        if(party != null){
            removeFromParty(ep, party);
        }

        return true;
    }

    public boolean removeFromParty(EchoPlayer ep, Party party){
        if(party.getLeader() == ep){
            party.removeLeader();
        }

        if(party.isMember(ep)){
            party.removeMember(ep);
        }
        
        return true;
    }



    public boolean inviteToParty(EchoPlayer ep, Party party, EchoPlayer inviter){
        party.sendMessage(inviter.getPlayer().getDisplayName() + " has invited " + ep.getPlayer().getDisplayName() + " to the party.");
        party.addInvite(ep);
        ep.getPlayer().sendMessage("You've been invited to " + party.getLeader().getPlayer().getDisplayName() + "'s party by " + inviter.getPlayer().getDisplayName() + ". Use /party join " + party.getLeader().getPlayer().getDisplayName() + " to join.");
        return true;
    }
    
    public Party getParty(EchoPlayer ep){
        for(Party party : parties){
            if(party.getLeader() == ep){
                return party;
            }
            if(party.isMember(ep)){
                return party;
            }
        }

        return null;
    }


}
