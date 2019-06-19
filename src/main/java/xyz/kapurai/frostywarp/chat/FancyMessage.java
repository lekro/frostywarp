package xyz.kapurai.frostywarp.chat;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class FancyMessage {

    // These are public. yes.
    public String text;
    public Color color;
    public Boolean bold, italic, underlined, strikethrough, obfuscated;
    public HoverAction hoverEvent;
    public ClickAction clickEvent;
    public List<FancyMessage> extra;
    
    private static Gson gson;

    static {
        gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .disableHtmlEscaping()
            .create();
    };


    public FancyMessage(String text) {
        this.text = text;
        color = Color.WHITE;
        bold = italic = underlined = strikethrough = obfuscated = null;
        hoverEvent = null;
        clickEvent = null;
        extra = null;
    }

    public String toString() {
        return gson.toJson(this);
    }

    public void add(FancyMessage m) {
        if (extra == null) {
            extra = new LinkedList<>();
        }

        extra.add(m);
    }

    public void sendTo(Player p) {

        String json = toString();
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

}
