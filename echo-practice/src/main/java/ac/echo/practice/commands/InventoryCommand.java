package ac.echo.practice.commands;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.practice.EchoPractice;

public class InventoryCommand implements CommandExecutor {
    EchoPractice echoPractice;
    
    public InventoryCommand(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;

            if(args.length > 0){

                echoPractice.getGuiManager().showInventoryGui(p, echoPractice.getFinalInventoryManager().getInventory(UUID.fromString(args[0])), null);

                

                p.sendMessage("Player not found!");
            }else{
                p.sendMessage("args: /inventory <uuid>");
            }
        }

        return true;
    }
}
