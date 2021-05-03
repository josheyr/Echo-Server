package ac.echo.practice.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;

public class ItemUtils {
    public static String serializeItemStack(ItemStack[] itemStacks){

        String items = "";
        //get all items in players inventory
        for(int slot = 0 ; slot < itemStacks.length ; slot++){
            //check if slot is empty
            if(itemStacks[(slot)] != null){
                //get item in slot
                ItemStack item = itemStacks[(slot)];
                String itemStackString = "";
               
                //encode the item into a string
                try {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
                    dataOutput.writeObject(item);
                    dataOutput.close();
                    itemStackString = Base64Coder.encodeLines(outputStream.toByteArray());
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to save item stacks.", e);
                }

                if(slot == itemStacks.length-1){
                    items = items+itemStackString;
                }else{
                    items = items+itemStackString+"##";
                }
            }else{
                if(slot == itemStacks.length-1){
                    items = items+"null";
                }else{
                    items = items+"null"+"##";
                }
            }          
        }
        
        return URLEncoder.encode(items);
    }

    public static ItemStack[] retrievePlayerInventory(String inventory, int size) throws IOException {

        inventory = URLDecoder.decode(inventory);

        ItemStack[] itemStacks = new ItemStack[size];

        //get inventory from mySQL
        if(inventory != null){
            //get stored string
            String input = inventory;

            //loop through players inv slots
            for(int slot = 0 ; slot < size ; slot++){

                String[] parts = input.split("##");

                String itemString = parts[slot];

                //check if slot from saved inventory was empty
                if(parts[slot].equalsIgnoreCase("null")){
                    itemStacks[slot] = null;
                }else{
                  
                    ItemStack itemtoreturn = new ItemStack(Material.AIR);
                  
                    //decode the string back to an itemstack
                    try {
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(itemString));
                        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
                        itemtoreturn = (ItemStack) dataInput.readObject();
                        dataInput.close();
                    } catch (ClassNotFoundException e) {
                        throw new IOException("Unable to decode class type.", e);
                    }

                    itemStacks[slot] = itemtoreturn;
                }
            }
        }

        return itemStacks;
    }

    public static void updateInventoryTitle(Player p, String title)
    {
        EntityPlayer ep = ((CraftPlayer)p).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(ep.activeContainer.windowId, "minecraft:chest", new ChatMessage(title), p.getOpenInventory().getTopInventory().getSize());
        ep.playerConnection.sendPacket(packet);
        ep.updateInventory(ep.activeContainer);
    }
}
