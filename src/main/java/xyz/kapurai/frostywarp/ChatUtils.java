package xyz.kapurai.frostywarp;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.entity.Player;
import java.lang.reflect.InvocationTargetException;

/**
 * Utility methods for sending fancy chats
 */
public final class ChatUtils {

    // Don't instantiate me
    private ChatUtils() {};

    /**
     * Send a fancy message to the client.
     * 
     * Note that there is a limitation of 32767 bytes on json.
     */
    public static void sendJSONMessage(Player p, String json) {

        System.out.println(json);
        PacketContainer chatPacket = new PacketContainer(
                PacketType.Play.Server.CHAT);
        WrappedChatComponent comp = WrappedChatComponent.fromJson(json);
        chatPacket.getChatComponents().write(0, comp);
        chatPacket.getChatTypes().write(0, EnumWrappers.ChatType.SYSTEM);

        try {
            ProtocolLibrary.getProtocolManager()
                .sendServerPacket(p, chatPacket);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * Lazily written routine to get a json string out of those
     * attributes...
     */
    public static String getJSONString(String text, String color,
            boolean bold, boolean italic, boolean underlined,
            boolean strikethrough, boolean obfuscated, String hoverAction,
            String hoverValue, String clickAction, String clickValue,
            String extra) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("{\"text\":\"");
        sb.append(text);
        sb.append('\"');

        if (color != null) {
            sb.append(",\"color\":\"");
            sb.append(color);
            sb.append("\"");
        }

        if (bold) {
            sb.append(",\"bold\":");
            sb.append(bold);
        }
        if (italic) {
            sb.append(",\"italic\":");
            sb.append(italic);
        }
        if (underlined) {
            sb.append(",\"underlined\":");
            sb.append(underlined);
        }
        if (strikethrough) {
            sb.append(",\"strikethrough\":");
            sb.append(strikethrough);
        }
        if (obfuscated) {
            sb.append(",\"obfuscated\":");
            sb.append(obfuscated);
        }

        if (hoverAction != null && hoverValue != null) {
            sb.append(",\"hoverEvent\":{\"action\":\"");
            sb.append(hoverAction);
            sb.append("\",\"value\":");
            sb.append(hoverValue);
            sb.append("}");
        }

        if (clickAction != null && clickValue != null) {
            sb.append(",\"clickEvent\":{\"action\":\"");
            sb.append(clickAction);
            sb.append("\",\"value\":\"");
            sb.append(clickValue);
            sb.append("\"}");
        }

        if (extra != null && extra.length() > 0) {
            sb.append(",\"extra\":[");
            sb.append(extra);
            sb.append(']');
        }

        sb.append("}");

        return sb.toString();
    }

}

