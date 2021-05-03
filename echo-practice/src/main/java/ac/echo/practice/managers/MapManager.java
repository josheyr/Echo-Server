package ac.echo.practice.managers;

import java.util.ArrayList;
import java.util.Random;

import ac.echo.core.classes.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Map;
import ac.echo.practice.enums.MapType;

public class MapManager {
    ArrayList<Map> maps = new ArrayList<>();

    public MapManager(EchoPractice echoPractice) {

        {
            
            //addMap(new Map(<ID>, <NAME>, MapType.<MAP TYPE>, <PASTE LOCATION>));
            addMap(new Map(0, "Archeo", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 2000, 250, 2000, 0.0f, 0.0f)));
            addMap(new Map(1, "Spruce", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -2000, 250, -2000, 0.0f, 0.0f)));
            addMap(new Map(2, "Dhrino", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -2000, 250, 2000, 0.0f, 0.0f)));
            addMap(new Map(3, "Armageddon", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 2000, 250, -2000, 0.0f, 0.0f)));
            addMap(new Map(4, "Alien", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 4000, 250, 2000, 0.0f, 0.0f)));
            addMap(new Map(5, "Rectangle", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 4000, 250, 4000, 0.0f, 0.0f)));
            addMap(new Map(6, "Blippy", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 2000, 250, 4000, 0.0f, 0.0f)));
            addMap(new Map(7, "Eyepat", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -2000, 250, 4000, 0.0f, 0.0f)));
            addMap(new Map(8, "Shades", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 6000, 250, 2000, 0.0f, 0.0f)));
            addMap(new Map(9, "Cyborg", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 6000, 250, 4000, 0.0f, 0.0f)));
            addMap(new Map(10, "Corona", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 0, 250, 2000, 0.0f, 0.0f)));
            addMap(new Map(11, "Google", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 0, 250, 4000, 0.0f, 0.0f)));
            addMap(new Map(12, "Rocky", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 0, 250, 6000, 0.0f, 0.0f)));
            addMap(new Map(13, "Mountains", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 0, 250, -6000, 0.0f, 0.0f)));
            addMap(new Map(14, "Orchard", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 0, 250, -4000, 0.0f, 0.0f)));
            addMap(new Map(15, "Deserted", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 0, 250, -2000, 0.0f, 0.0f)));
            addMap(new Map(16, "Swamp", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 2000, 250, 0, 0.0f, 0.0f)));
            addMap(new Map(17, "Boulders", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 4000, 250, 0, 0.0f, 0.0f)));
            addMap(new Map(18, "Redsand", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 6000, 250, 0, 0.0f, 0.0f)));
            addMap(new Map(19, "Trees", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -6000, 250, 0, 0.0f, 0.0f)));
            addMap(new Map(20, "Decay", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -4000, 250, 0, 0.0f, 0.0f)));
            addMap(new Map(21, "Drought", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -2000, 250, 0, 0.0f, 0.0f)));
            addMap(new Map(22, "Mars", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -6000, 250, 2000, 0.0f, 0.0f)));
            addMap(new Map(23, "Barren", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -6000, 250, 4000, 0.0f, 0.0f)));
            addMap(new Map(24, "Degrade", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -6000, 250, 6000, 0.0f, 0.0f)));
            addMap(new Map(25, "Pine", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 2000, 250, -6000, 0.0f, 0.0f))); // move spawns closer 43 blocks
            addMap(new Map(26, "Rats", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 4000, 250, -6000, 0.0f, 0.0f), true));
            addMap(new Map(27, "Saloon", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 6000, 250, -6000, 0.0f, 0.0f), true));
            addMap(new Map(28, "Marina", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -6000, 250, -6000, 0.0f, 0.0f), true));
            addMap(new Map(29, "Extinction", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -4000, 250, -6000, 0.0f, 0.0f), true));
            addMap(new Map(30, "Tropics", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -2000, 250, -6000, 0.0f, 0.0f), true));
            addMap(new Map(31, "Classic", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -6000, 250, -2000, 0.0f, 0.0f)));
            addMap(new Map(32, "Shrubs", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -6000, 250, -4000, 0.0f, 0.0f)));
            addMap(new Map(33, "Hellish", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 6000, 250, -2000, 0.0f, 0.0f)));
            addMap(new Map(34, "Venus", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 6000, 250, -4000, 0.0f, 0.0f)));
            addMap(new Map(35, "Rockets", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -4000, 250, 6000, 0.0f, 0.0f)));
            addMap(new Map(36, "Football", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -2000, 250, 6000, 0.0f, 0.0f))); //move spawns closer 15 blocks
            addMap(new Map(37, "YouTube", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 8000, 250, 8000, 0.0f, 0.0f)));
            addMap(new Map(38, "Simpsons", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 8000, 250, -8000, 0.0f, 0.0f)));
            addMap(new Map(39, "Herb", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -8000, 250, -8000, 0.0f, 0.0f)));
            addMap(new Map(40, "Urine", MapType.NORMAL, new Location(Bukkit.getWorld("world"), -8000, 250, 8000, 0.0f, 0.0f)));
            addMap(new Map(41, "Yellow", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 8000, 250, 2000, 0.0f, 0.0f)));
            addMap(new Map(42, "Clownfish", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 8000, 250, 4000, 0.0f, 0.0f)));
            addMap(new Map(43, "Blueprint", MapType.NORMAL, new Location(Bukkit.getWorld("world"), 8000, 250, 6000, 0.0f, 0.0f)));

            addMap(new Map(44, "Clay", MapType.SAFEROOM, new Location(Bukkit.getWorld("world"), 4000, 250, -2000, 0.0f, 0.0f)));
            addMap(new Map(45, "Trees", MapType.SAFEROOM, new Location(Bukkit.getWorld("world"), 4000, 250, -4000, 0.0f, 0.0f)));
            addMap(new Map(45, "Oragami", MapType.SAFEROOM, new Location(Bukkit.getWorld("world"), 8000, 250, -6000, 0.0f, 0.0f)));
            addMap(new Map(45, "Penguin", MapType.SAFEROOM, new Location(Bukkit.getWorld("world"), -8000, 250, 6000, 0.0f, 0.0f)));
            addMap(new Map(45, "Desert", MapType.SAFEROOM, new Location(Bukkit.getWorld("world"), -8000, 250, 4000, 0.0f, 0.0f)));

            addMap(new Map(46, "Birch", MapType.SUMO, new Location(Bukkit.getWorld("world"), -4000, 64, 2000, 0.0f, 0.0f)));
            addMap(new Map(47, "Pit", MapType.SUMO, new Location(Bukkit.getWorld("world"), -4000, 64, 4000, 0.0f, 0.0f)));
            addMap(new Map(48, "Antarctic", MapType.SUMO, new Location(Bukkit.getWorld("world"), 2000, 64, -4000, 0.0f, 0.0f)));
            addMap(new Map(49, "Mesa", MapType.SUMO, new Location(Bukkit.getWorld("world"), -2000, 64, -4000, 0.0f, 0.0f)));
            addMap(new Map(50, "Wildwest", MapType.SUMO, new Location(Bukkit.getWorld("world"), -4000, 64, -2000, 0.0f, 0.0f)));
            addMap(new Map(51, "Classic", MapType.SUMO, new Location(Bukkit.getWorld("world"), 2000, 64, 6000, 0.0f, 0.0f)));
            addMap(new Map(52, "Jungle", MapType.SUMO, new Location(Bukkit.getWorld("world"), 4000, 64, 6000, 0.0f, 0.0f)));
            addMap(new Map(53, "Bahamas", MapType.SUMO, new Location(Bukkit.getWorld("world"), 6000, 64, 6000, 0.0f, 0.0f)));
            addMap(new Map(53, "Sponge", MapType.SUMO, new Location(Bukkit.getWorld("world"), 8000, 64, -2000, 0.0f, 0.0f)));
            addMap(new Map(53, "Dojo", MapType.SUMO, new Location(Bukkit.getWorld("world"), 8000, 64, -4000, 0.0f, 0.0f)));
        }

        {
            // //CREATE TEST SUMO MAP
            // Map map = new Map(1, "Willybum", MapType.SUMO);
            // map.addSpawnPoint(new Location(Bukkit.getWorld("world"), -6, 65, 117, 0.0f, 0.0f));
            // map.addSpawnPoint(new Location(Bukkit.getWorld("world"), 3, 65, 117, 0.0f, 0.0f));

            // addMap(map);
        }
    }

    public void addMap(Map map){
        maps.add(map);
    }

    public Map getMap(int id) {
        for (Map map : maps) {
            if (map.getId() == id) {
                return map;
            }
        }
        return null;
    }

    public Map getMap(String name) {
        for (Map map : maps) {
            if (map.getName().toLowerCase().equals(name.toLowerCase())) {
                return map;
            }
        }
        try {
            return getMap(Integer.parseInt(name));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Map getRandomFreeMap(MapType mapType){
        ArrayList<Map> targetMaps = new ArrayList<>();

        for(Map map : maps){
            if(!map.isOccupied()){
                if(map.getMapType() == mapType){
                    targetMaps.add(map);
                }
            }
        }

        Random random = new Random();

        if(targetMaps.isEmpty()){
            return null;
        }
        return targetMaps.get(random.nextInt(targetMaps.size()));        
    }
}
