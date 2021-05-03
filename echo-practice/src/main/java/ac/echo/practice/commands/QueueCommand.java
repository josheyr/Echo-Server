package ac.echo.practice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Queue;
import ac.echo.practice.managers.QueueManager;

public class QueueCommand implements CommandExecutor {
    EchoPractice echoPractice;
    
    public QueueCommand(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            QueueManager queueManager = echoPractice.getQueueManager();

            if(args.length > 0){
                if(!queueManager.isInQueue(ep)){
                    Queue queue = queueManager.getQueue(args[0]);

                    if(queue != null){
                        queueManager.queuePlayer(ep, queue);
                        p.sendMessage("Joined queue " + queue.getName());
                    }else{
                        p.sendMessage("Queue not found.");
                    }
                }else{
                    p.sendMessage("Already queuing.");
                }
            }else{
                p.sendMessage("args: /queue <queue>");
            }
        }

        return true;
    }
}
