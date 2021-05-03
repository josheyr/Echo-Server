package ac.echo.practice.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Utils {

    public static Location getHighestBlockAt(Location location){
        for(int i = 255; i > 0; i--){
            location.setY(i);

            if(location.getBlock().getType() != Material.AIR && location.getBlock().getType() != Material.GLASS){
                return location;
            }
        }

        return null;
    }

    public static void drainBlocks(Location location){
        if(location.getBlock().isLiquid()){
            location.getBlock().setType(Material.AIR);

            ((Runnable)(() -> {drainBlocks(new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ())); })).run();
            ((Runnable)(() -> {drainBlocks(new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ())); })).run();
            ((Runnable)(() -> {drainBlocks(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 1)); })).run();
            ((Runnable)(() -> {drainBlocks(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() - 1)); })).run();
            ((Runnable)(() -> {drainBlocks(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ())); })).run();
            ((Runnable)(() -> {drainBlocks(new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ())); })).run();
        }
    }

    public static void removeIfCobble(Location location){
        if(location.getBlock().getType() == Material.COBBLESTONE){
            location.getBlock().setType(Material.AIR);
        }
    }

    public static void removeCobbleAround(Location location){
            ((Runnable)(() -> {removeIfCobble(new Location(location.getWorld(), location.getX() + 1, location.getY(), location.getZ()));
                removeIfCobble(new Location(location.getWorld(), location.getX() - 1, location.getY(), location.getZ()));
                removeIfCobble(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() + 1));
                removeIfCobble(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ() - 1));
                removeIfCobble(new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ())); 
                
                for(int i = (int)location.getY(); i > 0; i--){
                    removeIfCobble(new Location(location.getWorld(), location.getX(), (double)i, location.getZ()));
                    removeIfCobble(new Location(location.getWorld(), location.getX(), (double)i, location.getZ() + 1));
                    removeIfCobble(new Location(location.getWorld(), location.getX(), (double)i, location.getZ() - 1));
                    removeIfCobble(new Location(location.getWorld(), location.getX() + 1, (double)i, location.getZ()));
                    removeIfCobble(new Location(location.getWorld(), location.getX() - 1, (double)i, location.getZ()));
                }
            })).run();
            
    }
    
}
