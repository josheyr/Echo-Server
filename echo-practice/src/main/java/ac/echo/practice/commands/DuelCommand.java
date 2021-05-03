package ac.echo.practice.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Kit;
import ac.echo.practice.classes.Map;

public class DuelCommand implements CommandExecutor {
    EchoPractice echoPractice;

    public DuelCommand(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            EchoPlayer rp = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            if (echoPractice.getMatchManager().getIngameMatch(rp) != null) {
                p.sendMessage("You're already in a match.");
                return true;
            }

            if (args.length > 1) {
                Kit kit = echoPractice.getKitManager().getKit(args[1]);
                if (kit != null) {
                    Map map;
                    if (args.length == 2) {
                        map = echoPractice.getMapManager().getRandomFreeMap(kit.getRequiredMapType());
                    } else {
                        map = echoPractice.getMapManager().getMap(args[2]);
                    }

                    if (map != null) {
                        if (!map.isOccupied()) {

                            for (Player op : Bukkit.getOnlinePlayers()) {
                                EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(op.getUniqueId());

                                if (op.getDisplayName().toLowerCase().equals(args[0].toLowerCase())) {

                                    if (echoPractice.getMatchManager().getIngameMatch(ep) != null) {
                                        p.sendMessage("This player is already in a match.");
                                        return true;
                                    }
                                    echoPractice.getMatchManager().deletePreviousRequestedMatch(rp);

                                    if (echoPractice.getMatchManager().createDuelMatch(map, kit, rp, ep) != null) {
                                        p.sendMessage("You have sent a duel request to " + op.getDisplayName()
                                                + " with kit " + kit.getName() + " on map " + map.getName() + "!");
                                        op.sendMessage("You have recieved a duel request from " + p.getDisplayName()
                                                + " with kit " + kit.getName() + " on map " + map.getName() + "!");
                                    } else {
                                        p.sendMessage("Could not create duel request.");
                                    }
                                    return true;
                                }
                            }

                            p.sendMessage("Player not found.");
                        } else {
                            p.sendMessage("Map " + map.getName() + " is occupied.");
                        }
                    } else {
                        p.sendMessage("Map not found.");
                    }
                } else {
                    p.sendMessage("Kit not found.");
                }
            } else {
                p.sendMessage("args: /duel <player> <kit> <map>");
            }
        }

        return true;
    }
}
