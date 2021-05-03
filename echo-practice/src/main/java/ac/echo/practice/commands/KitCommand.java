package ac.echo.practice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Kit;
import ac.echo.practice.managers.KitManager;

public class KitCommand implements CommandExecutor {
    EchoPractice echoPractice;

    public KitCommand(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            EchoPlayer rp = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            if (args.length > 0) {
                KitManager kitManager = echoPractice.getKitManager();
                if (!args[0].equals("reset")) {

                    Kit kit = kitManager.getKit(args[0]);

                    if (kit != null) {
                        kitManager.deapplyKit(p);
                        kitManager.applyKit(rp, kit);

                        p.sendMessage("The kit " + kit.getName() + " has been applied.");
                    } else {
                        p.sendMessage("Kit not found!");
                    }
                } else {
                    kitManager.deapplyKit(p);

                    p.sendMessage("Your kit has been reset.");
                }

            }else{
                p.sendMessage("args: /kit <kit/reset>");
            }
        }

        return true;
    }
}
