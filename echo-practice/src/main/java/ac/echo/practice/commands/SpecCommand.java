package ac.echo.practice.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Match;
import ac.echo.practice.managers.MatchManager;

public class SpecCommand implements CommandExecutor {
    EchoPractice echoPractice;

    public SpecCommand(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            EchoPlayer rp = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
            
            MatchManager matchManager = echoPractice.getMatchManager();

            if(args.length > 0){
                if(!matchManager.inIngameMatch(rp)){
                    Player op = Bukkit.getPlayer(args[0]);
                    if(op != null){
                        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(op.getUniqueId());
                        Match match = matchManager.getIngameMatch(ep);
                        if(match != null){
                            matchManager.removeSpectator(rp);
                            matchManager.addSpectator(rp, match, true, false);
                        }else{
                            p.sendMessage("Player is not in a match!");
                        }
                    }else{
                        p.sendMessage("Player not found!");
                    }

                }else{
                    p.sendMessage("You are in a match!");
                }
            }else{
                p.sendMessage("args: /spec <player>");
            }
        }

        return true;
    }
}
