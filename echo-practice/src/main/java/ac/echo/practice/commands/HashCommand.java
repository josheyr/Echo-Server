package ac.echo.practice.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.practice.EchoPractice;
import ac.echo.practice.utils.FileUtils;
import ac.echo.practice.utils.ItemUtils;

public class HashCommand implements CommandExecutor {
    EchoPractice echoPractice;

    public HashCommand(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage("saddas");

            if (args.length > 1) {

                if (args[0].equals("in")) {
                    if (args[1].equals("inv")) {
                        try {
                            p.getInventory()
                                    .setContents(ItemUtils.retrievePlayerInventory((FileUtils.readFromTestFile()), 36));
                            // p.getInventory().setArmorContents(ItemUtils.retrievePlayerInventory(FileUtils.readFromTestFile().split("|")[1],
                            // 4));
                            p.sendMessage("retrieved inv");

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else if (args[1].equals("armor")) {
                        try {
                            p.getInventory().setArmorContents(
                                    ItemUtils.retrievePlayerInventory((FileUtils.readFromTestFile()), 4));

                            // p.getInventory().setArmorContents(ItemUtils.retrievePlayerInventory(FileUtils.readFromTestFile().split("|")[1],
                            // 4));
                            p.sendMessage("retrieved armor");

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                } else if (args[0].equals("out")) {

                    if (args[1].equals("inv")) {
                        FileUtils.writeToTestFile(ItemUtils.serializeItemStack(p.getInventory().getContents()));

                        p.sendMessage("saved inv");

                    } else if (args[1].equals("armor")) {
                        FileUtils.writeToTestFile(ItemUtils.serializeItemStack(p.getInventory().getArmorContents()));

                        p.sendMessage("saved armor");
                    }
                }
            } else {
            }

        }

        return true;
    }
}
