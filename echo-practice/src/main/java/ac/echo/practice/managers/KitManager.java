package ac.echo.practice.managers;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Kit;
import ac.echo.practice.enums.MapType;
import ac.echo.practice.utils.ItemUtils;
import ac.echo.practice.utils.TemporaryShit;
import cc.nano.gspigot.knockback.KnockbackConfig;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class KitManager {

    EchoPractice echoPractice;
    ArrayList<Kit> kits = new ArrayList<>();

    public KitManager(EchoPractice echoPractice) {
        this.echoPractice = echoPractice;

        try {
            ItemStack nodebuffKitIcon = new ItemStack(Material.POTION, 1, (byte) 16421);
            Potion nodebuffKitIconP = new Potion(1);

            nodebuffKitIconP.setSplash(true);
            nodebuffKitIconP.setType(PotionType.INSTANT_HEAL);
            nodebuffKitIconP.apply(nodebuffKitIcon);

            Kit nodebuffKit = new Kit(0, "NoDebuff", nodebuffKitIcon,
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getNoDebuffContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getNoDebuffArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            ItemStack debuffKitIcon = new ItemStack(Material.POTION, 1, (byte) 8202);
            Potion debuffKitIconP = new Potion(1);

            debuffKitIconP.setSplash(true);
            debuffKitIconP.setType(PotionType.SLOWNESS);
            debuffKitIconP.apply(debuffKitIcon);

            Kit debuffKit = new Kit(1, "Debuff", debuffKitIcon,
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getDebuffContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getDebuffArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit sumoKit = new Kit(2, "Sumo", new ItemStack(Material.LEASH), new ItemStack[36], new ItemStack[4],
                    new ArrayList<PotionEffect>());
            sumoKit.setDiesInWater(true);
            sumoKit.setRequiredMapType(MapType.SUMO);

            Kit builduhcKit = new Kit(3, "BuildUHC", new ItemStack(Material.LAVA_BUCKET),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getBuildUHCContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getNoDebuffArmorContents(), 4),
                    new ArrayList<PotionEffect>());
            builduhcKit.setRegenHealth(false);
            builduhcKit.setCanPlaceBlocks(true);

            Kit comboKit = new Kit(4, "Combo", new ItemStack(Material.RAW_FISH, 1, (byte) 3),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getComboContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getComboArmorContents(), 4),
                    new ArrayList<PotionEffect>());
            comboKit.setComboMode(true);

            Kit gappleKit = new Kit(5, "Gapple", new ItemStack(Material.GOLDEN_APPLE, 1, (byte) 1),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getGappleContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getGappleArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit archerKit = new Kit(6, "Archer", new ItemStack(Material.BOW),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getArcherContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getArcherArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit soupKit = new Kit(7, "Soup", new ItemStack(Material.BOWL),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getSoupContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getSoupArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit hcfKit = new Kit(8, "HCF", new ItemStack(Material.FENCE_GATE),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getHCFContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getHCFArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit vanillaKit = new Kit(9, "Vanilla", new ItemStack(Material.POTION, 1, (byte) 8233),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getVanillaContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getVanillaArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit spleefKit = new Kit(10, "Spleef", new ItemStack(Material.DIAMOND_SPADE),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getSpleefContents(), 36), new ItemStack[4],
                    new ArrayList<PotionEffect>());
            
            Kit classicKit = new Kit(11, "Classic", new ItemStack(Material.DIAMOND_SWORD),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getClassicContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getClassicArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit mcsgKit = new Kit(12, "MCSG", new ItemStack(Material.FLINT_AND_STEEL),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getMCSGContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getMCSGArmorContents(), 4),
                    new ArrayList<PotionEffect>());
            mcsgKit.setCanPlaceBlocks(true);
            
            Kit axeKit = new Kit(13, "Axe", new ItemStack(Material.IRON_AXE),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getAxeContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getAxeArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit hgKit = new Kit(14, "HG", new ItemStack(Material.WOOD_SWORD),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getHGContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getHGArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            Kit saferoomKit = new Kit(15, "Saferoom", new ItemStack(Material.IRON_DOOR),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getHCFContents(), 36),
                    ItemUtils.retrievePlayerInventory(TemporaryShit.getHCFArmorContents(), 4),
                    new ArrayList<PotionEffect>());

            sumoKit.setRequiredMapType(MapType.SAFEROOM);

            addKit(nodebuffKit);
            addKit(debuffKit);
            addKit(sumoKit);
            addKit(builduhcKit);
            addKit(comboKit);
            addKit(gappleKit);
            addKit(archerKit);
            addKit(soupKit);
            addKit(hcfKit);
            addKit(vanillaKit);
            addKit(spleefKit);
            addKit(classicKit);
            addKit(mcsgKit);
            addKit(axeKit);
            addKit(hgKit);
            addKit(saferoomKit);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void addKit(Kit kit) {
        kits.add(kit);
    }

    public Kit getKit(int id) {
        for (Kit kit : kits) {
            if (kit.getId() == id) {
                return kit;
            }
        }

        return null;
    }

    public Kit getKit(String name) {
        for (Kit kit : kits) {
            if (kit.getName().toLowerCase().equals(name.toLowerCase())) {
                return kit;
            }
        }

        try {
            return getKit(Integer.parseInt(name));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void applyKit(EchoPlayer ep, Kit kit){
        kit.applyKit(ep);
        Player p = ep.getPlayer();
        echoPractice.getItemManager().removeAllResponses(p);
    }

    public void deapplyKit(Player p, Kit kit) {
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

        if(canPlaceBlocks(ep)){
            deletePlacedBlocks(ep);
            ep.setCanPlaceBlocks(false);
            ep.setCanBreakBlocks(false);
        }

        p.getInventory().setContents(new ItemStack[36]);
        p.getInventory().setArmorContents(new ItemStack[4]);

        p.setHealth(p.getMaxHealth());
        p.setFireTicks(0);

        p.setMaximumNoDamageTicks(19);

        ((CraftPlayer) p).getHandle().setKbProfile(KnockbackConfig.getKbProfileByName("Default"));

        for (PotionEffect potion : p.getActivePotionEffects()) {
            p.removePotionEffect(potion.getType());
        }
    }

    public void deapplyKit(Player p) {
        EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

        if(canPlaceBlocks(ep)){
            deletePlacedBlocks(ep);
            ep.setCanPlaceBlocks(false);
            ep.setCanBreakBlocks(false);
        }

        for (Kit kit : kits) {
            if (kit.hasPlayer(ep)) {
                kit.removePlayer(ep);
            }
        }

        p.getInventory().setContents(new ItemStack[36]);
        p.getInventory().setArmorContents(new ItemStack[4]);

        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);
        p.setExhaustion(0);
        p.setSaturation(0.2f);
        p.setFireTicks(0);

        p.setMaximumNoDamageTicks(19);

        ((CraftPlayer) p).getHandle().setKbProfile(KnockbackConfig.getKbProfileByName("Default"));

        for (PotionEffect potion : p.getActivePotionEffects()) {
            p.removePotionEffect(potion.getType());
        }
    }

    public void deapplyAllKits() {
        for (Kit kit : kits) {
            for (EchoPlayer ep : kit.getPlayers()) {
                Player p = ep.getPlayer();

                deapplyKit(p, kit);
            }
        }
    }

    public boolean isKitted(EchoPlayer ep) {
        for (Kit kit : kits) {
            if (kit.hasPlayer(ep)) {
                return true;
            }
        }

        return false;
    }

    public boolean canDieInWater(EchoPlayer ep) {
        for (Kit kit : kits) {
            if (kit.isDiesInWater()) {
                for (EchoPlayer kp : kit.getPlayers()) {
                    if (kp == ep) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean canRegenHealth(EchoPlayer ep) {
        for (Kit kit : kits) {
            if (kit.isRegenHealth()) {
                for (EchoPlayer kp : kit.getPlayers()) {
                    if (kp == ep) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean canPlaceBlocks(EchoPlayer ep) {
        for (Kit kit : kits) {
            if (kit.isCanPlaceBlocks()) {
                for (EchoPlayer kp : kit.getPlayers()) {
                    if (kp == ep) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean canBreakBlock(EchoPlayer ep, Location loc){
        for (Kit kit : kits) {
            if (kit.isCanPlaceBlocks()) {
                for (EchoPlayer kp : kit.getPlayers()) {
                    if (kp == ep) {
                        if(kit.canBreakBlock(loc)){
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }

    public void addGeneratedBlock(Location loc){
        for (Kit kit : kits) {
            if (kit.isCanPlaceBlocks()) {
                kit.addGeneratedBlock(loc);
            }
        }
    }

    public void addPlacedBlock(EchoPlayer ep, Location loc){
        for (Kit kit : kits) {
            if (kit.isCanPlaceBlocks()) {
                for (EchoPlayer kp : kit.getPlayers()) {
                    if (kp == ep) {
                        kit.addPlacedBlock(ep, loc);
                    }
                }
            }
        }
    }

    public void deletePlacedBlocks(EchoPlayer ep){
        for (Kit kit : kits) {
            if (kit.isCanPlaceBlocks()) {
                for (EchoPlayer kp : kit.getPlayers()) {
                    if (kp == ep) {
                        
                        kit.deletePlacedBlocks(ep);
                    }
                }
            }
        }
    }
}
