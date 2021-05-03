package ac.echo.core.managers;

import java.util.ArrayList;

import org.bukkit.Location;

import ac.echo.core.classes.Region;

public class RegionManager {
    
    ArrayList<Region> regions = new ArrayList<>();

    public RegionManager(){
        
    }

    public void addRegion(Region region){
        regions.add(region);
    }

    public void removeRegion(Region region){
        regions.remove(region);
    }

    public Region createRegion(Location corner1, Location corner2){
        Region region = new Region(corner1, corner2);
        addRegion(region);
        
        return region;
    }

}
