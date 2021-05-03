package ac.echo.core.classes;

import org.bukkit.Location;

public class Region {
    Location corner1;
    Location corner2;

    public Region(Location corner1, Location corner2){
        this.corner1 = corner1;
        this.corner2 = corner2;
    }

    public boolean isInRegion(Location location){
        double cornerMinX = Math.min(corner1.getX(), corner2.getX());
        double cornerMaxX = Math.max(corner1.getX(), corner2.getX());
        
        double cornerMinY = Math.min(corner1.getY(), corner2.getY());
        double cornerMaxY = Math.max(corner1.getY(), corner2.getY());

        double cornerMinZ = Math.min(corner1.getZ(), corner2.getZ());
        double cornerMaxZ = Math.max(corner1.getZ(), corner2.getZ());

        if(location.getX() > cornerMinX && location.getX() < cornerMaxX && location.getY() > cornerMinY && location.getY() < cornerMaxY && location.getZ() > cornerMinZ && location.getZ() < cornerMaxZ)
            return true;
            
        return false;
    }
}
