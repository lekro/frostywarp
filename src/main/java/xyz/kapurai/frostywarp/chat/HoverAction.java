package xyz.kapurai.frostywarp.chat;

import com.google.gson.annotations.SerializedName;

public class HoverAction {

    public static enum Type {
        @SerializedName("show_text") SHOW_TEXT,
        @SerializedName("show_achievement") SHOW_ACHIEVEMENT,
        @SerializedName("show_item") SHOW_ITEM,
        @SerializedName("show_entity") SHOW_ENTITY;
    }

    private Type action;
    private Object value;

    public HoverAction(Type type, Object value) {
        this.action = type;
        this.value = value;
    }
    
}

