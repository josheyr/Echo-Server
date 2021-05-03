package ac.echo.practice.classes;

import java.util.ArrayList;
import ac.echo.practice.enums.MapType;
import ac.echo.practice.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Map {
    Location location;
    
    @Getter @Setter String name;
    @Getter @Setter int id;

    @Getter @Setter boolean occupied = false;

    @Getter @Setter MapType mapType;

    @Getter ArrayList<Location> spawnPoints = new ArrayList<>();

    public Map(int id, String name, MapType mapType, Location location) {
        this(id, name, mapType, location, false);
    }

    public Map(int id, String name, MapType mapType, Location location, boolean flipped) {
        this.id = id;
        this.name = name;
        this.mapType = mapType;
        this.location = location;

        if(mapType == MapType.SAFEROOM){
            Location spawnPoint1 = Utils.getHighestBlockAt(new Location(Bukkit.getWorld("world"), ((int)location.getX() + (7.5)), 255, ((int)location.getZ() - (1991.5 - 2000)), -90.0f, 0.0f));
            spawnPoint1.setY(spawnPoint1.getY() + 1);
            spawnPoints.add(spawnPoint1);

            Location spawnPoint2 = Utils.getHighestBlockAt(new Location(Bukkit.getWorld("world"), ((int)location.getX() + (8.5)), 255, ((int)location.getZ() - (1991.5 - 2000)), 90.0f, 0.0f));
            spawnPoint2.setY(spawnPoint2.getY() + 1);
            spawnPoints.add(spawnPoint2);
        }

        if(mapType == MapType.NORMAL){
            if(!flipped){
                Location spawnPoint1 = Utils.getHighestBlockAt(new Location(Bukkit.getWorld("world"), ((int)location.getX() - (37.5)), 255, ((int)location.getZ() - (4.5)), 180.0f, 0.0f));
                spawnPoint1.setY(spawnPoint1.getY() + 1);
                spawnPoints.add(spawnPoint1);

                Location spawnPoint2 = Utils.getHighestBlockAt(new Location(Bukkit.getWorld("world"), ((int)location.getX() + (1962.5 - 2000)), 255, ((int)location.getZ() + (1870.5 - 2000)), 0.0f, 0.0f));
                spawnPoint2.setY(spawnPoint2.getY() + 1);
                spawnPoints.add(spawnPoint2);
            }else{
                Location spawnPoint1 = Utils.getHighestBlockAt(new Location(Bukkit.getWorld("world"), ((int)location.getX() + (37.5)), 255, ((int)location.getZ() + (4.5)), 180.0f, 0.0f));
                spawnPoint1.setY(spawnPoint1.getY() + 1);
                spawnPoints.add(spawnPoint1);

                Location spawnPoint2 = Utils.getHighestBlockAt(new Location(Bukkit.getWorld("world"), ((int)location.getX() - (1962.5 - 2000)), 255, ((int)location.getZ() - (1870.5 - 2000)), 0.0f, 0.0f));
                spawnPoint2.setY(spawnPoint2.getY() + 1);
                spawnPoints.add(spawnPoint2);
            }
        }

        if(mapType == MapType.SUMO){
            Location spawnPoint1 = (new Location(Bukkit.getWorld("world"), ((int)location.getX()), 63, ((int)location.getZ() - 3.5), 0.0f, 0.0f));
            spawnPoint1.setY(spawnPoint1.getY() + 1);
            spawnPoints.add(spawnPoint1);

            Location spawnPoint2 = (new Location(Bukkit.getWorld("world"), ((int)location.getX()), 63, ((int)location.getZ() + 4.5), 180.0f, 0.0f));
            spawnPoint2.setY(spawnPoint2.getY() + 1);
            spawnPoints.add(spawnPoint2);
        }
    }
  
    public void addSpawnPoint(Location location){
        if(mapType == MapType.NORMAL){
            spawnPoints.add(location);
            spawnPoints.add(location);

        }
    }

    public void removeSpawnPoint(Location location){
        spawnPoints.remove(location);
    }
}
