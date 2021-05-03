package ac.echo.core.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.core.EchoCore;

public class EditCommand implements CommandExecutor {
    EchoCore echoCore;
    
    public EditCommand(EchoCore echoCore){
        this.echoCore = echoCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            EchoPlayer ep = echoCore.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());
            
            if(p.isOp()){
                ep.setCanBreakBlocks(!ep.getCanBreakBlocks());
                ep.setCanPlaceBlocks(!ep.getCanPlaceBlocks());
                p.sendMessage(ep.getCanBreakBlocks() ? "You can now build!" : "You can no-longer build!");

                if(ep.getCanBreakBlocks()){
                    p.setGameMode(GameMode.CREATIVE);
                }else{
                    p.setGameMode(GameMode.SURVIVAL);
                }
            }else{
                p.sendMessage("You don't have permissions.");
            }
        }

        return true;
    }
}
