package xyz.kapurai.frostywarp.chat;

import com.google.gson.annotations.SerializedName;

public class ClickAction {

    public static enum Type {
        @SerializedName("open_url") OPEN_URL,
        @SerializedName("open_file") OPEN_FILE,
        @SerializedName("run_command") RUN_COMMAND,
        @SerializedName("suggest_command") SUGGEST_COMMAND,
        @SerializedName("change_page") CHANGE_PAGE;
    }

    private Type action;
    private String value;

    public ClickAction(Type type, String value) {
        this.action = type;
        this.value = value;
    }
    
}

