package ac.echo.core.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class ChatUtils {

    public static PacketPlayOutChat createPacketPlayOutChat(String s){return new PacketPlayOutChat(ChatSerializer.a(s));}

    public static void sendJsonMessage(Player p, String s){( (CraftPlayer)p ).getHandle().playerConnection.sendPacket( createPacketPlayOutChat(s) );}
}
