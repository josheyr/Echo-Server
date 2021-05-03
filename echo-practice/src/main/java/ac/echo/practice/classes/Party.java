package ac.echo.practice.classes;

import java.util.ArrayList;
import java.util.Random;

import ac.echo.core.classes.EchoPlayer;
import lombok.Getter;
import lombok.Setter;

public class Party {


    @Getter @Setter EchoPlayer leader;
    @Getter ArrayList<EchoPlayer> members = new ArrayList<>();
    @Getter ArrayList<EchoPlayer> invites = new ArrayList<>();
    @Getter @Setter boolean open = false;
    @Getter @Setter boolean allinvite = false;
    @Getter @Setter int limit = 10;

    public Party(EchoPlayer ep){
        leader = ep;
    }

    public boolean isFull(){
        return limit == members.size();
    }

    public void addMember(EchoPlayer ep){
        members.add(ep);
    }

    public void addInvite(EchoPlayer ep){
        invites.add(ep);
    }

    public void removeInvite(EchoPlayer ep){
        invites.remove(ep);
    }

    public boolean isInvited(EchoPlayer ep){
        return invites.contains(ep);
    }

    public void removeMember(EchoPlayer ep){

        if(ep == leader)
            removeLeader();
        else
            members.remove(ep);
    }

    public boolean isMember(EchoPlayer ep){
        return members.contains(ep);
    }

    public void removeLeader(){
        Random random = new Random();
        setLeader(members.get(random.nextInt(members.size())));

        members.remove(leader);
    }

    public void sendMessage(String message){
        leader.getPlayer().sendMessage(message);

        for(EchoPlayer ep : members){
            ep.getPlayer().sendMessage(message);
        }
    }
}
