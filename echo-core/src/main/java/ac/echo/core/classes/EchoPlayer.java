package ac.echo.core.classes;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EchoPlayer {
    
    boolean hotbarFrozen = false;
    boolean canPlaceBlocks = false;
    boolean canBreakBlocks = false;

    boolean hasPearlCooldown = false;
    long lastPearlThrowTime = 0;

    UUID uuid;

    public EchoPlayer(UUID uuid) {
        this.uuid = uuid;
    }
    
    public void throwPearl(){
        lastPearlThrowTime = System.currentTimeMillis();
    }

    public void setLastPearlThrowtime(){
        lastPearlThrowTime = System.currentTimeMillis();
    }

    public long getPearlTimeAgo(){
        return System.currentTimeMillis() - lastPearlThrowTime;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void setCanPlaceBlocks(boolean canPlaceBlocks) {
        this.canPlaceBlocks = canPlaceBlocks;
    }

    public boolean getCanPlaceBlocks() {
        return this.canPlaceBlocks;
    }

    public void setCanBreakBlocks(boolean canBreakBlocks) {
        this.canBreakBlocks = canBreakBlocks;
    }

    public boolean getCanBreakBlocks(){
        return this.canBreakBlocks;
    }

    public void setHotbarFrozen(boolean hotbarFrozen) {
        this.hotbarFrozen = hotbarFrozen;
    }

    public boolean isHotbarFrozen() {
        return this.hotbarFrozen;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void destroy(){
        uuid = null;
    }
}
