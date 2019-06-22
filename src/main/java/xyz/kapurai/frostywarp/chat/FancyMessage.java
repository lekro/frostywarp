/*
 * Copyright 2019 lekro (kapurai).
 *
 * This file is part of frostywarp.
 *
 * frostywarp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * frostywarp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with frostywarp.  If not, see <https://www.gnu.org/licenses/>.
 */


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
