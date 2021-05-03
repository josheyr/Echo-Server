package ac.echo.practice.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;

public class AcceptCommand implements CommandExecutor {
    EchoPractice echoPractice;
    
    public AcceptCommand(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            EchoPlayer rp = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            if(args.length > 0){
                for (Player op : Bukkit.getOnlinePlayers()) {
                    EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(op.getUniqueId());

                    if(op.getDisplayName().equals(args[0])){
                        if(echoPractice.getMatchManager().acceptDuelMatch(rp, ep) != null){
                            p.sendMessage("Found duel request and accepted!");
                        }else{
                            p.sendMessage("Duel request not found!");
                        }

                        return true;
                    }
                }

                p.sendMessage("Player not found!");
            }else{
                p.sendMessage("args: /accept <player>");
            }
        }

        return true;
    }
}
