package ac.echo.practice.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Queue;
import ac.echo.practice.enums.MapType;
import ac.echo.practice.enums.QueueType;

public class QueueManager {
    ArrayList<Queue> queues = new ArrayList<>();

    EchoPractice echoPractice;

    public QueueManager(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;

        addQueue(new Queue(0, "UnrankedNoDebuff", "Unranked NoDebuff", QueueType.UNRANKED, echoPractice.getKitManager().getKit("NoDebuff"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(1, "UnrankedDebuff", "Unranked Debuff", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Debuff"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(3, "UnrankedSumo", "Unranked Sumo", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Sumo"), MapType.SUMO, false, echoPractice));
        addQueue(new Queue(4, "UnrankedBuildUHC", "Unranked BuildUHC", QueueType.UNRANKED, echoPractice.getKitManager().getKit("BuildUHC"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(5, "UnrankedCombo", "Unranked Combo", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Combo"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(6, "UnrankedGapple", "Unranked Gapple", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Gapple"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(7, "UnrankedArcher", "Unranked Archer", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Archer"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(8, "UnrankedSoup", "Unranked Soup", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Soup"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(9, "UnrankedHCF", "Unranked HCF", QueueType.UNRANKED, echoPractice.getKitManager().getKit("HCF"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(10, "UnrankedVanilla", "Unranked Vanilla", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Vanilla"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(11, "UnrankedSpleef", "Unranked Spleef", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Spleef"), MapType.SPLEEF, false, echoPractice));
        addQueue(new Queue(12, "UnrankedClassic", "Unranked Classic", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Classic"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(13, "UnrankedMCSG", "Unranked MCSG", QueueType.UNRANKED, echoPractice.getKitManager().getKit("MCSG"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(14, "UnrankedAxe", "Unranked Axe", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Axe"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(15, "UnrankedHG", "Unranked HG", QueueType.UNRANKED, echoPractice.getKitManager().getKit("HG"), MapType.NORMAL, false, echoPractice));
        addQueue(new Queue(16, "UnrankedSaferoom", "Unranked Saferoom", QueueType.UNRANKED, echoPractice.getKitManager().getKit("Saferoom"), MapType.SAFEROOM, false, echoPractice));
    }

    public void addQueue(Queue queue){
        queues.add(queue);
    }

    public Queue getQueue(int id) {
        for (Queue queue : queues) {
            if (queue.getId() == id) {
                return queue;
            }
        }
        return null;
    }

    public ArrayList<Queue> getQueues(){
        return queues;
    }

    public Queue getQueue(String name) {
        for (Queue queue : queues) {
            if (queue.getName().toLowerCase().equals(name.toLowerCase())) {
                return queue;
            }
        }
        try {
            return getQueue(Integer.parseInt(name));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean isInQueue(EchoPlayer ep){
        for(Queue queue : queues){
            if(queue.inQueue(ep)){
                return true;
            }
        }
        return false;
    }

    public void dequeuePlayer(EchoPlayer ep){
        for(Queue queue : queues){
            if(queue.inQueue(ep)){
                queue.removePlayer(ep);
            }
        }
    }

    public void queuePlayer(EchoPlayer ep, Queue queue){
        Player p = Bukkit.getPlayer(ep.getUuid());

        echoPractice.getItemManager().giveQueueItems(p);
        echoPractice.getScoreboardManager().giveQueuingScoreboard(p, queue, System.currentTimeMillis());
        queue.addPlayer(ep);
    }
}
