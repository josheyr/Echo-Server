package ac.echo.practice.classes;

import java.util.ArrayList;

import ac.echo.core.classes.EchoPlayer;
import lombok.Getter;
import lombok.Setter;

public class Team {
    @Getter @Setter ArrayList<EchoPlayer> players;


    public Team(ArrayList<EchoPlayer> players){
        this.players = players;
    }

    public Team(EchoPlayer player){
        this.players = new ArrayList<EchoPlayer>();
        this.players.add(player);
    }
}
