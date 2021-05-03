package ac.echo.practice.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import ac.echo.practice.EchoPractice;

public class BlockFromToEventHandler implements Listener {

    EchoPractice echoPractice;

    public BlockFromToEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;   
    }

    private final BlockFace[] faces = new BlockFace[]
    {
        BlockFace.SELF,
        BlockFace.UP,
        BlockFace.DOWN,
        BlockFace.NORTH,
        BlockFace.EAST,
        BlockFace.SOUTH,
        BlockFace.WEST,
     
    };

    @SuppressWarnings("deprecation")
    public void generatesCobble(int id, Block b)
    {
        int mirrorID1 = (id == 8 || id == 9 ? 10 : 8);
        int mirrorID2 = (id == 8 || id == 9 ? 11 : 9);
        for(BlockFace face : faces)
        {
            Block r = b.getRelative(face, 1);
            if(r.getTypeId() == mirrorID1 || r.getTypeId() == mirrorID2)
            {
                echoPractice.getKitManager().addGeneratedBlock(r.getLocation());;
            }
        }
    }


    @EventHandler
    public void onBlockFromTo(BlockFromToEvent e){
        if(e.getToBlock().getType() == Material.WATER
        || e.getToBlock().getType() == Material.STATIONARY_WATER
        || e.getToBlock().getType() == Material.LAVA
        || e.getToBlock().getType() == Material.STATIONARY_LAVA
        || e.getToBlock().getType() == Material.COBBLESTONE
        || e.getToBlock().getType() == Material.STONE){
            int id = e.getBlock().getTypeId();
            Block b = e.getToBlock();
            echoPractice.getKitManager().addGeneratedBlock(e.getBlock().getLocation());;

            generatesCobble(id, b);

        }
    }
}
